package application.back.models;
import application.back.models.reseau.INoeud;

public class Arret implements INoeud {

        private int id;
        private String nom;
        private int latitude,
                    longitude;

        public Arret(int id, String nom , int latitude, int longitude) {
            this.id = id;
            this.nom = nom;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String toString(){
            return "ID: "+id + " {Latitude: "+latitude+" Longitude: "+longitude+"} Nom: "+ nom + " \n";
        }

        @Override
        public String getInformation() {
            return null;
        }
}
