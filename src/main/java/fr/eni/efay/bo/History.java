package fr.eni.efay.bo;

import java.time.LocalDate;

public class History {
    LocalDate date;
    long id;
    User user;
    Product product;


    public History(LocalDate date, long id, Product product_id, User user_id) {
        this(date, product_id, user_id);
        this.id = id;
    }

    public History(LocalDate date, Product product_id, User user_id) {
        this.date = date;
        this.product = product_id;
        this.user = user_id;
    }

    public History() {}

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
