package com.lyc.domain;

import lombok.Data;

import java.io.Serializable;
@Data
public class ProductCategory implements Serializable {
    private Integer id;
    private String category_name;
    private Integer seller_id;
    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCategory_name() != null ? getCategory_name().hashCode() : 0);
        result = 31 * result + (getSeller_id() != null ? getSeller_id().hashCode() : 0);
        return result;
    }

    public ProductCategory() {
    }

    public ProductCategory(Integer id, String category_name, Integer seller_id) {
        this.id = id;
        this.category_name = category_name;
        this.seller_id = seller_id;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", category_name='" + category_name + '\'' +
                ", seller_id=" + seller_id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Integer getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Integer seller_id) {
        this.seller_id = seller_id;
    }
}
