package application.back.simulation.reseau;

public class AmetisReseau extends Reseau {

    @Override
    public void loadReseau() {
        createReseauUsingFile("resources/ametis.json");
        affectationBus();
        displayGUI();
    }


}
