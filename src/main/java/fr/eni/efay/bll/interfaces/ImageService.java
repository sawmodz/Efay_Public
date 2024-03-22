package fr.eni.efay.bll.interfaces;

import fr.eni.efay.bo.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public void insert (Image image);

    Image getById(long id);

    Image uploadImage(MultipartFile file);
}
