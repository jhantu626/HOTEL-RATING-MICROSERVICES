package io.app.controller;

import com.netflix.discovery.converters.Auto;
import io.app.payload.ApiResponse;
import io.app.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api/users")
public class FileController {
	@Autowired
	private FileService fileService;



	@PostMapping("/upload-profile")
	public ResponseEntity<ApiResponse> uploadProfilePicture(@RequestParam("image") MultipartFile file) throws IOException {
		return ResponseEntity.ok(fileService.addProfilePicture(file));
	}
}
