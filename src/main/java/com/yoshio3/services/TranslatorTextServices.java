package com.yoshio3.services;

import com.yoshio3.services.util.PropertyReaderService;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Translate English document to Japanese documents by using Microsoft
 * Translator Text API.
 *
 * @author Yoshio Terada
 */
public class TranslatorTextServices {

    private final static String OCP_APIM_SUBSCRIPTION_KEY = "Ocp-Apim-Subscription-Key";
    private final static String AUTH_URL = "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";
    private final static String TRANSLATOR_URL = "https://api.microsofttranslator.com/v2/http.svc/Translate";
    private final static String LANG_LIST = "https://api.microsofttranslator.com/v2/http.svc/GetLanguagesForTranslate";
    private final static String AUTH_ERROR_MESSAGE = "認証に失敗しました。アクセス・トークンが正しいか調べてください";
    private final static String ERROR_MESSAGE = "正しく翻訳ができませんでした。";
    private final static String SUBSCRIPTION_KEY;
    private final String accessToken;

    static {
        SUBSCRIPTION_KEY = PropertyReaderService.getPropertyValue("TRANSLATOR_SUBSCRIPTION_KEY");
    }

    public TranslatorTextServices() {
        Client client = ClientBuilder.newBuilder()
                .build();
        Entity<String> entity = Entity.entity("", MediaType.TEXT_PLAIN_TYPE);
        Response response = client.target(AUTH_URL)
                .request()
                .header(OCP_APIM_SUBSCRIPTION_KEY, SUBSCRIPTION_KEY)
                .post(entity);
        if (isRequestSuccess(response)) {
            this.accessToken = response.readEntity(String.class);
        } else {
            this.accessToken = null;
        }
    }

    /**
     * Translate from English to Japanese.
     *
     * The detail information to translate the document is as follows.
     * http://docs.microsofttranslator.com/text-translate.html
     *
     *
     * @param englishText The text value which you would like to translate.
     *
     * @return {@code String} Translated Japanese String
     */
    public String translateEnglishToJapanese(String englishText) {
        if (accessToken == null) {
            return AUTH_ERROR_MESSAGE;
        }
        StringBuilder builder = new StringBuilder();

        Client client = ClientBuilder.newBuilder()
                .build();
        Response response = client.target(TRANSLATOR_URL)
                .queryParam("text", englishText)
                .queryParam("from", "en")
                .queryParam("to", "ja")
                .queryParam("contentType", MediaType.TEXT_PLAIN)
                .request()
                .header("Accept", MediaType.APPLICATION_XML)
                .header("Authorization", "Bearer " + accessToken)
                .get();
        if (isRequestSuccess(response)) {
            String readEntity = response.readEntity(String.class);
            readEntity = readEntity.replaceAll("</?" + "string" + "/?>", "");
            readEntity = readEntity.replaceAll("<" + "string" + " [^>]*>", "");
            builder.append(readEntity);
        } else {
            builder.append(ERROR_MESSAGE);
        }

        return builder.toString();
    }

    public String getLangList() {
        Client client = ClientBuilder.newBuilder()
                .build();

        Response response = client.target(LANG_LIST)
                .queryParam("contentType", MediaType.TEXT_PLAIN)
                .request()
                .header("Accept", MediaType.APPLICATION_XML)
                .header("Authorization", "Bearer " + accessToken)
                .get();
        if (isRequestSuccess(response)) {
            String readEntity = response.readEntity(String.class);
            readEntity = readEntity.replaceAll("</?" + "ArrayOfstring" + "/?>", "");
            readEntity = readEntity.replaceAll("<" + "ArrayOfstring" + " [^>]*>", "");
            readEntity = readEntity.replaceAll("</?" + "string" + "/?>", ",");
            readEntity = readEntity.replaceAll(",,", ",");
            return readEntity;
        } else {
            return ERROR_MESSAGE;
        }
    }

    private boolean isRequestSuccess(Response response) {
        Response.StatusType statusInfo = response.getStatusInfo();
        Response.Status.Family family = statusInfo.getFamily();
        return family != null && family == Response.Status.Family.SUCCESSFUL;
    }
}
