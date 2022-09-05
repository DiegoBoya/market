package com.diego.supermercado.persistance.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "compras")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Integer idCompra;

    @Column(name = "id_cliente", length = 20)
    private String idCliente;

    private LocalDateTime fecha;

    @Column(name = "medio_pago", length = 1)
    private String medioPago;

    @Column(length = 300)
    private String comentario;

    @Column(length = 1)
    private String estado;

    //Constructor con todos los parametros menos con el id_compra
    public Compra(String idCliente, LocalDateTime fecha, String medioPago, String comentario, String estado) {
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.medioPago = medioPago;
        this.comentario = comentario;
        this.estado = estado;
    }
}
