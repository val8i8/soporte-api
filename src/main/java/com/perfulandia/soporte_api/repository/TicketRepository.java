package com.perfulandia.soporte_api.repository;

import com.perfulandia.soporte_api.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByIdUsuario(Integer idUsuario);

}
