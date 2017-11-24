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
package com.yoshio3.rest.entities.ocr.childelements;

import java.util.List;

/**
 *
 * @author yoterada
 */
public class Regions {
    private String boundingBox;
    private List<Lines> lines;

    /**
     * @return the boundingBox
     */
    public String getBoundingBox() {
        return boundingBox;
    }

    /**
     * @param boundingBox the boundingBox to set
     */
    public void setBoundingBox(String boundingBox) {
        this.boundingBox = boundingBox;
    }

    /**
     * @return the lines
     */
    public List<Lines> getLines() {
        return lines;
    }

    /**
     * @param lines the lines to set
     */
    public void setLines(List<Lines> lines) {
        this.lines = lines;
    }
}