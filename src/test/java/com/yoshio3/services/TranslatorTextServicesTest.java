/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yoshio3.services;

import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoterada
 */
public class TranslatorTextServicesTest {

    /**
     * Test of translateEnglish method, of class TranslatorTextServices.
     */
    @Test
    public void testTranslateEnglish() {
        System.out.println("translateEnglish");
        String englishText = "This is a pen";
        String translatedEnglish = "これはペンです。";

        TranslatorTextServices instance = new TranslatorTextServices();
        Optional<String> accessTokenForTranslator = instance.getAccessTokenForTranslator();
        String token = accessTokenForTranslator.orElseGet(() -> "");
        String result = instance.translateEnglish(englishText, token);
        assertEquals(translatedEnglish, result);
    }
}
