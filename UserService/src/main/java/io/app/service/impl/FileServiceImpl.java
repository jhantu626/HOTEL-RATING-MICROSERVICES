package io.app.service.impl;

import io.app.payload.ApiResponse;
import io.app.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{
	@Value("${project.image}")
	private String folderPath;
	@Override
	public ApiResponse addProfilePicture(MultipartFile file) throws IOException {
		String originalFileName=file.getOriginalFilename();
		String uniqueId=UUID.randomUUID().toString();
		String fullPath=folderPath+uniqueId+originalFileName;

		File path=new File(folderPath);
		if (!path.exists()){
			path.mkdir();
		}

		Files.copy(file.getInputStream(), Paths.get(fullPath));



		return ApiResponse.builder()
				.msg("The file Name exists: "+uniqueId+originalFileName)
				.success(true)
				.build();
	}
}
