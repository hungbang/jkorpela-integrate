package jkorpela.fi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by KAI on 9/1/18.
 * Copyright 2018 by jkorpela-integrate
 * All rights reserved.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentStatusResponse {

    @JsonProperty(value = "textline")
    public String textLine;
    public String datafile;
    public boolean block;
    private String reason;
    private String options;
    private String forcePoint;

}
