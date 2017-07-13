/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.springvendingmachine.dao;

import com.sguild.springvendingmachine.dto.Merch;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class VMDaoImpl implements VMDao {

    private String inventoryFileString = "/inventory.txt";

    @Override
    public void setInventoryFile(String inventoryFile) {
        this.inventoryFileString = inventoryFile;
    }

    private static final String DELIMITER = "::";

    private Map<Integer, Merch> inventory = new HashMap<>();

    @Override
    public List<Merch> getAllMerch() {
        return new ArrayList<Merch>(inventory.values());
    }

    @Override
    public Merch getItem(int slotNum) {
        return inventory.get(slotNum);
    }

    @Override
    public int getInvSlots() {
        int size = inventory.size();
        return size;
    }

    @Override
    public void loadInventory() throws VMDataTransferException {
        Scanner load;

        try {
            String inventoryFile = getClass().getResource(inventoryFileString).getFile();
            load = new Scanner(new BufferedReader(new FileReader(inventoryFile)));
        } catch (FileNotFoundException e) {
            throw new VMDataTransferException("Could not locate merchandise.");
        }

        String currentLine;
        String[] currentTokens;
        int slotNum = 0;
        boolean haveErrors = false;

        while (load.hasNextLine()) {
            currentLine = load.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            //validation to check for currentTokens array length to skip improperly built objects
            if (currentTokens.length == 3) {
                //if properly built, so far our current line has no errors
                haveErrors = false;

                Merch currentItem = new Merch();

                String itemName = currentTokens[0];
                currentItem.setName(itemName);

                
                //Classification: Improvement Desirable
                //Ranking:          Minor
                //Could implement file persistence of corrupt data for reference
                //any corrupt data throws an exception notifying the loop to skip to the next line of data
                try {
                    currentItem.setPrice(validateBigD(currentTokens[1], itemName));
                } catch (VMDataTransferException e) {
                    haveErrors = true;
                }

                try {
                    currentItem.setQuantity(validateInt(currentTokens[2], itemName));
                } catch (VMDataTransferException e) {
                    haveErrors = true;
                }

                //if passes all validation, make the slot, make the merchandise that goes in the slot
                if (!haveErrors) {
                    slotNum++;
                    currentItem.setSlotNum(slotNum);
                    inventory.put(currentItem.getSlotNum(), currentItem);
                }
            }
        }
        load.close();
    }

    private BigDecimal validateBigD(String formatItem, String merchName) throws VMDataTransferException {
        double bigDAsDouble;
        BigDecimal fullBigD, scaledBigD;

        try {
            bigDAsDouble = Double.parseDouble(formatItem);
            if (bigDAsDouble > 0) {
                fullBigD = new BigDecimal(formatItem);
                scaledBigD = fullBigD.setScale(2, RoundingMode.HALF_UP);
            } else {
                throw new VMDataTransferException("Improper field found in item " + merchName);
            }
        } catch (NumberFormatException e) {
            throw new VMDataTransferException("Improper field found in item " + merchName);
        }
        return scaledBigD;
    }

    private int validateInt(String formatItem, String merchName) throws VMDataTransferException {
        int stockCount;

        try {
            stockCount = Integer.parseInt(formatItem);
            if (stockCount < 0) {
                throw new VMDataTransferException("Improper field found in item " + merchName);
            }
        } catch (NumberFormatException e) {
            throw new VMDataTransferException("Improper field found in item " + merchName);
        }
        return stockCount;
    }
    


}
    

