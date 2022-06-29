package application.back;

import application.back.simulation.reseau.Reseau;

public class SimulationMain {

            public static void main(String[] args){
                Reseau reseau = Reseau.getInstance();
                reseau.constructReseau();
            }

}
