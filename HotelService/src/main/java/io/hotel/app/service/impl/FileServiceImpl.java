package io.hotel.app.service.impl;

import io.hotel.app.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

	@Value("${project.image}")
	private String path;

	@Override
	public String uploadHotelProfilePic(MultipartFile file) throws IOException {
		File filePath=new File(path);
		if (!filePath.exists()){
			filePath.mkdir();
		}

		String uid= UUID.randomUUID().toString();
		String fullpath=path+"/"+uid+file.getOriginalFilename();

		FileOutputStream fileOutputStream=new FileOutputStream(fullpath);
		fileOutputStream.write(file.getBytes());
		fileOutputStream.flush();
		fileOutputStream.close();

		return uid+file.getOriginalFilename();
	}
}
