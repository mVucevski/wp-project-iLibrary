package com.mvuchevski.ilibrary.services;

import com.mvuchevski.ilibrary.exceptions.ImageUploadException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.mvuchevski.ilibrary.AppConstants.FILE_PATH_IMG_STORAGE;

@Service
public class ImagesService {

    public String uploadImage(MultipartFile file) throws IOException {

        String originalName = file.getOriginalFilename();
        String name = file.getName();
        String contentType = file.getContentType();
        long size = file.getSize();

        System.out.println("originalName: " + originalName);
        System.out.println("name: " + name);
        System.out.println("contentType: " + contentType);
        System.out.println("size: " + size);

        if(!(contentType.equals("image/jpeg") || contentType.equals("image/png"))){
            throw new ImageUploadException("Please upload an jpeg or png image");
        }


        System.out.println("INN:--");
        InputStream is = file.getInputStream();

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        File a = new File(FILE_PATH_IMG_STORAGE);
        System.out.println("PATHSDHJKADS:" + a.getAbsolutePath());
        //System.out.println("CURRENCT PATH:" + s );

        Files.copy(is, Paths.get(FILE_PATH_IMG_STORAGE + originalName),
                StandardCopyOption.REPLACE_EXISTING);



        return (originalName);

    }

    public byte[] getImage(String name) throws IOException {

        File file = new File(FILE_PATH_IMG_STORAGE + name);
        //System.out.println("FILEEE:" + a.getAbsolutePath());

        if(file == null){
            throw new ImageUploadException("Not found!");
        }

        InputStream newInput = FileUtils.openInputStream(file);

        if(newInput == null){
            throw new ImageUploadException("Not found!");
        }

        return IOUtils.toByteArray(newInput);
    }
}
