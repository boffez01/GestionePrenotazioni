package it.epicode.GestionePrenotazioni.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Utente utente;

    @ManyToOne
    private Postazione postazione;

    private LocalDate dataPrenotazione;
}

//ciao