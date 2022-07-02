package application.back.models;

import java.util.List;

public class ReseauModel {

        private String nomReseau;
        private List<LigneModel> lignes;

        public ReseauModel(String nomReseau, List<LigneModel> lignes) {
            this.nomReseau = nomReseau;
            this.lignes = lignes;
        }

        public String getNomReseau() {
            return nomReseau;
        }

        public void setNomReseau(String nomReseau) {
            this.nomReseau = nomReseau;
        }

        public List<LigneModel> getLignes() {
            return lignes;
        }

        public void setLignes(List<LigneModel> lignes) {
            this.lignes = lignes;
        }


        public String toString(){
           return "Nom: " +  nomReseau + "Lignes: "+lignes.toString();
        }
}
