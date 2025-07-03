package com.perfulandia.soporte_api.controller;

import com.perfulandia.soporte_api.dto.TicketDTO;
import com.perfulandia.soporte_api.model.Ticket;
import com.perfulandia.soporte_api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*

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

    // GET para obtener un ticket por ID con HATEOAS
    @GetMapping("/{id}")
    public EntityModel<TicketDTO> obtenerTicketPorId(@PathVariable Integer id) {
        Optional<TicketDTO> ticketOpt = ticketService.obtenerTicketDTOporId(id);

        if (ticketOpt.isPresent()) {
            TicketDTO ticket = ticketOpt.get();
            EntityModel<TicketDTO> recurso = EntityModel.of(ticket);

            recurso.add(linkTo(methodOn(TicketController.class).obtenerTicketPorId(id)).withSelfRel());
            recurso.add(linkTo(methodOn(TicketController.class).listarTodosLosTickets()).withRel("todosLosTickets"));

            return recurso;
        } else {
            throw new RuntimeException("Ticket no encontrado con ID: " + id);
        }
    }

    // GET listar todos los tickets con HATEOAS
    @GetMapping
    public CollectionModel<EntityModel<TicketDTO>> listarTodosLosTickets() {
        List<TicketDTO> tickets = ticketService.obtenerTodosLosTicketsDTO();

        List<EntityModel<TicketDTO>> recursos = tickets.stream()
                .map(t -> {
                    EntityModel<TicketDTO> recurso = EntityModel.of(t);
                    recurso.add(linkTo(methodOn(TicketController.class).obtenerTicketPorId(t.getIdTicket())).withSelfRel());
                    return recurso;
                })
                .toList();

        CollectionModel<EntityModel<TicketDTO>> coleccion = CollectionModel.of(recursos);
        coleccion.add(linkTo(methodOn(TicketController.class).listarTodosLosTickets()).withSelfRel());

        return coleccion;
    }

}
