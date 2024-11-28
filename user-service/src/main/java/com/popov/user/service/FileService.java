package com.popov.user.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String upload(MultipartFile file);

    void delete(String file);

}
