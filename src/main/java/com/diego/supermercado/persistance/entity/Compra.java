package com.diego.supermercado.persistance.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "compras")
@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "compra", cascade = {CascadeType.ALL})
    private List<ComprasProducto> productos;

    //Constructor con todos los parametros menos con el id_compra
    public Compra(String idCliente, LocalDateTime fecha, String medioPago, String comentario, String estado) {
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.medioPago = medioPago;
        this.comentario = comentario;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "idCompra=" + idCompra +
                ", idCliente='" + idCliente + '\'' +
                ", fecha=" + fecha +
                ", medioPago='" + medioPago + '\'' +
                ", comentario='" + comentario + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
