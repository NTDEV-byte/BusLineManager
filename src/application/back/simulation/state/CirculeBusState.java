package application.back.simulation.state;

import application.back.simulation.items.BusSimulation;

public class CirculeBusState extends BusState {


    public CirculeBusState(BusSimulation bus) {
        super(bus);
    }


    @Override
    public void display() {
        System.out.println(bus.toString()+ " circule entre l'arrêt "+bus.getArretPresent().toString() + "et l'arrêt: "+1);
    }
}
