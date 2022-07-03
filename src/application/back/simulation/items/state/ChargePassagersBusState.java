package application.back.simulation.items.state;

import application.back.models.BusModel;
import application.back.simulation.BusNotification;
import application.back.simulation.items.BusSimulation;

public class ChargePassagersBusState extends BusState{

    public ChargePassagersBusState(BusSimulation bus) {
        super(bus);
    }

    @Override
    public synchronized void display() {
        System.out.println("Le bus : "+bus.getId()+ " est affecté à la ligne: "+bus.getCurrentLigneId()+" Il Charge des passagers à l'arret: "+bus.getCurrentArretNom());
        BusModel busModel = bus.getModel();
        int arretID = bus.getIndexCurrentArret();
        bus.getNotifier().submit(new BusNotification(BusNotification.STATE_CHARGEMENT_PASSAGERS,arretID, "Arrêt "+arretID+" - Bus "+busModel.getId()));
    }

}
