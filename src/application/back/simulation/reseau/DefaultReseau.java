package application.back.simulation.reseau;

public class DefaultReseau extends Reseau{

    @Override
    public void loadReseau() {
        createReseauUsingFile("resources/default.json");
        affectationBus();
        displayGUI();
    }

}
