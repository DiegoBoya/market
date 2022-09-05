package com.diego.supermercado.persistance.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "compras_productos")
@Getter
@Setter
public class ComprasProducto {

    //este representa las 2 PK definidas en ComprasProductoPK
    @EmbeddedId
    private ComprasProductoPK id;

    private Integer cantidad;

    private BigDecimal total;

    private Boolean estado;
}
