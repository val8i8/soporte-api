package com.perfulandia.soporte_api.controller;

import com.perfulandia.soporte_api.dto.TicketDTO;
import com.perfulandia.soporte_api.model.Ticket;
import com.perfulandia.soporte_api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soporte/tickets")

public class TicketController {
    @Autowired
    private TicketService ticketService;

    // Endpoint para guardar un nuevo ticket
    @PostMapping
    public Ticket guardarTicket(@RequestBody Ticket ticket) {
        return ticketService.guardarTicket(ticket);
    }

    // Endpoint para obtener todos los tickets de un usuario específico
    @GetMapping("/cliente/{idUsuario}")
    public List<TicketDTO> obtenerTicketsPorUsuario(@PathVariable Integer idUsuario) {
        return ticketService.obtenerTicketsPorUsuario(idUsuario);
    }

    // Endpoint para actualizar el estado de un ticket
    @PutMapping("/{idTicket}/estado")
    public TicketDTO actualizarEstadoTicket(@PathVariable Integer idTicket, @RequestParam String nuevoEstado) throws Exception {
        return ticketService.actualizarEstadoTicket(idTicket, nuevoEstado);
    }

}
