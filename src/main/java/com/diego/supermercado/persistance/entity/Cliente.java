package com.diego.supermercado.persistance.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cliente {

    @Id
    @Column(name="id", length = 20)
    private String idCliente;

    @Column(name="nombre", length = 40)
    private String nombre;

    @Column(name="apellidos", length = 100)
    private String apellido;

    private Integer celular;

    @Column(name="direccion", length = 80)
    private String direccion;

    @Column(name="correo_electronico", length = 70)
    private String email;

    public Cliente(String nombre, String apellido, Integer celular, String direccion, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.direccion = direccion;
        this.email = email;
    }
}
