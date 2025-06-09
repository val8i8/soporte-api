package com.perfulandia.soporte_api.dto;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private Integer idTicket;
    private Integer idUsuario;
    private String tipoTicket;
    private String descripcion;
    private String estado;
    private Date fechaCreacion;
    private Date fechaResolucion;

}
