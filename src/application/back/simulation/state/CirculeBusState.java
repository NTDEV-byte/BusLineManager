package application.back.simulation.state;

import application.back.simulation.items.BusSimulation;

public class CirculeBusState extends BusState {


    public CirculeBusState(BusSimulation bus) {
        super(bus);
    }


    @Override
    public void display() {
        System.out.println(bus.toString()+ " circule entre l'arrêt "+bus.getCurrentArret().toString() + " et l'arrêt: "+ bus.getNextArret().toString());
    }
}
