
package com.yoshio3.services;

import com.yoshio3.rest.entities.ocr.OCRResponseJSONBody;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoterada
 */
public class OCRServiceTest {
    

    /**
     * Test of getOCRAnalysisResult method, of class OCRService.
     */
    @Test
    public void testGetOCRAnalysisResult_String() {
        System.out.println("getOCRAnalysisResult");
        String pictURI = "https://jp.sansan.com/netacho/wp-content/uploads/2013/04/2555c449.jpg";
        OCRService instance = new OCRService();
        Optional<OCRResponseJSONBody> result = instance.getOCRAnalysisResult(pictURI);
        result.ifPresent((OCRResponseJSONBody response) -> {
            String ocrResultString = instance.getOCRResultString(response);
            System.out.println(ocrResultString);
            assertEquals("秒速であなたを必す成功に導きます!株式会社AKETCH・人を欺くための72の方法を体得済・計略と策謀の達人・信長の事を知り尽くしている・築城のことに造詣が深い・好物:ちまき代表取締役補佐明智光秀" , ocrResultString);
        });
    }
}
