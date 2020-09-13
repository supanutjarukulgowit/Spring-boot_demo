package com.example.stock_backend.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.stock_backend.exception.StorageException;

@Service
public class StorageServiceImpl implements StorageService {

	@Value("${app.upload.path:images}") // after : is default value
	private String path;
	private Path rootlocation;

	@Override
	public void init() { // initial folder path
		this.rootlocation = Paths.get(path);
		try {
			Files.createDirectories(rootlocation); // create folder if doesn't exist
		} catch (IOException ex) {
			throw new StorageException("Could not init storage" + ex.getMessage());
		}
	}

	@Override
	public String store(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			return null;
		}

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				throw new StorageException("Path file is outside directory.");
			}

			fileName = UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);

			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, this.rootlocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
				return fileName;
			}
		} catch (IOException ex) {
			throw new StorageException("Failed to store file : " + fileName + ", " + ex.getMessage());
		}

	}

}
