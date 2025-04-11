package it.epicode.GestionePrenotazioni.repository;

import it.epicode.GestionePrenotazioni.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
}