package org.scholanova.mealdeliverapi.application;

import org.scholanova.mealdeliverapi.domain.Commande.Commande;
import org.scholanova.mealdeliverapi.domain.Commande.EtatCommande;
import org.scholanova.mealdeliverapi.domain.ItemNourriture.ItemNourriture;
import org.scholanova.mealdeliverapi.infrastructure.Client.ClientRepository;
import org.scholanova.mealdeliverapi.infrastructure.Commande.CommandeRepository;
import org.scholanova.mealdeliverapi.infrastructure.Plat.repository.NourritureRepository;
import org.scholanova.mealdeliverapi.infrastructure.Restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CommandeController {

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    NourritureRepository nourritureRepository;

    @GetMapping("/commandes")
    public Iterable<Commande> listCommandes() {
        return commandeRepository.findAll();
    }


    @GetMapping("/commandes/{etat}")
    public List<Commande> listCommandesByEtat(@PathVariable("etat") String etatCommande) {
        List<Commande> commandes = new ArrayList<Commande>();
        for (Commande uneCommande : commandeRepository.findAll()) {
            if (uneCommande.getEtat().toString().equals(etatCommande)) {
                commandes.add(uneCommande);
            }
        }
        return commandes;
    }


    @GetMapping("/commandes/PRET")
    public List<Commande> listCommandesPrete() {
        List<Commande> commandes = new ArrayList<Commande>();
        for (Commande uneCommande : commandeRepository.findAll()) {
            if (uneCommande.getEtat().equals(EtatCommande.PRET)) {
                commandes.add(uneCommande);
            }
        }
        return commandes;
    }

    @GetMapping("/commandes/add/{id_client}/{id_restaurant}/{id_nourriture}/{date_livraison}/{couverts}")
    public void addCommande(@PathVariable("id_client") long id_client, @PathVariable("id_restaurant") long id_restaurant, @PathVariable("id_nourriture") long id_nourriture, @PathVariable("date_livraison") @DateTimeFormat(pattern = "dd-mm-yyyy") LocalDate date_livraison, @PathVariable("couverts") Boolean couverts) throws ParseException {

        Commande commande = new Commande();
        commande.setContenu(new ArrayList<ItemNourriture>());
        if (!isNull(id_client) && clientRepository.findById(id_client).get() != null) {
            commande.setClient(clientRepository.findById(id_client).get());
            if (!isNull(id_client) && restaurantRepository.findById(id_restaurant).get() != null) {
                commande.setRestaurant(restaurantRepository.findById(id_restaurant).get());
                if (!isNull(id_nourriture) && nourritureRepository.findById(id_nourriture).get() != null) {
                    commande.getContenu().add(nourritureRepository.findById(id_nourriture).get());
                    if (!isNull(date_livraison)) {
                        //Date date = new SimpleDateFormat("dd/mm/yyyy HH:mm").parse(date_livraison);
                        commande.setHeureLivraison(Date.valueOf(date_livraison));
                        if (couverts == true) {
                            commande.setCouvertPlastique(true);
                        } else {
                            commande.setCouvertPlastique(false);
                        }
                    }
                }
            }
        }
        commandeRepository.save(commande);
    }


    @GetMapping("/commandes/delete/{id_commande}")
    public void deleteCommande(@PathVariable("id_commande") Long id_commande) {
        commandeRepository.findById(id_commande).ifPresent(commande -> commandeRepository.delete(commande));
    }

    @GetMapping("/commandes/{id_commande}/modifyCouvert/{couvert}")
    public void addCouvert(@PathVariable("id_commande") Long id_commande, @PathVariable("couvert") Boolean couvert) {
        commandeRepository.findById(id_commande).ifPresent(commande -> {
            commande.setCouvertPlastique(couvert);
            commandeRepository.save(commande);
        });
    }

    @GetMapping("/commandes/{id_commande}/removePlat/{id_nourriture}")
    public void addCouvert(@PathVariable("id_commande") Long id_commande, @PathVariable("id_nourriture") Long id_nourriture) {
        commandeRepository.findById(id_commande).ifPresent(commande -> {
            commande.getContenu().forEach(plat -> {
                if (plat.getId().equals(id_nourriture)) {
                    commande.getContenu().remove(plat);
                    commandeRepository.save(commande);
                }});
        });
    }


    @GetMapping("/commandes/clear:{minutes}")
    public void deleteCommandeAfterNminutes(@PathVariable("int") int minutes) {


        Iterable<Commande> lesCommandes = commandeRepository.findAll();
        lesCommandes.forEach(cmd -> {

            LocalDateTime heure = LocalDateTime.ofInstant(cmd.getHeureLivraison().toInstant(),null);

            if (heure.plusMinutes(minutes).toLocalDate().isAfter(LocalDateTime.now().toLocalDate())) {
                commandeRepository.delete(cmd);
            }});
    }

    public Boolean isNull(Object object) {
        Boolean response;
        if (object == null) {
            response = true;
        } else {
            response = false;
        }
        return response;
    }


}
