package com.asusoftware.ImageUpload.service;

import com.asusoftware.ImageUpload.model.Image;
import com.asusoftware.ImageUpload.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UploadFileService {

    private final ImageRepository imageRepository;

    public Image uploadFile(MultipartFile multipartFile)  {
        Image img = new Image();
        try {
            InputStream initialStream = multipartFile.getInputStream();
            UUID uuid = UUID.randomUUID();
            String pathName = "src/main/resources/images/" + uuid + ".txt";
            File targetFile = new File(pathName);
            java.nio.file.Files.copy(
                    initialStream,
                    targetFile.toPath()
            );
            IOUtils.closeQuietly(initialStream);
            // TODO: save pathName to database so after we can retrieve the particular image
            img.setLocalPath(pathName);
            imageRepository.save(img);
            return img;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (img.getId() != null) ? img : null;
    }

    public List<byte[]> getAllImages() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        List<InputStream> images = imageRepository.findAll().stream().map(image -> getClass().getResourceAsStream(image.getLocalPath())).collect(Collectors.toList());
        List<byte[]> imageList = images.stream().map(image -> ByteStreams)
        return IOUtils.toByteArray(images);
    }
}
