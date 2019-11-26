package iiitb.placement_portal.services;

import org.springframework.core.io.Resource;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import iiitb.placement_portal.exception.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.UrlResource;
@Service
public class StorageService {
	
	public boolean addFile(String fileName,MultipartFile file)
	{
		boolean result=true;
		try
		{
	        if (file.isEmpty()) {
	            throw new StorageException("Failed to store empty file");
	        }
            InputStream is = file.getInputStream();
            String path = "";
            if (System.getProperty("os.name").toLowerCase().contains("windows")) 
            	path = "C:\\placement_portal\\";
            else path = "/home/placement_portal/";
            
            Path dest=Paths.get(path+fileName);
            
//			log.debug(this.getClass() + " Saving " + fileName + " in " + path + " .");
            
            Files.copy(is, dest,
                    StandardCopyOption.REPLACE_EXISTING);
		}catch(Exception e)
		{
			result=false;
		      String msg = String.format("Failed to store file", file.getName());

	          throw new StorageException(msg, e);
		}
		return result;
	}
	
	public Resource loadFileAsResource(String fileName) {
        try {
        	Path fileStorageLocation= Paths.get("C:\\placement_portal\\")
                    .toAbsolutePath().normalize();
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}