package ru.cloudcom.chtz.parser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Step extends ChtzObject {

    @JsonProperty("number")
    private String number;

    @JsonProperty("name")
    private String name;

    @JsonProperty("blocks")
    private List<Block> blocks;

    public Block lastBlock() {
        return blocks.get(blocks.size() - 1);
    }

    public Step() {
        this.blocks = new ArrayList<Block>();
    }
}