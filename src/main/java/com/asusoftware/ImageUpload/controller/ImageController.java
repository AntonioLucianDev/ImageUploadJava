package com.asusoftware.ImageUpload.controller;

import com.asusoftware.ImageUpload.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/images")
@RequiredArgsConstructor
public class ImageController {

    private final UploadFileService uploadFileService;

    @PostMapping
    public void uploadImage(@RequestPart("file") MultipartFile file) {
        uploadFileService.uploadFile(file);
    }

    @GetMapping(path = "/all", produces = MediaType.IMAGE_JPEG_VALUE)
    public List<byte[]> getAllImages() {
        uploadFileService
    }
}
