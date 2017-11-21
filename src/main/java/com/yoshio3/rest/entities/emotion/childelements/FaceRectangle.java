/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yoshio3.rest.entities.emotion.childelements;

/**
 *
 * @author yoterada
 */

public enum FaceRectangle {
     HEIGHT("height"),
     LEFT("left"),
     TOP("top"),
     WIDTH("width");
     
     private final String keyName;     
     private FaceRectangle(String keyName){
         this.keyName = keyName;
     }
     public String value(){
         return keyName;
     }
}
