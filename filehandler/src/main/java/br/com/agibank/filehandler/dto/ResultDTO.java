package br.com.agibank.filehandler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {
    private String type;
    private String value;

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(String.valueOf(obj));
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(this.type);
    }
}