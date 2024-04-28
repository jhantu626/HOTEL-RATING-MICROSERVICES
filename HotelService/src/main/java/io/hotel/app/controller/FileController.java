package io.hotel.app.controller;

import io.hotel.app.service.FileService;
import io.hotel.app.service.impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/hotels")
public class FileController {
	@Autowired
	private FileServiceImpl fileService;

	@PostMapping("/upload-profile")
	public ResponseEntity<String> uploadHotelProfilePic(@RequestParam("image") MultipartFile file) throws IOException {
		return ResponseEntity.ok(fileService.uploadHotelProfilePic(file));
	}
}
