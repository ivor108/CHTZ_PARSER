package ru.cloudcom.chtz.parser.controller;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.cloudcom.chtz.parser.dto.Chtz;
import ru.cloudcom.chtz.parser.dto.ParserResponse;
import ru.cloudcom.chtz.parser.entity.ChtzEntity;
import ru.cloudcom.chtz.parser.repository.ChtzRepository;
import ru.cloudcom.chtz.parser.service.ParserService;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@Api(tags = "chtzParser")
public class ParserController {

    @Autowired
    ParserService parserService;

    @Autowired
    ChtzRepository chtzRepository;

    @ApiOperation(value = "Распарсить ЧТЗ и сохранить в БД", consumes = "multipart/form-data", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ЧТЗ успешно обработано"),
            @ApiResponse(code = 400, message = "Неверный формат запроса"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ParserResponse saveChtz(@RequestPart("chtzFile") MultipartFile chtzFile) throws IOException {

        ParserResponse response = new ParserResponse();

        XWPFTable table = parserService.extractFirstTable(chtzFile.getInputStream());
        Chtz parsedChtz = parserService.parseTable(table);
        parsedChtz.setServiceName(parserService.extractServiceName(chtzFile.getInputStream()));
        parsedChtz.setServiceCode(parserService.extractServiceCode(chtzFile.getInputStream()));

        response.setChtz(parsedChtz);
        response.setId(UUID.randomUUID().toString());
        response.setVersion("1.0");
        response.setCreated(LocalDateTime.now().toString());
        chtzRepository.save(new ChtzEntity(response));

        return response;
    }
}