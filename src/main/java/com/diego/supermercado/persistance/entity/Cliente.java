package com.diego.supermercado.persistance.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {

    @Id
    @Column(name="id", length = 20)
    private String idCliente;

    @Column(name="nombre", length = 40)
    private String nombre;

    @Column(name="apellidos", length = 100)
    private String apellido;

    private Long celular;

    @Column(name="direccion", length = 80)
    private String direccion;

    @Column(name="correo_electronico", length = 70)
    private String email;

    public Cliente(String nombre, String apellido, Long celular, String direccion, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.direccion = direccion;
        this.email = email;
    }

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente='" + idCliente + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", celular=" + celular +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
