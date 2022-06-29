package application.back.simulation.items;

import application.back.models.ArretModel;

public class ArretSimulation extends SimulationObject {


    public ArretSimulation(ArretModel model) {
            super(model);
    }

    @Override
    public String toString() {
        ArretModel arretModel = getModel();
        return arretModel.toString();
    }

    @Override
    public ArretModel getModel() {
        return ((ArretModel)model);
    }

}
