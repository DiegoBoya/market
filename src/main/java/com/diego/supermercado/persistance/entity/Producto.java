package com.diego.supermercado.persistance.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="productos")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Integer idProducto;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name="id_categoria")
    private Integer idCategoria;

    @Column(name="codigo_barras", length = 150)
    private String codigoBarras;

    @Column(name="precio_venta", length = 16)
    private Double precioVenta;

    @Column(name="cantidad_stock")
    private Integer cantidadStock;

    private Boolean estado;

    //constructor con todos los atributos pero sin Id
    public Producto(String nombre, Integer idCategoria, String codigoBarras, Double precioVenta, Integer cantidadStock, Boolean estado) {
        this.nombre = nombre;
        this.idCategoria = idCategoria;
        this.codigoBarras = codigoBarras;
        this.precioVenta = precioVenta;
        this.cantidadStock = cantidadStock;
        this.estado = estado;
    }
}
