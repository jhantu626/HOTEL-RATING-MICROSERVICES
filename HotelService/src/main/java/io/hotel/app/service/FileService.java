package io.hotel.app.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
	public String uploadHotelProfilePic(MultipartFile file) throws IOException;
}
