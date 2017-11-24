/**
 *
 * Copyright (c) 2017 Yoshio Terada
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
