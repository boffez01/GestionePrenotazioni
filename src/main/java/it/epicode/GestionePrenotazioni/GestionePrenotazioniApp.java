package it.epicode.GestionePrenotazioni;

import it.epicode.GestionePrenotazioni.model.*;
import it.epicode.GestionePrenotazioni.repository.EdificioRepository;
import it.epicode.GestionePrenotazioni.repository.PostazioneRepository;
import it.epicode.GestionePrenotazioni.repository.PrenotazioneRepository;
import it.epicode.GestionePrenotazioni.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class GestionePrenotazioniApp implements CommandLineRunner {

    @Autowired
    private EdificioRepository edificioRepository;

    @Autowired
    private PostazioneRepository postazioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n*** Menu Gestione Prenotazioni ***");
            System.out.println("1. Visualizza edifici");
            System.out.println("2. Visualizza postazioni");
            System.out.println("3. Visualizza utenti");
            System.out.println("4. Aggiungi edificio");
            System.out.println("5. Aggiungi postazione");
            System.out.println("6. Aggiungi utente");
            System.out.println("7. Aggiungi prenotazione");
            System.out.println("8. Ricerca postazioni");
            System.out.println("9. Esci");
            System.out.print("Scegli un'opzione: ");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    visualizzaEdifici();
                    break;
                case 2:
                    visualizzaPostazioni(scanner);
                    break;
                case 3:
                    visualizzaUtenti();
                    break;
                case 4:
                    aggiungiEdificio(scanner);
                    break;
                case 5:
                    aggiungiPostazione(scanner);
                    break;
                case 6:
                    aggiungiUtente(scanner);
                    break;
                case 7:
                    aggiungiPrenotazione(scanner);
                    break;
                case 8:
                    ricercaPostazioni(scanner);
                    break;
                case 9:
                    System.out.println("Uscita dal programma...");
                    return;
                default:
                    System.out.println("Opzione non valida, riprova.");
            }
        }
    }

    private void visualizzaEdifici() {
        List<Edificio> edifici = edificioRepository.findAll();
        if (edifici.isEmpty()) {
            System.out.println("Nessun edificio trovato.");
        } else {
            System.out.println("\n*** Elenco edifici ***");
            for (Edificio edificio : edifici) {
                System.out.println("ID: " + edificio.getId() +
                        ", Nome: " + edificio.getNome() +
                        ", Indirizzo: " + edificio.getIndirizzo() +
                        ", Città: " + edificio.getCitta());
            }
        }
    }

    private void visualizzaPostazioni(Scanner scanner) {
        System.out.print("Inserisci il tipo di postazione (PRIVATO, OPENSPACE, SALA_RIUNIONI): ");
        String tipo = scanner.nextLine();
        System.out.print("Inserisci l'ID dell'edificio: ");
        Long edificioId = scanner.nextLong();
        scanner.nextLine();

        Optional<Edificio> edificio = edificioRepository.findById(edificioId);
        if (edificio.isPresent()) {
            List<Postazione> postazioni = postazioneRepository.findByTipoAndEdificio(TipoPostazione.valueOf(tipo), edificio.get());
            if (postazioni.isEmpty()) {
                System.out.println("Nessuna postazione trovata per il tipo e l'edificio selezionati.");
            } else {
                System.out.println("\n*** Elenco postazioni ***");
                for (Postazione postazione : postazioni) {
                    System.out.println("Codice: " + postazione.getId() +
                            ", Descrizione: " + postazione.getDescrizione() +
                            ", Tipo: " + postazione.getTipo() +
                            ", Max Occupanti: " + postazione.getNumeroMassimoOccupanti() +
                            ", Edificio: " + postazione.getEdificio().getNome());
                }
            }
        } else {
            System.out.println("Edificio non trovato.");
        }
    }

    private void visualizzaUtenti() {
        List<Utente> utenti = utenteRepository.findAll();
        if (utenti.isEmpty()) {
            System.out.println("Nessun utente trovato.");
        } else {
            System.out.println("\n*** Elenco utenti ***");
            for (Utente utente : utenti) {
                System.out.println("ID: " + utente.getId() + ", Username: " + utente.getUsername() + ", Email: " + utente.getEmail());
            }
        }
    }

    private void aggiungiEdificio(Scanner scanner) {
        System.out.print("Inserisci il nome dell'edificio: ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci l'indirizzo dell'edificio: ");
        String indirizzo = scanner.nextLine();
        System.out.print("Inserisci la città dell'edificio: ");
        String citta = scanner.nextLine();

        Edificio edificio = new Edificio(null, nome, indirizzo, citta, null);
        edificioRepository.save(edificio);
        System.out.println("Edificio aggiunto con successo!");
    }

    private void aggiungiPostazione(Scanner scanner) {
        System.out.print("Inserisci la descrizione della postazione: ");
        String descrizione = scanner.nextLine();
        System.out.print("Inserisci il tipo di postazione (PRIVATO, OPENSPACE, SALA_RIUNIONI): ");
        String tipo = scanner.nextLine();
        System.out.print("Inserisci il numero massimo di occupanti: ");
        int numeroMassimo = scanner.nextInt();
        System.out.print("Inserisci l'ID dell'edificio per la postazione: ");
        Long edificioId = scanner.nextLong();
        scanner.nextLine();

        Optional<Edificio> edificio = edificioRepository.findById(edificioId);
        if (edificio.isPresent()) {
            Postazione postazione = new Postazione(null, descrizione, TipoPostazione.valueOf(tipo), numeroMassimo, edificio.get());
            postazioneRepository.save(postazione);
            System.out.println("Postazione aggiunta con successo!");
        } else {
            System.out.println("Edificio non trovato.");
        }
    }

    private void aggiungiUtente(Scanner scanner) {
        System.out.print("Inserisci lo username dell'utente: ");
        String username = scanner.nextLine();
        System.out.print("Inserisci l'email dell'utente: ");
        String email = scanner.nextLine();

        Utente utente = new Utente(null, username, email);
        utenteRepository.save(utente);
        System.out.println("Utente aggiunto con successo!");
    }

    private void aggiungiPrenotazione(Scanner scanner) {
        System.out.print("Inserisci l'ID dell'utente: ");
        Long utenteId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Inserisci l'ID della postazione: ");
        Long postazioneId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Inserisci la data della prenotazione (yyyy-MM-dd): ");
        String data = scanner.nextLine();

        Optional<Utente> utente = utenteRepository.findById(utenteId);
        Optional<Postazione> postazione = postazioneRepository.findById(postazioneId);

        if (utente.isPresent() && postazione.isPresent()) {
            boolean success = prenotazioneRepository.existsByPostazioneAndDataPrenotazione(postazione.get(), LocalDate.parse(data));

            if (success) {
                System.out.println("Prenotazione non riuscita. La postazione è già prenotata.");
            } else {
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setUtente(utente.get());
                prenotazione.setPostazione(postazione.get());
                prenotazione.setDataPrenotazione(LocalDate.parse(data));
                prenotazioneRepository.save(prenotazione);
                System.out.println("Prenotazione avvenuta con successo!");
            }
        } else {
            System.out.println("Utente o Postazione non trovati.");
        }
    }

    private void ricercaPostazioni(Scanner scanner) {
        System.out.print("Inserisci il tipo di postazione (PRIVATO, OPENSPACE, SALA_RIUNIONI): ");
        String tipo = scanner.nextLine();
        System.out.print("Inserisci l'ID dell'edificio: ");
        Long edificioId = scanner.nextLong();
        scanner.nextLine();

        Optional<Edificio> edificio = edificioRepository.findById(edificioId);
        if (edificio.isPresent()) {
            List<Postazione> postazioni = postazioneRepository.findByTipoAndEdificio(TipoPostazione.valueOf(tipo), edificio.get());
            if (postazioni.isEmpty()) {
                System.out.println("Nessuna postazione trovata.");
            } else {
                System.out.println("\n*** Elenco postazioni trovate ***");
                for (Postazione postazione : postazioni) {
                    System.out.println("Codice: " + postazione.getId() +
                            ", Descrizione: " + postazione.getDescrizione() +
                            ", Tipo: " + postazione.getTipo() +
                            ", Max Occupanti: " + postazione.getNumeroMassimoOccupanti());
                }
            }
        } else {
            System.out.println("Edificio non trovato.");
        }
    }
}
