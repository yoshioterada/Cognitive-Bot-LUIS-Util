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
package com.yoshio3.services;

import com.yoshio3.rest.entities.bot.childelements.Conversation;
import com.yoshio3.rest.entities.bot.childelements.From;
import com.yoshio3.rest.entities.bot.childelements.Members;
import com.yoshio3.rest.entities.bot.MessageBackToBotFramework;
import com.yoshio3.rest.entities.bot.MessageFromBotFrameWork;
import com.yoshio3.rest.entities.bot.childelements.Recipient;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Yoshio Terada
 */
public class BotService{

    private final static Logger LOGGER = Logger.getLogger(BotService.class.getName());
    private final static String COVERSATIONS = "/v3/conversations/";
    private final static String ACTIVITIES = "/activities";
    
    public void sendResponse(MessageFromBotFrameWork requestMessage, String accessToken, String message) {
        MessageBackToBotFramework creaeteResponseMessage = creaeteResponseMessage(requestMessage, message);
        LOGGER.log(Level.FINE, "Back Messages:{0}", creaeteResponseMessage.toString());
        Jsonb jsonb = JsonbBuilder.create(); 
        String jsonData = jsonb.toJson(creaeteResponseMessage);
        
        String serviceUrl = requestMessage.getServiceUrl();

        String id = requestMessage.getId();
        String convID = requestMessage.getConversation().getId();
        try {
            convID = URLEncoder.encode(convID, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            LOGGER.log(Level.SEVERE, "UTF-8 is not supported", ex);
        }
        String uri = serviceUrl + COVERSATIONS + convID + ACTIVITIES; // + id;

        Response response = ClientBuilder.newBuilder()
                .build()
                .target(uri)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken)
                .post(Entity.entity(jsonData, MediaType.APPLICATION_JSON));

        if (!isRequestSuccess(response)) {
            LOGGER.log(Level.SEVERE, "{0}:{1}", new Object[]{response.getStatus(), response.readEntity(String.class)});
            handleIllegalState(response);
        }
    }

    public MessageBackToBotFramework creaeteResponseMessage(MessageFromBotFrameWork requestMessage, String message) {
        MessageBackToBotFramework resMsg = new MessageBackToBotFramework();
        resMsg.setType("message");

        //// 2016-08-18T09:31:31.756Z (UTC Time)
        Instant instant = Instant.now();
        String currentUTC = instant.toString();
        resMsg.setTimestamp(currentUTC);

        From from = new From();
        from.setId(requestMessage.getRecipient().getId());
        from.setName(requestMessage.getRecipient().getName());
        resMsg.setFrom(from);

        Conversation conversation = new Conversation();
        conversation.setId(requestMessage.getConversation().getId());
        resMsg.setConversation(conversation);

        Recipient recipient = new Recipient();
        recipient.setId(requestMessage.getRecipient().getId());
        recipient.setName(requestMessage.getRecipient().getName());
        resMsg.setRecipient(recipient);

        Members member = new Members();
        member.setId(requestMessage.getFrom().getId());
        member.setName(requestMessage.getFrom().getName());
        Members[] members = new Members[1];
        members[0] = member;
        resMsg.setMembers(members);

        resMsg.setText(message);
        resMsg.setReplyToId(requestMessage.getId());
        return resMsg;
    }

    private boolean isRequestSuccess(Response response) {
        Response.StatusType statusInfo = response.getStatusInfo();
        Response.Status.Family family = statusInfo.getFamily();
        return family != null && family == Response.Status.Family.SUCCESSFUL;
    }

    /*
     Operate when the REST invocaiton failed.
     */
    private void handleIllegalState(Response response)
            throws IllegalStateException {
        String error = response.readEntity(String.class);
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, error);
        throw new IllegalStateException(error);
    }
}
