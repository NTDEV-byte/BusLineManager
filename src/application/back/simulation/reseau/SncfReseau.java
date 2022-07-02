package application.back.simulation.reseau;

public class SncfReseau extends Reseau {

    @Override
    public void loadReseau() {
        createReseauUsingFile("resources/sncf.json");
        affectationBus();
        displayGUI();
    }
}
