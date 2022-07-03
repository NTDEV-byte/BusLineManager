package application.back.simulation.reseau.choix;

import application.back.simulation.reseau.Reseau;

public class AmetisReseau extends Reseau {

    @Override
    public void loadReseau() {
        createReseauUsingFile("resources/ametis.json");
        affectationBus();
        displayGUI();
    }


}
