package com.mace.swfs.gateways.jubliee;

import com.mace.swfs.dtos.jubilee.JubileeRoleDto;
import com.mace.swfs.exceptions.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class JubileeApi {

    private final String host;
    private final RestTemplate restTemplate;

    public JubileeApi(
            @Value("${gateway.jubilee.api.host}") String host,
            RestTemplate restTemplate) {
        this.host = host;
        this.restTemplate = restTemplate;
    }

    /**
     * Call to `Get Roles` API
     */
    public List<JubileeRoleDto> getRoles() {
        String url = MessageFormat.format(JubileeApiConstants.GET_ROLES, host);

        HttpEntity<String> entity = new HttpEntity<>(getHttpHeaders());
        return (List<JubileeRoleDto>) getResponse(entity, url).orElseThrow(() ->
                new ApiException("Failed to process JUBILEE .getRoles api: " + url));
    }

    private Optional<Object> getResponse(HttpEntity<String> httpEntity, String url) {
        log.info("JubileeApi.getResponse - url:" + url + ", httpEntity:[" + httpEntity.toString() + "]");
        try {
            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Object.class);
            log.info("JubileeApi.getResponse - status:" + response.getStatusCode() + ", resp:[" + response + "]");
            if (response.getStatusCode() != HttpStatus.OK) {
                return Optional.empty();
            }
            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

}
