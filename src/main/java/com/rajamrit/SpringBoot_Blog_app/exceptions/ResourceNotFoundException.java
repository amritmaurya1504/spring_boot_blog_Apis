package com.rajamrit.SpringBoot_Blog_app.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String resourceFieldName;
    long fieldValue;

    public ResourceNotFoundException(String resourceName, String resourceFieldName, long fieldValue){
        super(String.format("%s not found with %s : %s", resourceName, resourceFieldName, fieldValue));
        this.resourceName = resourceName;
        this.resourceFieldName = resourceFieldName;
        this.fieldValue = fieldValue;
    }
}
