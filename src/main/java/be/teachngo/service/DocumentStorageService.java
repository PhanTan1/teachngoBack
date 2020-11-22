package be.teachngo.service;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentStorageService {


    String storeFile(MultipartFile file, Integer userId, String docType);
}
