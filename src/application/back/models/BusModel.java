package application.back.models;

public class BusModel extends Model{

        private String modele;
        private String immatriculation;

        public BusModel(int id, String modele, String immatriculation) {
            super(id);
            this.modele = modele;
            this.immatriculation = immatriculation;
        }

        public String toString(){
            return "id: "+ id + " Modele: "+modele+" Immatriculation: "+immatriculation;
        }


        public String getModele() {
            return modele;
        }

        public void setModele(String modele) {
            this.modele = modele;
        }

        public String getImmatriculation() {
            return immatriculation;
        }

        public void setImmatriculation(String immatriculation) {
            this.immatriculation = immatriculation;
        }

}
