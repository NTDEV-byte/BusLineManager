package application.back.simulation.items;

import application.back.models.BusModel;

import java.util.ArrayList;
import java.util.List;

public class DepotSimulation {

        private List<BusSimulation> bus;
        private String models[] = {"BMW" , "MERCEDES" ,"MAN"};
        private String immatriculations[] = {"XA124" , "ZA124" ,"MA124"};

        public DepotSimulation(){
                bus = new ArrayList<>();
                for(int i = 1; i <= 3 ; i++){
                 bus.add(new BusSimulation(new BusModel(i , models[i - 1] , immatriculations[i - 1])));
                }
        }


        public BusSimulation affecteBusAlALigneDuReseau(int busId, LigneSimulation ligne){
                if(bus.size() == 0){
                        System.err.println("Aucun bus n'est présent dans le dépôt !");
                        return null;
                }
                BusSimulation bus = findBusWithId(busId);
                if(bus == null){
                        System.err.println("Bus avec l'identifiant: "+busId+" n'existe pas !");
                        return null;
                }

                ligne.addBus(bus);
                bus.onSortieDuDepotAction(ligne);
                return bus; // retourne le bus qui en circulation
        }

        public BusSimulation affecteBusAlALigneDuReseauEtArret(int busId, LigneSimulation ligne, ArretSimulation arretDebut){
                if(bus.size() == 0){
                        System.err.println("Aucun bus n'est présent dans le dépôt !");
                        return null;
                }
                BusSimulation bus = findBusWithId(busId);
                if(bus == null){
                        System.err.println("Bus avec l'identifiant: "+busId+"  n'existe pas ! ");
                        return null;
                }

                ligne.addBus(bus);
                bus.onSortieDuDepotAction(ligne,arretDebut);

                return bus; // retourne le bus qui en circulation
        }

        public void enleveBusDeLaLigne(int busId, LigneSimulation ligne){
                if(bus.size() == 0){
                        System.err.println("Aucun bus n'est présent dans le dépôt");
                        return;
                }
        }

        private BusSimulation findBusWithId(int busId){
             for(BusSimulation b : this.bus){
                     BusModel model = (BusModel) b.getModel();
                     if(model.getId() == busId) return b;
             }
             return null;
        }

}
