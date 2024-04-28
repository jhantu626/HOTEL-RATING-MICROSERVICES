package io.app.service;

import io.app.payload.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
	public ApiResponse addProfilePicture(MultipartFile file) throws IOException;

}
