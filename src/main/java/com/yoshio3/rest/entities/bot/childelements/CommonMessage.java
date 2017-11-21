/*
 * Copyright 2017 Yoshio Terada
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yoshio3.rest.entities.bot.childelements;


/**
 *
 * @author Yoshio Terada
 */
public class CommonMessage {
    private String mid;
    private Long seq;
    private String text;
    private Boolean is_echo;

    /**
     * @return the mid
     */
    public String getMid() {
        return mid;
    }

    /**
     * @param mid the mid to set
     */
    public void setMid(String mid) {
        this.mid = mid;
    }

    /**
     * @return the seq
     */
    public Long getSeq() {
        return seq;
    }

    /**
     * @param seq the seq to set
     */
    public void setSeq(Long seq) {
        this.seq = seq;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the is_echo
     */
    public Boolean getIs_echo() {
        return is_echo;
    }

    /**
     * @param is_echo the is_echo to set
     */
    public void setIs_echo(Boolean is_echo) {
        this.is_echo = is_echo;
    }
    
}
