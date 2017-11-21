/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yoshio3.rest.entities.ocr;

/**
 *
 * https://dev.projectoxford.ai/docs/services/56f91f2d778daf23d8ec6739/operations/56f91f2e778daf14a499e1fc
 * 
 * Response 400 系の場合
 * InvalidImageUrl : Image URL is badly formatted or not accessible.
 * InvalidImageFormat : Input data is not a valid image.
 * InvalidImageSize : Input image is too large.
 * NotSupportedLanguage : Specified language is not supported.
 * 
 * Response 500 系の場合
 * FailedToProcess : Failed to process the image.
 * Timeout : Image processing time out.
 * InternalServerError : Internal server error.
 * 
 * @author Yoshio Terada
 */
public class OCRResponseError {
    
    private String code;
    private String requestId;
    private String message;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * @param requestId the requestId to set
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
