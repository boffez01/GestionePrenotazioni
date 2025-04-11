package it.epicode.GestionePrenotazioni;

import it.epicode.GestionePrenotazioni.model.Edificio;
import it.epicode.GestionePrenotazioni.model.Postazione;
import it.epicode.GestionePrenotazioni.model.TipoPostazione;
import it.epicode.GestionePrenotazioni.model.Utente;
import it.epicode.GestionePrenotazioni.repository.EdificioRepository;
import it.epicode.GestionePrenotazioni.repository.PostazioneRepository;
import it.epicode.GestionePrenotazioni.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EdificioRepository edificioRepository;

    @Autowired
    private PostazioneRepository postazioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public void run(String... args) throws Exception {
        Edificio edificio1 = new Edificio(null, "Edificio A", "Via Roma 1", "Milano", null);
        edificioRepository.save(edificio1);

        Postazione postazione1 = new Postazione(null, "Postazione OpenSpace 1", TipoPostazione.OPENSPACE, 4, edificio1);
        Postazione postazione2 = new Postazione(null, "Postazione Privata 1", TipoPostazione.PRIVATO, 1, edificio1);
        postazioneRepository.save(postazione1);
        postazioneRepository.save(postazione2);

        Utente utente1 = new Utente(null, "mario", "mario@example.com");
        utenteRepository.save(utente1);

        System.out.println("Dati iniziali inseriti nel database.");
    }
}
