package application.back.simulation.state;

import application.back.simulation.items.BusSimulation;

public abstract class BusState implements IBusState {

            protected BusSimulation bus;

            public BusState(BusSimulation bus){
                 this.bus = bus;
            }

}
