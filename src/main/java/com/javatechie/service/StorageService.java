package com.javatechie.service;

import com.javatechie.entity.ImageData;
import com.javatechie.repository.StrorageRepository;
import com.javatechie.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StrorageRepository strorageRepository;


    public String uploadImoage(MultipartFile file) throws IOException {
    ImageData imageData =   strorageRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());

    if (imageData != null) {
        return "file saved Successfully : " + file.getOriginalFilename();
    }
    return null;
    }

    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = strorageRepository.findByName(fileName);
        byte[] images = ImageUtil.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
