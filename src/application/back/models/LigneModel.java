package application.back.models;

import java.util.ArrayList;
import java.util.List;

public class LigneModel extends Model {

            private String nom;
            private String couleur;
            private List<ArretModel> arrets;

            public LigneModel(int id, String nom) {
                super(id);
                this.nom = nom;
                this.arrets = new ArrayList<>();
            }

            public LigneModel(int id, String nom, String couleur) {
                super(id);
                this.nom = nom;
                this.couleur = couleur;
            }

            public void ajouteArret(ArretModel arret){
                   arrets.add(arret);
            }

            public void supprimeArret(ArretModel arret){
                   arrets.remove(arret);
            }


            public List<ArretModel> getArrets() {
                return arrets;
            }

            public void setArrets(List<ArretModel> arrets) {
                this.arrets = arrets;
            }

            public String toString(){
                        return "Id: "+id +" ligne : "+nom+ " \n Arrêts: \n"+arrets.toString();
                    }

            public String getCouleur() {
                return couleur;
            }

            public void setCouleur(String couleur) {
                this.couleur = couleur;
            }
}
