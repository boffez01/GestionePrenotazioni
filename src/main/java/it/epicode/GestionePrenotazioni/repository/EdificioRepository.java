package it.epicode.GestionePrenotazioni.repository;

import it.epicode.GestionePrenotazioni.model.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EdificioRepository extends JpaRepository<Edificio, Long> {
}
