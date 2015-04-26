package com.alexeinunez.dropwizard.swagger;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

public class SwaggerConfiguration {

    @Getter
    @NotNull
    @JsonProperty
    private String host = "localhost";

}
