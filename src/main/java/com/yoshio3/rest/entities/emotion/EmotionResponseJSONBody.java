/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
