package com.bi.chankya.imagestorageservice.service;

import com.bi.chankya.imagestorageservice.entity.ImageData;
import com.bi.chankya.imagestorageservice.repository.StorageRepository;
import com.bi.chankya.imagestorageservice.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository storageRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImgae(file.getBytes()))
                .build();
        storageRepository.save(imageData);
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = storageRepository.findByName(fileName);
        return ImageUtils.decompressImage(dbImageData.get().getImageData());
    }
}
