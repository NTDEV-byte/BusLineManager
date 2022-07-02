package application.back.simulation.items.state;

import application.back.models.BusModel;
import application.back.simulation.BusNotification;
import application.back.simulation.items.BusSimulation;

public class CirculeBusState extends BusState {

    public CirculeBusState(BusSimulation bus) {
        super(bus);
    }

    @Override
    public void display() {
        System.out.println("Le bus: "+bus.getId()+ "  est affecté à la ligne: "+bus.getCurrentLigneId() + " Il Circule entre l'arrêt Arrêt: "+bus.getCurrentArretNom() + " et l'arrêt: Arrêt "+ bus.getNextArretNom());
        BusModel busModel = bus.getModel();
        int arretID = bus.getIndexCurrentArret();
        bus.getNotifier().submit(new BusNotification(BusNotification.STATE_EN_CIRCULATION,arretID, "Bus "+busModel.getId()));
    }
}
