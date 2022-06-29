package application.back.models;

import application.back.models.reseau.INoeud;

import java.util.ArrayList;
import java.util.List;

public class LigneBus implements INoeud {

            private int id;
            private String nom;
            private List<Arret> arrets;

            public LigneBus(int id, String nom) {
                this.id = id;
                this.nom = nom;
                this.arrets = new ArrayList<>();
            }

            public void ajouteArret(Arret arret){
                   arrets.add(arret);
            }

            public void supprimeArret(Arret arret){
                   arrets.remove(arret);
            }

            public String toString(){
                return "Id: "+id +" ligne : "+nom+ " \n Arrêts: \n"+arrets.toString();
            }

            @Override
            public String getInformation() {
                return null;
            }
}
