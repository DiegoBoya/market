package com.diego.supermercado.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {

    private int categoryId;
    private String name;
    private boolean active;

}
