/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.springvendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author apprentice
 */
public class PurchaseDTO {

    private int selection;

    private BigDecimal userBalance;

    
    public int getSelection() {
        return selection;
    }

    public void setSelection(Integer selection) {
        this.selection = selection;
    }

    public BigDecimal getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(BigDecimal userBalance) {
        this.userBalance = userBalance;
    }

}
