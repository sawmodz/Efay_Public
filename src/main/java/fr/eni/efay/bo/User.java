package fr.eni.efay.bo;


import java.io.Serializable;
import java.util.Objects;


public class User implements Serializable {
    private long id;
    private String name;
    private String surname;
    private String username;
    private String role;
    private String email;
    private long sold;
    private String password;
    private String address;

    public User() {
    }

    public User(String name, String surname, String username, String role, String email, long sold, String password, String address) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.role = role;
        this.email = email;
        this.sold = sold;
        this.password = password;
        this.address = address;
    }

    public User(long id, String name, String surname, String username, String role, String email, long sold, String password, String address) {
        this(name, surname, username, role, email, sold, password, address);
        this.id = id;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getSold() {
        return sold;
    }

    public void setSold(long sold) {
        this.sold = sold;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", sold=").append(sold);
        sb.append(", password='").append(password).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
