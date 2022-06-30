package application.back.simulation.state;

import application.back.simulation.items.BusSimulation;

public class ChargePassagersBusState extends BusState{

    public ChargePassagersBusState(BusSimulation bus) {
        super(bus);
    }

    @Override
    public void display() {
        System.out.println(bus.toString()+ " est entrain de charger des passagers à l'arret: "+bus.getCurrentArret().toString());
    }

}
