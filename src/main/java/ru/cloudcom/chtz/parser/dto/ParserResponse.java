package ru.cloudcom.chtz.parser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.cloudcom.chtz.parser.entity.ChtzEntity;

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

	public ParserResponse(ChtzEntity chtzEntity) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		this.created = chtzEntity.getCreated().toString();
		this.chtz = objectMapper.readValue(chtzEntity.getChtz(), Chtz.class);
		this.id = chtzEntity.getId();
		this.version = chtzEntity.getVersion();
	}
}