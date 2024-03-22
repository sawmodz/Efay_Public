package fr.eni.efay.dal.interfaces;

import fr.eni.efay.bo.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageDAO {
    public void insert (Image image);

    Image getById(long id);

    Image uploadImage(MultipartFile file);
}
