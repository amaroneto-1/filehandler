package br.com.agibank.typeprocessor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResultadoDTO {
    private String type;
    private String value;
}