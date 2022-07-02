package application.back.models;

import java.util.List;

public class ReseauModel {

        private String nomReseau;
        private List<LigneModel> lignes;
        private List<ArretCommun> arretsCommuns;


        public ReseauModel(String nomReseau, List<LigneModel> lignes, List<ArretCommun> arretsCommuns) {
            this.nomReseau = nomReseau;
            this.lignes = lignes;
            this.arretsCommuns = arretsCommuns;
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

    public List<ArretCommun> getArretsCommuns() {
        return arretsCommuns;
    }

    public void setArretsCommuns(List<ArretCommun> arretsCommuns) {
        this.arretsCommuns = arretsCommuns;
    }

    public static class ArretCommun {

            private int ligne1;
            private int ligne2;
            private int arret1;
            private int arret2;

            public ArretCommun(int ligne1, int ligne2, int arret1, int arret2) {
                this.ligne1 = ligne1;
                this.ligne2 = ligne2;
                this.arret1 = arret1;
                this.arret2 = arret2;
            }

            public String toString(){
                return "LigneIndex1: "+ ligne1 + " LigneIndex2: " + ligne2 + " ArretL1: "+ arret1 + "ArretL2: "+ arret2;
            }

        public int getLigne1() {
            return ligne1;
        }

        public int getLigne2() {
            return ligne2;
        }

        public int getArret1() {
            return arret1;
        }

        public int getArret2() {
            return arret2;
        }
    }
}
