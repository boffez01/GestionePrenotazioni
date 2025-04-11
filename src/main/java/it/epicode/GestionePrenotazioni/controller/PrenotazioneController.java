package it.epicode.GestionePrenotazioni.controller;

import it.epicode.GestionePrenotazioni.model.Postazione;
import it.epicode.GestionePrenotazioni.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping("/ricerca")
    public List<Postazione> ricercaPostazioni(@RequestParam String tipo, @RequestParam Long edificioId) {
        return prenotazioneService.ricercaPostazioni(tipo, edificioId);
    }

    @PostMapping("/prenota")
    public boolean prenotaPostazione(@RequestParam Long utenteId, @RequestParam Long postazioneId, @RequestParam String data) {
        return prenotazioneService.prenotaPostazione(utenteId, postazioneId, LocalDate.parse(data));
    }
}
