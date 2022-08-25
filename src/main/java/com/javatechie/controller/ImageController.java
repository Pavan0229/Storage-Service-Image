package com.javatechie.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javatechie.service.StorageService;

@RestController
public class ImageController {
	
	@Autowired
	private StorageService imageService;
	
	@PostMapping("/image")
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException{
		String uploadImoage = imageService.uploadImoage(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImoage);
	}
	
	@GetMapping("/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
		byte[] downloadImage = imageService.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK)
							.contentType(MediaType.valueOf("image/png"))
							.body(downloadImage);
	}

}
