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
package com.yoshio3.rest.entities.emotion;

/**
 *
 * @author yoterada
 */
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author Yoshio Terada
 */
public class EmotionResponseJSONBody {

    private Map<String, Object> faceRectangle;
    private Map<String, BigDecimal> scores;

    /**
     * FaceRectangle.
     *
     * The key for the Map is FaceRectangle.HEIGHT.value();
     * FaceRectangle.LEFT.value() FaceRectangle.TOP.value()
     * FaceRectangle.WIDTH.value()
     *
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
     * The key for the Map is Score.ANGER.value() Score.CONTEMPT.value()
     * Score.DISGUST.value() Score.FEAT.value() Score.HAPPINESS.value()
     * Score.NEUTRAL.value() Score.SADNESS.value() Score.SURPRISE.value()
     *
     * @return the scores
     */
    public Map<String, BigDecimal> getScores() {
        return scores;
    }

    /**
     * @param scores the scores to set
     */
    public void setScores(Map<String, BigDecimal> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "EmotionResponseJSONBody{" + "faceRectangle=" + faceRectangle + ", scores=" + scores + '}';
    }

}
