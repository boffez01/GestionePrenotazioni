package it.epicode.GestionePrenotazioni.service;

import it.epicode.GestionePrenotazioni.model.Edificio;
import it.epicode.GestionePrenotazioni.model.Postazione;
import it.epicode.GestionePrenotazioni.model.TipoPostazione;
import it.epicode.GestionePrenotazioni.model.Utente;
import it.epicode.GestionePrenotazioni.repository.EdificioRepository;
import it.epicode.GestionePrenotazioni.repository.PostazioneRepository;
import it.epicode.GestionePrenotazioni.repository.UtenteRepository;
import it.epicode.GestionePrenotazioni.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PrenotazioneService {

    @Autowired
    private PostazioneRepository postazioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private EdificioRepository edificioRepository;

    public List<Postazione> ricercaPostazioni(String tipo, Long edificioId) {
        Optional<Edificio> edificio = edificioRepository.findById(edificioId);
        if (edificio.isPresent()) {
            return postazioneRepository.findByTipoAndEdificio(TipoPostazione.valueOf(tipo), edificio.get());
        }
        return null;
    }

    public boolean prenotaPostazione(Long utenteId, Long postazioneId, LocalDate data) {
        Optional<Utente> utente = utenteRepository.findById(utenteId);
        Optional<Postazione> postazione = postazioneRepository.findById(postazioneId);

        if (utente.isPresent() && postazione.isPresent()) {
            if (prenotazioneRepository.existsByPostazioneAndDataPrenotazione(postazione.get(), data)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public List<Postazione> getAllPostazioni() {
        return postazioneRepository.findAll();
    }
}

