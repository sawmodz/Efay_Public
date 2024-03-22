package fr.eni.efay.bo;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable {
    private long category_id;
    private String name;
    private String icon;

    public Category() {
    }

    public Category(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public Category(long category_id, String name, String icon) {
        this.category_id = category_id;
        this.name = name;
        this.icon = icon;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return category_id == category.category_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(category_id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Category{");
        sb.append("category_id=").append(category_id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
