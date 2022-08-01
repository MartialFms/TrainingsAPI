package fr.fms.apitrainings.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public void init();
    public void save(MultipartFile file);
}
