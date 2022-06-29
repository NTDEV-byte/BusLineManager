package application.back.models;


public class ArretModel extends Model {

        private String nom;
        private int latitude,
                    longitude;

        public ArretModel(int id, String nom , int latitude, int longitude) {
            super(id);
            this.nom = nom;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String toString(){
            return "ID: "+id + " {Latitude: "+latitude+" Longitude: "+longitude+"} Nom: "+ nom + " \n";
        }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
}
