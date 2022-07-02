package application.back;

import application.back.simulation.reseau.Reseau;
import application.back.simulation.reseau.ReseauDisponibleEnum;

public class SimulationMain {


            public static void main(String[] args){
                 Reseau reseau = Reseau.createReseau(ReseauDisponibleEnum.DEFAULT);
                 reseau.loadReseau();
            }

}
