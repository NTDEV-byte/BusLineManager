package application.back.simulation.reseau.choix;

import application.back.simulation.reseau.Reseau;

public class SncfReseau extends Reseau {

    @Override
    public void loadReseau() {
        createReseauUsingFile("resources/sncf.json");
        affectationBus();
        displayGUI();
    }
}
