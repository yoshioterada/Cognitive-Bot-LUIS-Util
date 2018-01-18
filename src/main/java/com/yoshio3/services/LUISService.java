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

import com.yoshio3.services.util.PropertyReaderService;
import com.yoshio3.services.util.BasicRESTService;
import com.yoshio3.rest.entities.luis.ResponseFromLUIS;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Yoshio Terada
 */
public class LUISService extends BasicRESTService {

    private final static Logger LOGGER = Logger.getLogger(LUISService.class.getName());
    private final static String LUIS_SERVER_URL;

    static {
        LUIS_SERVER_URL = PropertyReaderService.getPropertyValue("LUIS_SERVER_URL");
    }

    public Optional<ResponseFromLUIS> getResponseFromLUIS(String inputString) {
        LOGGER.log(Level.INFO, "invokeLUIS query: {0}", inputString);
        try {
            inputString = URLEncoder.encode(inputString, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            LOGGER.log(Level.SEVERE, "UTF-8 is not supported", ex);
        }
        String url = LUIS_SERVER_URL + inputString;
        LOGGER.log(Level.INFO, "Request URL for LUIS : {0}", url);

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        Response response = target
                .request(MediaType.WILDCARD)
                .get();

        LOGGER.log(Level.FINE, "Response Status : {0}", response.getStatus());
        LOGGER.log(Level.FINE, "Response Family : {0}", response.getStatusInfo().getFamily().toString());

        JsonbConfig nillableConfig = new JsonbConfig()
                .withNullValues(true);
        Jsonb jsonb = JsonbBuilder.create(nillableConfig);
        if (isRequestSuccess(response)) {
            String jsonEntity = response.readEntity(String.class);
            LOGGER.log(Level.INFO, "Debug : \n {0}", jsonEntity);
            try {
                ResponseFromLUIS luis = jsonb.fromJson(jsonEntity, ResponseFromLUIS.class);
                System.out.println(luis);
                return Optional.of(luis);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            LOGGER.log(Level.INFO, "Debug : {0}", luis.toString());

        } else {
            handleIllegalState(response);
            LOGGER.log(Level.SEVERE, "Error : {0}", response.readEntity(String.class));
            return Optional.empty();
        }
            return Optional.empty();        
    }
}

/*


        EmotionResponseJSONBody[] persons;
        if (checkRequestSuccess(emotionRes)) {
            persons = jsonb.fromJson(emotionRes.readEntity(String.class), EmotionResponseJSONBody[].class);
        } else {
            return emotionRes.readEntity(String.class);
        }
*/
