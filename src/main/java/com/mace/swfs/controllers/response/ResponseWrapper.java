package com.mace.swfs.controllers.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseWrapper {
    private Object data;
    private Object error;
    private String message;
    @Builder.Default
    private Integer httpStatusCode = HttpStatus.OK.value();
    @Builder.Default
    private Boolean isSuccess = true;
}
