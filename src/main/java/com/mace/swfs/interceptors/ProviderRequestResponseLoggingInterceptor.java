package com.mace.swfs.interceptors;

import com.mace.swfs.services.ApiLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@AllArgsConstructor
@Slf4j
@Component
public class ProviderRequestResponseLoggingInterceptor implements ClientHttpRequestInterceptor {

    private ApiLogService apiLogService;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        Integer statusCode = null;
        String statusText = null;
        String responseHeaders = null;
        String responseBody;
        String requestBody = body.length > 0 ? new String(body, StandardCharsets.UTF_8) : null;

        apiLogService.logRequest(request, requestBody);

        try {
            ClientHttpResponse response = execution.execute(request, body);
            statusCode = response.getStatusCode().value();
            statusText = response.getStatusText();
            responseHeaders = Objects.requireNonNull(response.getHeaders()).toString();
            responseBody = Objects.requireNonNull(response.getBody()).toString();
            if (!responseHeaders.contains("multipart") &&
                    !responseHeaders.contains("application/pdf")) {
                responseBody = StreamUtils.copyToString(response.getBody(), Charset.defaultCharset());
            }

            apiLogService.logResponse(statusCode, statusText, responseHeaders, responseBody);
            apiLogService.saveLog(request, body, statusCode, statusText, responseHeaders, responseBody);
            return response;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            statusCode = e.getStatusCode().value();
            statusText = e.getStatusText();
            responseHeaders = Objects.requireNonNull(e.getResponseHeaders()).toString();
            responseBody = e.getResponseBodyAsString();
            e.printStackTrace();
        } catch (Exception e) {
            responseBody = e.getMessage();
            e.printStackTrace();
        }

        apiLogService.logResponse(statusCode, statusText, responseHeaders, responseBody);
        apiLogService.saveLog(request, body, statusCode, statusText, responseHeaders, responseBody);
        return null;
    }
}
