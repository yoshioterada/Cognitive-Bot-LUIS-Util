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
package com.yoshio3.rest.entities.face;

/**
 *
 * @author yoterada
 */
import java.util.Map;


/**
 *
 * @author Yoshio Terada
 */
public class FaceDetectResponseJSONBody {
    private String faceId;
    private Map<String, Object> faceRectangle;
    private Map<String, Object> faceAttributes;

    /**
     * @return the faceId
     */
    public String getFaceId() {
        return faceId;
    }

    /**
     * @param faceId the faceId to set
     */
    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    /**
     * @return the faceRectangle
     */
    public Map<String, Object> getFaceRectangle() {
        return faceRectangle;
    }

    /**
     * @param faceRectangle the faceRectangle to set
     */
    public void setFaceRectangle(Map<String, Object> faceRectangle) {
        this.faceRectangle = faceRectangle;
    }

    /**
     * @return the faceAttributes
     */
    public Map<String, Object> getFaceAttributes() {
        return faceAttributes;
    }

    /**
     * @param faceAttributes the faceAttributes to set
     */
    public void setFaceAttributes(Map<String, Object> faceAttributes) {
        this.faceAttributes = faceAttributes;
    }

    @Override
    public String toString() {
        return "FaceDetectResponseJSONBody{" + "faceId=" + faceId + ", faceRectangle=" + faceRectangle + ", faceAttributes=" + faceAttributes + '}';
    }    
}
