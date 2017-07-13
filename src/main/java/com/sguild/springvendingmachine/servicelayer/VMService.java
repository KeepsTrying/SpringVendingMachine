/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.springvendingmachine.servicelayer;

import com.sguild.springvendingmachine.dao.VMDataTransferException;
import com.sguild.springvendingmachine.dto.ChangePurse;
import com.sguild.springvendingmachine.dto.Merch;
import com.sguild.springvendingmachine.dto.PurchaseDTO;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface VMService {
    
    public void loadInventory() throws VMDataTransferException;

    public List<Merch> getAllMerch();

    public BigDecimal getDifference(PurchaseDTO purchaseInfo);
    
    public ChangePurse buildChangePurse(BigDecimal remainder);
    
    public Merch getItem(int slotNum);

}
