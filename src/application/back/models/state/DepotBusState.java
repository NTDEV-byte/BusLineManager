package application.back.models.state;

import application.back.models.Bus;

public class DepotBusState extends BusState{

    public DepotBusState(Bus bus) {
        super(bus);
    }

    @Override
    public String getBusState() {
        return "null";
    }

    @Override
    public void render() {
        System.out.println("sz");
    }
}
