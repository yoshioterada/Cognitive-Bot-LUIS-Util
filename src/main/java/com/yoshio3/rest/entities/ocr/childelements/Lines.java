/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yoshio3.rest.entities.ocr.childelements;

import java.util.List;

/**
 *
 * @author yoterada
 */
public class Lines{
    private String boundingBox;
    private List<Words> words;

    /**
     * @return the boundingBox
     */
    public String getBoundingBox() {
        return boundingBox;
    }

    /**
     * @param boundingBox the boundingBox to set
     */
    public void setBoundingBox(String boundingBox) {
        this.boundingBox = boundingBox;
    }

    /**
     * @return the words
     */
    public List<Words> getWords() {
        return words;
    }

    /**
     * @param words the words to set
     */
    public void setWords(List<Words> words) {
        this.words = words;
    }

}
