/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yoshio3.services;

import com.yoshio3.rest.entities.face.FaceDetectRequestJSONBody;
import com.yoshio3.rest.entities.face.FaceDetectResponseJSONBody;
import com.yoshio3.services.util.PropertyReaderService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
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
public class FaceDetectorService implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(FaceDetectorService.class.getName());
    private static final String BASE_URI
            = "https://southeastasia.api.cognitive.microsoft.com/face/v1.0/detect?returnFaceId=true&returnFaceLandmarks=false&returnFaceAttributes=age,gender";

    private final static String FACE_API_SUBSCRIPTION;

    static {
        FACE_API_SUBSCRIPTION = PropertyReaderService.getPropertyValue("FACE_API_SUBSCRIPTION");
    }    
    
    /*
        対応ォーマット： JPEG, PNG, GIF(最初のフレーム), BMP
        画像サイズ： 4MB 以下
        検知可能な顔のサイズ：36x36 〜 4096x4096
        一画像辺り検知可能な人数：64 名
        指定可能な属性オプション(まだ実験的不正確)：
            age, gender, headPose, smile and facialHair, and glasses
            HeadPose の pitch 値は 0 としてリザーブ
     */

    public String getFaceInfo(String pictURI)
            throws InterruptedException, ExecutionException {
        Client client = ClientBuilder.newBuilder()
                .build();

        WebTarget target = client.target(BASE_URI);
        FaceDetectRequestJSONBody entity = new FaceDetectRequestJSONBody();
        entity.setUrl(pictURI);

        Future<Response> futureResponse = target
                .request(MediaType.APPLICATION_JSON)
                .header("Ocp-Apim-Subscription-Key", FACE_API_SUBSCRIPTION)
                .async()
                .post(Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE));
        Response response = futureResponse.get();
//        return response.readEntity(String.class);
        //[
        //{"faceId":"de0cd25d-7834-46cc-9fce-9c757f71eb1c",
        //"faceRectangle":{"top":147,"left":326,"width":133,"height":133},
        //"faceAttributes":{"gender":"male","age":33.4}}]

        
        return jobForFace(response);
    }

    private String jobForFace(Response faceRes) {
        FaceDetectResponseJSONBody[] persons = null;
        if (checkRequestSuccess(faceRes)) {
            persons = faceRes.readEntity(FaceDetectResponseJSONBody[].class);
        } else {
            return faceRes.readEntity(String.class);
        }
        //現在は一人のみ解析処理
        FaceDetectResponseJSONBody faceDetectData = persons[0];

        //年齢、性別を取得
        Map<String, Object> faceAttributes = faceDetectData.getFaceAttributes();
        BigDecimal age =  (BigDecimal)faceAttributes.get("age");
        String gender = (String) faceAttributes.get("gender"); 
        
        JsonObject value = Json.createObjectBuilder()
                .add("age", age)
                .add("gender", gender)
                .build();
        return value.toString();
    }

    /*
     REST 呼び出し成功か否かの判定
     */
    protected boolean checkRequestSuccess(Response response) {
        Response.StatusType statusInfo = response.getStatusInfo();
        Response.Status.Family family = statusInfo.getFamily();
        return family != null && family == Response.Status.Family.SUCCESSFUL;
    }
}

