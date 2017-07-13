/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.springvendingmachine.dao;

/**
 *
 * @author apprentice
 */
public class VMDataTransferException extends Exception {

    public VMDataTransferException(String message) {
        super(message);
    }

    public VMDataTransferException(String message, Throwable cause) {
        super(message, cause);
    }
}
