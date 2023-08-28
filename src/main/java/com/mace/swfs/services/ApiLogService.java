package com.mace.swfs.services;

import com.mace.swfs.persistance.entities.ApiLog;
import com.mace.swfs.persistance.repositories.ApiLogRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@AllArgsConstructor
@Slf4j
@Service
public class ApiLogService {
    private ApiLogRepository apiLogRepository;

    public void logRequest(HttpRequest request, String requestBody) throws IOException {
        log.info("===========================request SME_API begin===========================");
        log.info("URI         : {}", request.getURI());
        log.info("Method      : {}", request.getMethod());
        log.info("Headers     : {}", request.getHeaders());
        log.info("Request body: {}", requestBody);
        log.info("===========================request SME_API end===========================");

    }

    public void logResponse(Integer statusCode, String statusText, String responseHeaders,
                            String responseBody) {
        log.info("===========================response SME_API begin===========================");
        log.info("Status code  : {}", statusCode);
        log.info("Status text  : {}", statusText);
        log.info("Headers      : {}", responseHeaders);
        log.info("Response body: {}", responseBody);
        log.info("===========================response SME_API end===========================");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void saveLog(HttpRequest request, byte[] body, Integer statusCode, String statusText, String responseHeaders,
                        String responseBody) {
        ApiLog apiLog = ApiLog.builder()
                .uri(request.getURI().toString())
                .method(request.getMethodValue())
                .requestHeaders(request.getHeaders().toString())
                .requestBody(
                        body.length > 0 ? new String(body, StandardCharsets.UTF_8)
                                : null)
                .statusCode(statusCode)
                .statusText(statusText)
                .responseHeaders(responseHeaders)
                .responseBody(responseBody)
                .creationDate(LocalDateTime.now())
                .build();
        apiLogRepository.save(apiLog);
    }
}
