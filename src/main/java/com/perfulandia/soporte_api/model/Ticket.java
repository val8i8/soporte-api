package com.perfulandia.soporte_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "tickets_soporte")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idTicket;

    @Column(name = "cliente_id")
    private Integer idUsuario;

    @Column(name = "tipo_ticket")
    private String tipoTicket;

    private String descripcion;

    private String estado;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "fecha_resolucion")
    private Date fechaResolucion;

}
