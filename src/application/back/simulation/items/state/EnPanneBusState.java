package application.back.simulation.items.state;

import application.back.models.BusModel;
import application.back.simulation.BusNotification;
import application.back.simulation.items.BusSimulation;

public class EnPanneBusState extends BusState{

    public EnPanneBusState(BusSimulation bus) {
        super(bus);
    }


    @Override
    public synchronized void display() {
        System.out.println("Le bus: " + bus.getId() + "  est en panne sur la ligne " + bus.getCurrentLigneId());
        BusModel busModel = bus.getModel();
        int arretID = bus.getIndexCurrentArret();
        bus.getNotifier().submit(new BusNotification(BusNotification.STATE_BUS_EN_PANNE,arretID, "Bus "+busModel.getId()));
    }
}
