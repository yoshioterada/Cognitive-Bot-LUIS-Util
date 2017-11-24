/**
 * 
 * @author Yoshio Terada
 * 
 *         Copyright (c) 2017 Yoshio Terada
 * 
 *         Permission is hereby granted, free of charge, to any person obtaining
 *         a copy of this software and associated documentation files (the
 *         "Software"), to deal in the Software without restriction, including
 *         without limitation the rights to use, copy, modify, merge, publish,
 *         distribute, sublicense, and/or sell copies of the Software, and to
 *         permit persons to whom the Software is furnished to do so, subject to
 *         the following conditions:
 * 
 *         The above copyright notice and this permission notice shall be
 *         included in all copies or substantial portions of the Software.
 * 
 *         THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *         EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *         MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *         NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 *         BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 *         ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *         CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *         SOFTWARE.
 */
package com.yoshio3.rest.entities.bot;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yoshio Terada
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AccessTokenEntity {
    private String token_type;
    private Long expires_in;
    private Long ext_expires_in;
    private String access_token;

    /**
     * @return the token_type
     */
    public String getToken_type() {
        return token_type;
    }

    /**
     * @param token_type the token_type to set
     */
    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    /**
     * @return the expires_in
     */
    public Long getExpires_in() {
        return expires_in;
    }

    /**
     * @param expires_in the expires_in to set
     */
    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    /**
     * @return the ext_expires_in
     */
    public Long getExt_expires_in() {
        return ext_expires_in;
    }

    /**
     * @param ext_expires_in the ext_expires_in to set
     */
    public void setExt_expires_in(Long ext_expires_in) {
        this.ext_expires_in = ext_expires_in;
    }

    /**
     * @return the access_token
     */
    public String getAccess_token() {
        return access_token;
    }

    /**
     * @param access_token the access_token to set
     */
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Override
    public String toString() {
        return "AccessTokenEntity{" + "token_type=" + token_type + ", expires_in=" + expires_in + ", ext_expires_in=" + ext_expires_in + ", access_token=" + access_token + '}';
    }
}
