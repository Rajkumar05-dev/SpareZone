package com.learn.SpareZone.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadfile(MultipartFile file, String path) throws IOException {
	 @Nullable
	String originalFilename = file.getOriginalFilename();
	 String filename = UUID.randomUUID().toString();
	 String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
	String fileNameWithExtension= filename+extension;
	String fullPathWithFileName=path+fileNameWithExtension;
	 if(extension.equalsIgnoreCase(".png")||extension.equalsIgnoreCase(".jpg")||extension.equalsIgnoreCase(".jpeg")) {
		 File folder = new File(path);
		 if(!folder.exists()) {
			 folder.mkdirs();
		 }
		 Files.copy(file.getInputStream(),Paths.get(fullPathWithFileName));
	 }
		return fileNameWithExtension;
	}

	@SuppressWarnings("resource")
	@Override
	public InputStream getResource(String path, String name)throws FileNotFoundException {
String fullPath= path+File.separator+name;
FileInputStream inputStream = new FileInputStream(fullPath);
		return inputStream;
	}

}
