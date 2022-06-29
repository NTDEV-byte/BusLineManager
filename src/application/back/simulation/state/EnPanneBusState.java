package application.back.simulation.state;

import application.back.simulation.items.BusSimulation;

public class EnPanneBusState extends BusState{

    public EnPanneBusState(BusSimulation bus) {
        super(bus);
    }


    @Override
    public void display() {
        System.out.println(bus.toString()+" est en panne");
    }
}
