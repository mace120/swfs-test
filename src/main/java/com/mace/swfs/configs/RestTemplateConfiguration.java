package com.mace.swfs.configs;

import com.mace.swfs.interceptors.ProviderRequestResponseLoggingInterceptor;
import com.mace.swfs.services.ApiLogService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class RestTemplateConfiguration {

    @Value("${rest-template.read-timeout:30000}")
    private Integer readTimeout;

    @Value("${rest-template.connect-timeout:20000}")
    private Integer connectTimeout;

    @Value("${rest-template.retry.count:3}")
    private Integer retryCount;

    @Value("${rest-template.retry.enabled:true}")
    private Boolean retryEnabled;
    private final ApiLogService apiLogService;

    public RestTemplateConfiguration(ResourceLoader resourceLoader,
                                     ApiLogService apiLogService) {
        this.apiLogService = apiLogService;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        restTemplate.setInterceptors(Collections.singletonList(new ProviderRequestResponseLoggingInterceptor(apiLogService)));
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        return new BufferingClientHttpRequestFactory(getClientHttpRequestFactory());
    }

    private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        //Connect timeout
        clientHttpRequestFactory.setConnectTimeout(connectTimeout);
        clientHttpRequestFactory.setOutputStreaming(false);

        //Read timeout
        clientHttpRequestFactory.setReadTimeout(readTimeout);
        return clientHttpRequestFactory;
    }
}
