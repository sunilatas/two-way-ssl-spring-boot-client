package com.sunil.atas.twowaysslspringbootclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Hello implements Serializable {

    @JsonProperty("value")
    private String value;
}
