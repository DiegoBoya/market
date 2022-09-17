package com.diego.supermercado.domain.dto;

import com.diego.supermercado.domain.dto.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private int productId;
    private String name;
    private int categoryId;
    private double price;
    private int stock;
    private boolean active;
    private Category category;

}
