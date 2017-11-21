/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yoshio3.rest.entities.ocr;

import com.yoshio3.rest.entities.ocr.childelements.Regions;
import java.util.List;

/**
 *
 * @author Yoshio Terada
 */

public class OCRResponseJSONBody {
    private String language;
    private String textAngle;
    private String orientation;
    private List<Regions> regions;

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the textAngle
     */
    public String getTextAngle() {
        return textAngle;
    }

    /**
     * @param textAngle the textAngle to set
     */
    public void setTextAngle(String textAngle) {
        this.textAngle = textAngle;
    }

    /**
     * @return the orientation
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * @param orientation the orientation to set
     */
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    /**
     * @return the regions
     */
    public List<Regions> getRegions() {
        return regions;
    }

    /**
     * @param regions the regions to set
     */
    public void setRegions(List<Regions> regions) {
        this.regions = regions;
    }

}


