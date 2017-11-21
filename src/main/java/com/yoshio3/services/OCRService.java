package com.yoshio3.services;

import com.yoshio3.rest.entities.ocr.OCRRequestJSONBody;
import com.yoshio3.rest.entities.ocr.OCRResponseJSONBody;
import com.yoshio3.rest.entities.ocr.childelements.Lines;
import com.yoshio3.rest.entities.ocr.childelements.Regions;
import com.yoshio3.rest.entities.ocr.childelements.Words;
import com.yoshio3.services.util.PropertyReaderService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Yoshio Terada
 */
public class OCRService {

    private final static String BASE_URI = "https://westus2.api.cognitive.microsoft.com/vision/v1.0/ocr?language=unk&detectOrientation=true";

    private final Invocation.Builder invokeBuilder;
    private final static String SUBSCRIPTION_KEY;

    static {
        SUBSCRIPTION_KEY = PropertyReaderService.getPropertyValue("OCR_SUBSCRIPTION_KEY");
    }    
    
    /* Constructor */
    public OCRService() {
        Client client = ClientBuilder.newBuilder().build();
        WebTarget target = client.target(BASE_URI);
        invokeBuilder = target.request(MediaType.APPLICATION_JSON)
                .header("Ocp-Apim-Subscription-Key", SUBSCRIPTION_KEY);
    }

    /* 
      Get the result of OCR from specified URI
     */
    public Optional<OCRResponseJSONBody> getOCRAnalysisResult(String pictURI) {
        OCRRequestJSONBody entity = new OCRRequestJSONBody();
        entity.setUrl(pictURI);
        Response response = invokeBuilder.post(Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE));
        if (checkRequestSuccess(response)) {
            OCRResponseJSONBody result = response.readEntity(OCRResponseJSONBody.class);
            return Optional.of(result);
        } else {
            printErrorMessage(response);
            return Optional.empty();
        }
    }

    /* 
      Get the result of OCR from file
     */
    public Optional<OCRResponseJSONBody> getOCRAnalysisResult(Path path) throws IOException {
        byte[] readAllBytes = Files.readAllBytes(path);
        return getOCRAnalysisResult(readAllBytes);
    }

    /* 
      Get the result of OCR from binary data
     */
    public Optional<OCRResponseJSONBody> getOCRAnalysisResult(byte[] binaryImage) throws IOException {
        Response response = invokeBuilder.post(Entity.entity(binaryImage, MediaType.APPLICATION_OCTET_STREAM_TYPE));
        if (checkRequestSuccess(response)) {
            OCRResponseJSONBody result = response.readEntity(OCRResponseJSONBody.class);
            return Optional.of(result);
        } else {
            printErrorMessage(response);
            return Optional.empty();
        }
    }

    public String getOCRResultString(OCRResponseJSONBody body) {
        String language = body.getLanguage();
        String orientation = body.getOrientation();
        String textAngle = body.getTextAngle();
        
        StringBuilder builder = new StringBuilder();

        List<Regions> regions = body.getRegions();
        regions.stream().forEach(region -> {
            List<Lines> lines = region.getLines();
            lines.stream().forEach(line -> {
                List<Words> words = line.getWords();
                words.stream().forEach(word -> {
                    String text = word.getText();
                    builder.append(text);
                });
                builder.append("");
            });
        });
        return builder.toString();
    }    
    
    /* 
      Print out the Result of OCR to STDOUT 
     */
    public void printOCRResult(OCRResponseJSONBody body) {
        String language = body.getLanguage();
        String orientation = body.getOrientation();
        String textAngle = body.getTextAngle();
        

        List<Regions> regions = body.getRegions();
        regions.stream().forEach(region -> {
            List<Lines> lines = region.getLines();
            lines.stream().forEach(line -> {
                List<Words> words = line.getWords();
                words.stream().forEach(word -> {
                    String text = word.getText();
                    System.out.print(text);
                });
                System.out.println("");
            });
        });
    }

    /* 
      Print out the Result of OCR to STDOUT 
     */
    private void printErrorMessage(Response response) {
        String error = response.readEntity(String.class);
        Logger.getLogger(OCRService.class.getName()).log(Level.SEVERE, "{0}", error);
    }

    /*
      Evaluate the response 
    true : SUCESS
    false : FAILED
    */
    private boolean checkRequestSuccess(Response response) {
        Response.StatusType statusInfo = response.getStatusInfo();
        Response.Status.Family family = statusInfo.getFamily();
        return family != null && family == Response.Status.Family.SUCCESSFUL;
    }

}
