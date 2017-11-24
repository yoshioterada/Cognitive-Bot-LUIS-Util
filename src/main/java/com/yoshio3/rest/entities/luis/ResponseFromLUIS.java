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
package com.yoshio3.rest.entities.luis;

import com.yoshio3.rest.entities.luis.childelements.TopScoringIntent;
import com.yoshio3.rest.entities.luis.childelements.Intent;
import com.yoshio3.rest.entities.luis.childelements.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yoshio Terada
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseFromLUIS{

private String query;
private TopScoringIntent topScoringIntent;
private Intent[] intents;
private Entity[] entities;

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * @return the topScoringIntent
     */
    public TopScoringIntent getTopScoringIntent() {
        return topScoringIntent;
    }

    /**
     * @param topScoringIntent the topScoringIntent to set
     */
    public void setTopScoringIntent(TopScoringIntent topScoringIntent) {
        this.topScoringIntent = topScoringIntent;
    }

    /**
     * @return the intents
     */
    public Intent[] getIntents() {
        return intents;
    }

    /**
     * @param intents the intents to set
     */
    public void setIntents(Intent[] intents) {
        this.intents = intents;
    }

    /**
     * @return the entities
     */
    public Entity[] getEntities() {
        return entities;
    }

    /**
     * @param entities the entities to set
     */
    public void setEntities(Entity[] entities) {
        this.entities = entities;
    }

    @Override
    public String toString() {
        return "ResponseFromLUIS{" + "query=" + query + ", topScoringIntent=" + topScoringIntent + ", intents=" + intents + ", entities=" + entities + '}';
    }



} 
