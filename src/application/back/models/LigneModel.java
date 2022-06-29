package application.back.models;

import java.util.ArrayList;
import java.util.List;

public class LigneModel extends Model {

            private String nom;
            private List<ArretModel> arrets;

            public LigneModel(int id, String nom) {
                super(id);
                this.nom = nom;
                this.arrets = new ArrayList<>();
            }

            public void ajouteArret(ArretModel arret){
                   arrets.add(arret);
            }

            public void supprimeArret(ArretModel arret){
                   arrets.remove(arret);
            }

            public String ordreCroissant(){
                arrets.sort((a1 , a2) -> a1.getId() - a2.getId());
                return arrets.toString();
            }

            public String ordreDecroissant(){
                arrets.sort((a1 , a2) -> a2.getId() - a1.getId());
                return arrets.toString();
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


}
