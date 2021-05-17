package com.server.example.serverdemo.Api.Requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorItem {

    private Integer code;
    private String field;
    private String message;

    private String objectName;
    private String rejectedValue;

}
