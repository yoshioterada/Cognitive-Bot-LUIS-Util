/*
 * Copyright 2017 Yoshio Terada
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yoshio3.services.util;

import java.util.ResourceBundle;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Yoshio Terada
 */
@ApplicationScoped
public class PropertyReaderService {
    
    private final static String APP_RESOURCES = "app-resources";

    public static String getPropertyValue(String key) {
        ResourceBundle resources = ResourceBundle.getBundle(APP_RESOURCES);
        String value = "";
        if (resources.containsKey(key)) {
            value = resources.getString(key);
        }
        return value;   
    }
}
