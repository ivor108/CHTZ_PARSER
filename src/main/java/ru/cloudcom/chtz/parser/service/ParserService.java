package ru.cloudcom.chtz.parser.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;
import ru.cloudcom.chtz.parser.dto.Block;
import ru.cloudcom.chtz.parser.dto.Chtz;
import ru.cloudcom.chtz.parser.dto.Field;
import ru.cloudcom.chtz.parser.dto.Step;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ParserService {

    public String extractServiceName(InputStream inputStream) throws IOException {
        XWPFDocument document = new XWPFDocument(inputStream);
        var paragraphs = document.getParagraphs();
        return paragraphs.get(0).getText();
    }

    public String extractServiceCode(InputStream inputStream) throws IOException {
        XWPFDocument document = new XWPFDocument(inputStream);
        var paragraphs = document.getParagraphs();
        return paragraphs.get(1).getText();
    }

    public XWPFTable extractFirstTable(InputStream inputStream) throws IOException {
        XWPFDocument document = new XWPFDocument(inputStream);
        return document.getTables().get(0);
    }

    public Chtz parseTable(XWPFTable table) {
        Chtz chtz = new Chtz();
        chtz.setSteps(new ArrayList<>());
        for (XWPFTableRow row : table.getRows()) {
            String rowText = rowToString(row);
//            log.info(rowText);
            if (rowCheckType(row, "шаг")) {
                chtz.getSteps().add(rowToStep(row));
            } else if (rowCheckType(row, "блок")) {
                chtz.lastStep().getBlocks().add(rowToBlock(row));
            } else if (rowCheckType(row, "текст") || rowCheckType(row, "число")|| rowCheckType(row, "список")) {
                if (chtz.lastStep().getBlocks().isEmpty()) {
                    chtz.lastStep().getBlocks().add(new Block());
                }
                chtz.lastStep().lastBlock().getFields().add(rowToFiled(row));
            }
        }

        return chtz;
    }

    private Step rowToStep(XWPFTableRow row) {
        Step step = new Step();
        List<XWPFTableCell> cells = row.getTableCells();
        step.setNumber(cells.get(0).getText());
        step.setName(cells.get(1).getText());
        step.setIsVisiblePdf(cells.get(6).getText().trim());
        return step;
    }

    private Block rowToBlock(XWPFTableRow row) {
        Block block = new Block();
        List<XWPFTableCell> cells = row.getTableCells();
        block.setNumber(cells.get(0).getText());
        block.setName(cells.get(1).getText());
        block.setIsVisiblePdf(cells.get(6).getText().trim());
        return block;
    }

    private Field rowToFiled(XWPFTableRow row) {
        Field field = new Field();
        List<XWPFTableCell> cells = row.getTableCells();
        field.setNumber(cells.get(0).getText());
        field.setName(cells.get(1).getText());
        field.setXpath(cells.get(2).getText());
        field.setType(cells.get(3).getText());
        field.setIsVisiblePdf(cells.get(6).getText().trim());
        return field;
    }

    private Boolean rowCheckType(XWPFTableRow row, String type) {
        List<XWPFTableCell> cells = row.getTableCells();
        String cellType = cells.get(3).getText().trim().toLowerCase();
        return type.trim().equals(cellType.trim());
    }

    private String rowToString(XWPFTableRow row) {
        return row.getTableCells().stream().map(XWPFTableCell::getText).collect(Collectors.joining (", "));
    }
}
