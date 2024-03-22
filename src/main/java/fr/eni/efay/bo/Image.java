package fr.eni.efay.bo;

import java.io.Serializable;
import java.util.Objects;

public class Image implements Serializable {
    private long image_id;

    public Image() {
    }

    public Image(long image_id) {
        this.image_id = image_id;
    }

    public long getImage_id() {
        return image_id;
    }

    public void setImage_id(long image_id) {
        this.image_id = image_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return image_id == image.image_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(image_id);
    }

}
