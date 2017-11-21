package com.yoshio3.services;

import java.math.BigDecimal;
import java.util.Map;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import org.hamcrest.Matchers;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoterada
 */
public class EmotionDetectorServiceTest {

    /**
     * Test of getEmotionInfo method, of class EmotionDetectorService.
     */
    @Test
    public void testGetEmotionInfo() {
        System.out.println("getEmotionInfo");
        String fileURL = "https://thinkit.co.jp/sites/default/files/article_node/837002.jpg";
        EmotionDetectorService emotion = new EmotionDetectorService();
        String json = emotion.getEmotionInfo(fileURL);

        //"scores":{
        //"anger":6.25079047E-05,
        //"contempt":0.0006554612,
        //"disgust":0.0001575452,
        //"fear":1.02432514E-05,
        //"happiness":4.513315E-05,
        //"neutral":0.9630757,
        //"sadness":0.0349147841,
        //"surprise":0.00107860123}        
        Jsonb jsonb = JsonbBuilder.create();
        Map fromJson = jsonb.fromJson(json, Map.class);
        double sum = fromJson.keySet().stream()
                .mapToDouble((key) -> ((BigDecimal) fromJson.get(key)).doubleValue())
                .sum();
        System.out.println(sum);
        //Whether the total score become over 99% or not.
        assertThat(sum, (Matchers.greaterThanOrEqualTo(0.99D)));
    }
}
