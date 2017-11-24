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
package com.yoshio3.rest.entities.bot;

import com.yoshio3.rest.entities.bot.childelements.Recipient;
import com.yoshio3.rest.entities.bot.childelements.From;
import com.yoshio3.rest.entities.bot.childelements.Members;
import com.yoshio3.rest.entities.bot.childelements.Conversation;
import java.util.Arrays;

/**
 *
 * @author Yoshio Terada
 */
public class MessageBackToBotFramework {

    private String type;
    private String timestamp;
    private From from;
    private Conversation conversation;
    private Recipient recipient;
    private String text;
    private String replyToId;
    private Members[] members;

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the from
     */
    public From getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(From from) {
        this.from = from;
    }

    /**
     * @return the conversation
     */
    public Conversation getConversation() {
        return conversation;
    }

    /**
     * @param conversation the conversation to set
     */
    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    /**
     * @return the recipient
     */
    public Recipient getRecipient() {
        return recipient;
    }

    /**
     * @param recipient the recipient to set
     */
    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
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
     * @return the replyToId
     */
    public String getReplyToId() {
        return replyToId;
    }

    /**
     * @param replyToId the replyToId to set
     */
    public void setReplyToId(String replyToId) {
        this.replyToId = replyToId;
    }

    /**
     * @return the members
     */
    public Members[] getMembers() {
        return members;
    }

    /**
     * @param members the members to set
     */
    public void setMembers(Members[] members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "MessageBackToBotFramework{" + "type=" + type + ", timestamp=" + timestamp + ", from=" + from + ", conversation=" + conversation + ", recipient=" + recipient + ", text=" + text + ", replyToId=" + replyToId + ", members=" + Arrays.toString(members) + '}';
    }
}

