/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yoshio3.rest.entities.emotion;

/**
 *
 * @author yoterada
 */

/**
 *
 * @author Yoshio Terada
 */

public class EmotionRequestJSONBody {
    
    private String url ;

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "EmotionRequestJSONBody{" + "url=" + url + '}';
    }    
}
