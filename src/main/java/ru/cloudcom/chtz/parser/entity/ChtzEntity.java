package ru.cloudcom.chtz.parser.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import ru.cloudcom.chtz.parser.dto.ParserResponse;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "chtz")
@NoArgsConstructor
@Getter
public class ChtzEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "chtz")
    private String chtz;
    @Column(name = "version")
    private String version;
    @Column(name = "created")
    private LocalDateTime created;

    public ChtzEntity(ParserResponse parserResponse) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        this.id = parserResponse.getId();
        this.chtz = ow.writeValueAsString(parserResponse.getChtz());
        this.version = parserResponse.getVersion();
        this.created = LocalDateTime.parse(parserResponse.getCreated());
    }
}
