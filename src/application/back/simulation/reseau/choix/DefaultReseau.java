package application.back.simulation.reseau.choix;

import application.back.simulation.reseau.Reseau;

public class DefaultReseau extends Reseau {

    @Override
    public void loadReseau() {
        createReseauUsingFile("resources/default.json");
        affectationBus();
        displayGUI();
    }

}
