package com.yoshio3.services;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoterada
 */
public class FaceDetectorServiceTest {
    
    /**
     * Test of getFaceInfo method, of class FaceDetectorService.
     */
    @Test
    public void testGetFaceInfo() throws Exception {
        System.out.println("getFaceInfo");
        String pictURI = "https://thinkit.co.jp/sites/default/files/article_node/837002.jpg";
        FaceDetectorService faceDetect = new FaceDetectorService();
        String result = faceDetect.getFaceInfo(pictURI);
        assertEquals("{\"age\":33.4,\"gender\":\"male\"}", result);
    }
}
