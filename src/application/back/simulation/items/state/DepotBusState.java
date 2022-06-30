package application.back.simulation.items.state;

import application.back.simulation.items.BusSimulation;

public class DepotBusState extends BusState{

    public DepotBusState(BusSimulation bus) {
        super(bus);
    }


    @Override
    public void display() {
        System.out.println(bus.toString()+" est dans le dépot");
    }
}
