package application.back.models.decorator;

import java.util.ArrayList;
import java.util.List;

public class Depot {


        private List<Bus> bus;

        public Depot(){
                bus = new ArrayList<>();
        }

        public void addBus(Bus bus){}
        public void removeBus(Bus bus){}

        public void affecterALigne(int idBus,LigneBus ligne){}
        public void affecterALigne(int idBus,LigneBus ligne,Arret debut){}

        public void enleverDeLigne(int idBus,LigneBus ligne){}


}
