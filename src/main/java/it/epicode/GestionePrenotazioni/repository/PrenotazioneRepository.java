package it.epicode.GestionePrenotazioni.repository;

import it.epicode.GestionePrenotazioni.model.Prenotazione;
import it.epicode.GestionePrenotazioni.model.Postazione;
import it.epicode.GestionePrenotazioni.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.Optional;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByPostazioneAndDataPrenotazione(Postazione postazione, LocalDate data);
}