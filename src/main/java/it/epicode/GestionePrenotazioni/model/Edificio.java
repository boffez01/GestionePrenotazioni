package it.epicode.GestionePrenotazioni.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Edificio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String indirizzo;
    private String città;

    @OneToMany(mappedBy = "edificio")
    private List<Postazione> postazioni; // Un edificio ha più postazioni

    public String getCitta() {
        return "";
    }
}
