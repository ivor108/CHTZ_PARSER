package ru.cloudcom.chtz.parser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Field extends ChtzObject{

	@JsonProperty("number")
	private String number;

	@JsonProperty("name")
	private String name;

	@JsonProperty("xpath")
	private String xpath;

	@JsonProperty("type")
	private String type;

}