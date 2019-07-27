package com.seoulit.util.files;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class FileUtil {
    	
	public List<FilesTO> parseFileInfo(MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
		if(ObjectUtils.isEmpty(multipartHttpServletRequest)){
			return null;
		}
		
		List<FilesTO> fileList = new ArrayList<>();
				
		//파일명 
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd"); 
    	ZonedDateTime current = ZonedDateTime.now();
    	String path = "E:/DEV/FILES/"+current.format(format);
    	File file = new File(path);
		if(file.exists() == false){
			file.mkdirs();
		}
		
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		String newFileName, contentType;
		
		while(iterator.hasNext()){
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
			for (MultipartFile multipartFile : list){
				if(multipartFile.isEmpty() == false){
					contentType = multipartFile.getContentType();
					if(ObjectUtils.isEmpty(contentType)){
						break;
					}

					
					newFileName = Long.toString(System.nanoTime());
					FilesTO filesTO = new FilesTO();
					filesTO.setOriginalFileName(multipartFile.getOriginalFilename());
                    filesTO.setFileSize(multipartFile.getSize());
                    filesTO.setStoredFile(newFileName);
                    filesTO.setFileUrl(path);
					fileList.add(filesTO);
					file = new File(path + "/" + newFileName);
					multipartFile.transferTo(file);
				}
			}
		}
		return fileList;
	}
}