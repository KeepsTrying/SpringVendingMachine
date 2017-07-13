/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.springvendingmachine.controller;

import com.sguild.springvendingmachine.dao.VMDataTransferException;
import com.sguild.springvendingmachine.dto.ChangePurse;
import com.sguild.springvendingmachine.dto.Merch;
import com.sguild.springvendingmachine.dto.PurchaseDTO;
import com.sguild.springvendingmachine.servicelayer.VMService;
import java.math.BigDecimal;
import static java.math.BigDecimal.ZERO;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class VMController {

    VMService service;

    @Inject
    public VMController(VMService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landing(HttpServletRequest request, Model model) {
        try {
            service.loadInventory();
        } catch (VMDataTransferException e) {
            System.out.println("add error page");
        }
        List<Merch> merchandise = service.getAllMerch();
        model.addAttribute("merchandise", merchandise);
        return "index";
    }

    @RequestMapping(value = "/vendItem", method = RequestMethod.POST)
    public String vending(HttpServletRequest request, Model model) {
        List<Merch> merchandise = service.getAllMerch();
        String stringSlotNum = request.getParameter("slotNum");
        int slotNum = 0;
        int quantity = 0;
        Merch desire;
        BigDecimal difference;

        try {
            slotNum = Integer.parseInt(stringSlotNum);
        } catch (NumberFormatException e) {
            return "noItem";
        }

        desire = service.getItem(slotNum);
        quantity = desire.getQuantity();
        String name = desire.getName();

        String stringBalance = request.getParameter("userBalance");
        BigDecimal attemptingBalance = new BigDecimal(stringBalance).setScale(2);

        PurchaseDTO purchaseInfo = new PurchaseDTO();
        purchaseInfo.setSelection(slotNum);
        purchaseInfo.setUserBalance(attemptingBalance);

        difference = service.getDifference(purchaseInfo);

        model.addAttribute("name", name);
        model.addAttribute("purchaseInfo", purchaseInfo);

        if (quantity <= 0) {
            model.addAttribute("merchandise", merchandise);
            return "outOfStock";
        } else if (difference.compareTo(ZERO) == 0) {
            quantity--;
            desire.setQuantity(quantity);
            model.addAttribute("merchandise", merchandise);
            return "exactChange";
        } else if (difference.compareTo(ZERO) > 0) {
            quantity--;
            desire.setQuantity(quantity);
            model.addAttribute("merchandise", merchandise);
            ChangePurse change = service.buildChangePurse(difference);
            model.addAttribute("change", change);
            return "successAndChange";
        } else {
            difference = difference.negate();
            model.addAttribute("merchandise", merchandise);
            model.addAttribute("difference", difference);
            return "insufficientFunds";
        }

    }

}
