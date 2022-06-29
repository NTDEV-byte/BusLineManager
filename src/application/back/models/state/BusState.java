package application.back.models.state;

import application.back.models.Bus;

public abstract class BusState implements IBusState {

            private Bus bus;

            public BusState(Bus bus){
                 this.bus = bus;
            }

}
