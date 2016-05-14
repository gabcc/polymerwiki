package hu.gab.wiki.shared.dto;

import java.io.Serializable;

/**
 * @author PG
 * @since 2016-05-15
 */
public class DTO_NiceError implements Serializable {
    private String message;

    public DTO_NiceError() {
    }

    public DTO_NiceError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
