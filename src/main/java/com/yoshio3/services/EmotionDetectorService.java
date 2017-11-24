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

import com.yoshio3.rest.entities.emotion.EmotionRequestJSONBody;
import com.yoshio3.rest.entities.emotion.EmotionResponseJSONBody;
import com.yoshio3.rest.entities.emotion.childelements.Scores;
import com.yoshio3.services.util.PropertyReaderService;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Yoshio Terada
 */
public class EmotionDetectorService implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(EmotionDetectorService.class.getName());
    private static final String BASE_URI = "https://westus.api.cognitive.microsoft.com/emotion/v1.0/recognize";
    private final static String SUBSCRIPTION_KEY;

    static {
        SUBSCRIPTION_KEY = PropertyReaderService.getPropertyValue("EMOTIONAL_API_SUBSCRIPTION_KEY");
    }

    public String getEmotionInfo(String fileURL) {
        try {
            String file = URLDecoder.decode(fileURL, "UTF-8");
            Future<Response> responseForEmotion = getEmotionalInfo(fileURL);
            Response emotionRes = responseForEmotion.get();
            return jobForEmotion(emotionRes);
            //{"faceRectangle":{"height":133,"left":326,"top":147,"width":133},
            //  "scores":{"anger":6.25079047E-05,"contempt":0.0006554612,"disgust":0.0001575452,"fear":1.02432514E-05,"happiness":4.513315E-05,"neutral":0.9630757,"sadness":0.0349147841,"surprise":0.00107860123}}]
        } catch (InterruptedException | ExecutionException | UnsupportedEncodingException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return "";
    }

    private String jobForEmotion(Response emotionRes) {

        JsonbConfig nillableConfig = new JsonbConfig()
                .withNullValues(true);
        Jsonb jsonb = JsonbBuilder.create(nillableConfig);

        EmotionResponseJSONBody[] persons;
        if (checkRequestSuccess(emotionRes)) {
            persons = jsonb.fromJson(emotionRes.readEntity(String.class), EmotionResponseJSONBody[].class);
        } else {
            return emotionRes.readEntity(String.class);
        }
        //現在は一人のみ解析処理
        EmotionResponseJSONBody emotionalPerson = persons[0];
        Map<String, BigDecimal> scores = emotionalPerson.getScores();


        //感情の情報を取得
        JsonObject value = Json.createObjectBuilder()
                .add("anger", scores.get(Scores.ANGER.value()))
                .add("contempt", scores.get(Scores.CONTEMPT.value()))
                .add("disgust", scores.get(Scores.DISGUST.value()))
                .add("fear", scores.get(Scores.FEAR.value()))
                .add("happiness", scores.get(Scores.HAPPINESS.value()))
                .add("neutral", scores.get(Scores.NEUTRAL.value()))
                .add("sadness", scores.get(Scores.SADNESS.value()))
                .add("surprise", scores.get(Scores.SURPRISE.value()))
                .build();
        return value.toString();
    }
    
    /*
    REST 呼び出し成功か否かの判定
     */
    private boolean checkRequestSuccess(Response response) {
        Response.StatusType statusInfo = response.getStatusInfo();
        Response.Status.Family family = statusInfo.getFamily();
        return family != null && family == Response.Status.Family.SUCCESSFUL;
    }

    public Future<Response> getEmotionalInfo(String pictURI) throws InterruptedException, ExecutionException {
        Client client = ClientBuilder.newBuilder()
                .build();
        WebTarget target = client.target(BASE_URI);

        EmotionRequestJSONBody entity = new EmotionRequestJSONBody();
        entity.setUrl(pictURI);

        Future<Response> response = target
                .request(MediaType.APPLICATION_JSON)
                .header("Ocp-Apim-Subscription-Key", SUBSCRIPTION_KEY)
                .async()
                .post(Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE));
        return response;
    }
}
