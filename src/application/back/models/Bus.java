package application.back.models;

import application.back.models.reseau.INoeud;

public class Bus implements INoeud {

        private int id;
        private String modele;
        private String immatriculation;

        public Bus(int id, String modele, String immatriculation) {
            this.id = id;
            this.modele = modele;
            this.immatriculation = immatriculation;
        }

        public String toString(){
            return "id: "+ id + " Modele: "+modele+" Immatriculation: "+immatriculation;
        }

        @Override
        public String getInformation() {
            return null;
        }
}
