package jkorpela.fi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

/**
 * Created by KAI on 9/1/18.
 * Copyright 2018 by jkorpela-integrate
 * All rights reserved.
 */

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class JkorpelaBody {
    private String textline;
    private String datafile;

}
