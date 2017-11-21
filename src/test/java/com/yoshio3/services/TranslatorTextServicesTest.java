/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yoshio3.services;

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
        String result = instance.translateEnglishToJapanese(englishText);
        assertEquals(translatedEnglish, result);
        
        String langList = instance.getLangList();
        assertEquals(",af,ar,bn,bs-Latn,bg,ca,zh-CHS,zh-CHT,yue,hr,cs,da,nl,en,et,fj,fil,fi,fr,de,el,ht,he,hi,mww,hu,id,it,ja,sw,tlh,tlh-Qaak,ko,lv,lt,mg,ms,mt,yua,no,otq,fa,pl,pt,ro,ru,sm,sr-Cyrl,sr-Latn,sk,sl,es,sv,ty,ta,th,to,tr,uk,ur,vi,cy,", langList);
    }
}
