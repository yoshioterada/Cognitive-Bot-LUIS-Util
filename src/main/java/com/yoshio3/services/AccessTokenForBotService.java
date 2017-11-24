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

import com.yoshio3.rest.entities.bot.AccessTokenEntity;
import com.yoshio3.services.util.PropertyReaderService;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;

/**
 *
 * @author Yoshio Terada
 */
public class AccessTokenForBotService {

    private final static Logger LOGGER = Logger.getLogger(AccessTokenForBotService.class.getName());
    
    private final static String GRANT_TYPE = "client_credentials";
    private final static String BOT_APP_CLIENT_ID;
    private final static String BOT_APP_CLIENT_SECRET;
    private final static String SCOPE = "https://api.botframework.com/.default";
    private final static String LOGIN_SERVER = "https://login.microsoftonline.com/botframework.com/oauth2/v2.0/token";
    private final static int EXPIRE_SEC = 3600;
    
    static {
        BOT_APP_CLIENT_ID = PropertyReaderService.getPropertyValue("BOT_APP_CLIENT_ID");
        BOT_APP_CLIENT_SECRET = PropertyReaderService.getPropertyValue("BOT_APP_CLIENT_SECRET");
    }

    private static String accessToken;
    private static LocalDateTime oldDate;

    public String getAccesToken() {
        if (accessToken == null) {
            createToken();
        } else {
            LocalDateTime newDate = LocalDateTime.now();
            Duration duration = Duration.between(oldDate, newDate);
            long expireTime = duration.getSeconds();
            LOGGER.log(Level.FINE, "\tEXPIRE TIME : {0}", expireTime);             

            if(expireTime > EXPIRE_SEC){
                createToken();
            }
        }
        return accessToken;
    }

    private String createToken() {
        accessToken = getAccesTokenFromServer().getAccess_token();
        oldDate = LocalDateTime.now();        
        LOGGER.log(Level.FINE, "CREATED or REFRESHED Token : {0}\tDate: {1}", new Object[]{accessToken, oldDate});
        return accessToken;
    }

    private static AccessTokenEntity getAccesTokenFromServer() {
        Client client = ClientBuilder.newBuilder()
                .build();

        MultivaluedHashMap<String, String> formdata = new MultivaluedHashMap<>();
        formdata.putSingle("grant_type", GRANT_TYPE);
        formdata.putSingle("client_id", BOT_APP_CLIENT_ID);
        formdata.putSingle("client_secret", BOT_APP_CLIENT_SECRET);
        formdata.putSingle("scope", SCOPE);

        WebTarget target = client
                .target(LOGIN_SERVER);
        Response response = target
                .request(MediaType.APPLICATION_FORM_URLENCODED)
                .post(Entity.form(formdata));
        Response.StatusType statusInfo = response.getStatusInfo();
        Response.Status.Family family = statusInfo.getFamily();

        AccessTokenEntity readEntity;
        if (family == Response.Status.Family.SUCCESSFUL) {
            String jsonData = response.readEntity(String.class); 
            Jsonb jsonb = JsonbBuilder.create();            
            AccessTokenEntity tokenEntity = jsonb.fromJson(jsonData, AccessTokenEntity.class);
            return tokenEntity;
        } else {
            return null;
        }
    }
}
