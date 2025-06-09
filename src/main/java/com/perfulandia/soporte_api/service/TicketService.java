package com.perfulandia.soporte_api.service;

import com.perfulandia.soporte_api.dto.TicketDTO;
import com.perfulandia.soporte_api.model.Ticket;
import com.perfulandia.soporte_api.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    // Guarda un nuevo ticket en la base de datos
    public Ticket guardarTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    // Obtiene todos los tickets de un usuario específico 
    public List<TicketDTO> obtenerTicketsPorUsuario(Integer idUsuario) {
        return ticketRepository.findByIdUsuario(idUsuario)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Actualiza el estado de un ticket y devuelve el ticket actualizado 
    public TicketDTO actualizarEstadoTicket(Integer idTicket, String nuevoEstado) throws Exception {
        Optional<Ticket> optionalTicket = ticketRepository.findById(idTicket);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setEstado(nuevoEstado);
            Ticket actualizado = ticketRepository.save(ticket);
            return convertirADTO(actualizado);
        } else {
            throw new Exception("Ticket no encontrado");
        }
    }

    // Convierte un Ticket a TicketDTO
    private TicketDTO convertirADTO(Ticket ticket) {
        return new TicketDTO(
                ticket.getIdTicket(),
                ticket.getIdUsuario(),
                ticket.getTipoTicket(),
                ticket.getDescripcion(),
                ticket.getEstado(),
                ticket.getFechaCreacion(),
                ticket.getFechaResolucion()
        );
    }

    
   

}
