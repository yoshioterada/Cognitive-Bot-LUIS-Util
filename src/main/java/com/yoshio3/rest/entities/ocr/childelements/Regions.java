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
public class Regions {
    private String boundingBox;
    private List<Lines> lines;

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
     * @return the lines
     */
    public List<Lines> getLines() {
        return lines;
    }

    /**
     * @param lines the lines to set
     */
    public void setLines(List<Lines> lines) {
        this.lines = lines;
    }
}