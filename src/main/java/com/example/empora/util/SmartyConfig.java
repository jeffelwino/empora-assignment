package com.example.empora.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SmartyConfig {


    private String key;


    private String authId = "9615ba1a-9bd9-09b0-9b94-4a17111be131";


    private String authToken = "x9zzOXZr6Xq29gSwEbhm";

    private String apiURL = "https://us-street.api.smartystreets.com/street-address?";

    @Bean
    public String getKey() {
        return this.key;
    }
    @Bean
    public String getAuthId() {
        return this.authId;
    }
    @Bean
    public String getAuthToken() {
        return this.authToken;
    }
    @Bean
    public String getApiURL() {
        return this.apiURL;
    }
}
