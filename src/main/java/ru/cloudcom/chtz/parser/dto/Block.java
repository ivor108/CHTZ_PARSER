package ru.cloudcom.chtz.parser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Block extends ChtzObject {

    @JsonProperty("number")
    private String number;

    @JsonProperty("name")
    private String name;

    @JsonProperty("fields")
    private List<Field> fields;

    public Block() {
        this.fields = new ArrayList<Field>();
    }
}