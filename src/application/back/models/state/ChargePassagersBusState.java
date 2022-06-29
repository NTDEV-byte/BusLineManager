package application.back.models.state;

import application.back.models.Bus;

public class ChargePassagersBusState extends BusState{

    public ChargePassagersBusState(Bus bus) {
        super(bus);
    }

    @Override
    public String getBusState() {
        return null;
    }

    @Override
    public void render() {

    }

}
