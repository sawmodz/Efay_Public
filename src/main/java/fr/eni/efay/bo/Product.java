package fr.eni.efay.bo;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    private long product_id;
    private String name;
    private String description;
    private long price;
    private State state;
    private Category category_id;
    private User user_id;
    private Image image_id;

    public Product() {
    }

    public Product(String name, String description, long price, State state, Category category_id, User user_id, Image image_id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.state = state;
        this.category_id = category_id;
        this.user_id = user_id;
        this.image_id = image_id;
    }

    public Product(long product_id, String name, String description, long price, State state, Category category_id, User user_id, Image image_id) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.state = state;
        this.category_id = category_id;
        this.user_id = user_id;
        this.image_id = image_id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Image getImage_id() {
        return image_id;
    }

    public void setImage_id(Image image_id) {
        this.image_id = image_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return product_id == product.product_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("product_id=").append(product_id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", state=").append(state);
        sb.append(", category_id=").append(category_id);
        sb.append(", user_id=").append(user_id);
        sb.append(", image_id=").append(image_id);
        sb.append('}');
        return sb.toString();
    }
}
