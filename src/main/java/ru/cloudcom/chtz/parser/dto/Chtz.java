package ru.cloudcom.chtz.parser.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Chtz {

    @JsonProperty("service_name")
    private String serviceName;

    @JsonProperty("service_code")
    private String serviceCode;

    @JsonProperty("steps")
    private List<Step> steps;

    public Step lastStep() {
        return steps.get(steps.size() - 1);
    }
}