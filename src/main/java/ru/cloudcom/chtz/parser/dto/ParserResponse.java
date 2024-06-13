package ru.cloudcom.chtz.parser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParserResponse{

	@JsonProperty("created")
	private String created;

	@JsonProperty("chtz")
	private Chtz chtz;

	@JsonProperty("id")
	private String id;

	@JsonProperty("version")
	private String version;
}