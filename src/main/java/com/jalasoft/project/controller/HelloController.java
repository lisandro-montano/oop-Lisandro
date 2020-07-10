package com.jalasoft.project.controller;

import org.springframework.http.RequestEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author HP
 * @version 1.1
 */

@RestController
@RequestMapping("/hello")
public class HelloController {

    public static final String UPLOAD_FOLDER = "/Users/luigilm/Desktop/oop-uploads/";
    @PostMapping
    public String sayHello(@RequestParam(value = "name") String name,
                           @RequestParam(value = "lastName") String lastName,
                           @RequestParam(value = "file") MultipartFile file) {

        if (file.isEmpty()) {
            return "Please add a file to upload.";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Hello " + name + " " + lastName + ", you have successfully uploaded the file: '"
                + file.getOriginalFilename() + "'";
    }
}
