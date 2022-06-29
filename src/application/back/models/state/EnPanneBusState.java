package application.back.models.state;

import application.back.models.Bus;

public class EnPanneBusState extends BusState{


    public EnPanneBusState(Bus bus) {
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
