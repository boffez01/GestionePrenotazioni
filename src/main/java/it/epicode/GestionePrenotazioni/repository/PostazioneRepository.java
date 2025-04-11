package it.epicode.GestionePrenotazioni.repository;



import it.epicode.GestionePrenotazioni.model.Edificio;
import it.epicode.GestionePrenotazioni.model.Postazione;
import it.epicode.GestionePrenotazioni.model.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostazioneRepository extends JpaRepository<Postazione, Long> {
    List<Postazione> findByTipoAndEdificio(TipoPostazione tipo, Edificio edificio);
}
