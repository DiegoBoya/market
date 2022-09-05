package com.diego.supermercado.persistance.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;

    @Column(length = 45)
    private String descripcion;

    private Boolean estado;

    public Categoria ( String descripcion, Boolean estado){
        this.descripcion = descripcion;
        this.estado = estado;
    }
}
