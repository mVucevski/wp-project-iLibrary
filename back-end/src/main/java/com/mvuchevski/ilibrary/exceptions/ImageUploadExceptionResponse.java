package com.mvuchevski.ilibrary.exceptions;

public class ImageUploadExceptionResponse {

    private String imageFile;

    public ImageUploadExceptionResponse(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
}
