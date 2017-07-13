/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.springvendingmachine.servicelayer;

import com.sguild.springvendingmachine.dao.VMDao;
import com.sguild.springvendingmachine.dao.VMDaoImpl;
import com.sguild.springvendingmachine.dao.VMDataTransferException;
import com.sguild.springvendingmachine.dto.ChangePurse;
import com.sguild.springvendingmachine.dto.Merch;
import com.sguild.springvendingmachine.dto.PurchaseDTO;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author apprentice
 */
public class VMServiceImpl implements VMService {

    VMDao dao;
    
    @Inject
    private VMServiceImpl(VMDaoImpl dao) {
        this.dao = dao;
    }
    
    @Override
    public ChangePurse buildChangePurse(BigDecimal remainder){
        BigDecimal totalPenniesBD = remainder.movePointRight(2);
        int totalPenniesInt = totalPenniesBD.intValue();
        ChangePurse change = new ChangePurse(totalPenniesInt);
        
        return change;
    }
    
    
    @Override
    public BigDecimal getDifference(PurchaseDTO purchaseInfo) {
        BigDecimal price = dao.getItem(purchaseInfo.getSelection()).getPrice();
        BigDecimal difference = purchaseInfo.getUserBalance().subtract(price);
        return difference;
    }

    @Override
    public void loadInventory() throws VMDataTransferException {
        dao.loadInventory();
    }

    @Override
    public List<Merch> getAllMerch() {
        return dao.getAllMerch();
    }

    @Override
    public Merch getItem(int slotNum) {
        return dao.getItem(slotNum);
    }

}
