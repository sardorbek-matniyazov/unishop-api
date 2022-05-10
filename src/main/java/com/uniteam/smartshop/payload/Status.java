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
    public static final Status SUCCESS_UPDATE = new Status("Successfully updated", true, 200);
    public static final Status CANT_DELETE = new Status("The item wasn't delete cause of relationships", false, HttpStatus.BAD_REQUEST.value());
    public static final Status DELETED = new Status("Item deleted", true, HttpStatus.NOT_FOUND.value());
    public static final Status CLIENT_ALREADY_EXIST = new Status("Client already created take another name", false, HttpStatus.BAD_REQUEST.value());
    public static final Status DATA_ENTITY = new Status("The items found", true, 200);
    public static final Status ITEM_NOT_FOUND = new Status("Item hasn't found", false, HttpStatus.NOT_FOUND.value(), null);
    public static final Status CLIENT_NOT_FOUND = new Status("Client hasn't found", false, HttpStatus.NOT_FOUND.value(), null);;
    public static final Status CATEGORY_NOT_FOUND = new Status("Category not found", false, HttpStatus.BAD_REQUEST.value());
    public static final Status PHONE_NUMBER_TAKEN = new Status("this phone number has already taken", false, HttpStatus.BAD_REQUEST.value());
    public static final Status PRODUCTS_LIST_IS_NULL = new Status("the products list is null", false, HttpStatus.BAD_REQUEST.value());


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
