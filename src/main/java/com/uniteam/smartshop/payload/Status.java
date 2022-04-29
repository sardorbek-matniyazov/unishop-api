package com.uniteam.smartshop.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {
    public static final Status CATEGORY_ALREADY_EXISTS = new Status("Category has already created", false, HttpStatus.BAD_REQUEST.value());
    public static final Status SUCCESS_SAVED = new Status("Successfully saved", true, 200);
    public static final Status CANT_DELETE = new Status("The item wasn't delete cause of relationships", false, HttpStatus.BAD_REQUEST.value());
    public static final Status DELETED = new Status("Item deleted", true, HttpStatus.NOT_FOUND.value());

    private String message;
    private boolean active;
    private Integer status;
    private Object body;

    public Status(String message, boolean active, Integer status) {
        this.message = message;
        this.active = active;
        this.status = status;
    }
}
