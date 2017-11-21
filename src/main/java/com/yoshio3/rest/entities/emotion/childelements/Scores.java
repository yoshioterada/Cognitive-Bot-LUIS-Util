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
public enum Scores {
//"anger":6.25079047E-05,"contempt":0.0006554612,"disgust":0.0001575452,"fear":1.02432514E-05,"happiness":4.513315E-05,"neutral":0.9630757,"sadness":0.0349147841,"surprise":0.00107860123}}    
//"anger":6.25079047E-05,"contempt":0.0006554612,"disgust":0.0001575452,"fear":1.02432514E-05,"happiness":4.513315E-05,"neutral":0.9630757,"sadness":0.0349147841,"surprise":0.00107860123}}    
    ANGER("anger"),
    CONTEMPT("contempt"),
    DISGUST("disgust"),
    FEAR("fear"),
    HAPPINESS("happiness"),
    NEUTRAL("neutral"),
    SADNESS("sadness"),
    SURPRISE("surprise");
    
    private final String keyName;
    private Scores(String keyName){
        this.keyName = keyName;
    }
    
    public String value(){
        return keyName;
    }
}
