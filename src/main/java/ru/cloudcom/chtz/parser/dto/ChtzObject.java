package ru.cloudcom.chtz.parser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class ChtzObject {

    @JsonProperty("is_visible_pdf")
    private Boolean isVisiblePdf;

    public void setIsVisiblePdf(String visiblePdf) {
        if ("+".equals(visiblePdf)) {
            isVisiblePdf = Boolean.TRUE;
            return;
        }
        if ("-".equals(visiblePdf)) {
            isVisiblePdf = Boolean.FALSE;
            return;
        }
        isVisiblePdf = null;
    }
}
