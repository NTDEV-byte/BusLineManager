package application.back.simulation.reseau;

import application.back.simulation.reseau.choix.AmetisReseau;
import application.back.simulation.reseau.choix.DefaultReseau;
import application.back.simulation.reseau.choix.SncfReseau;

public class ReseauFactory {

            private ReseauFactory(){}

            public static Reseau createReseau(ReseauDisponibleEnum reseau){
                    if(reseau == ReseauDisponibleEnum.AMETIS){
                         return new AmetisReseau();
                    }
                    else if(reseau == ReseauDisponibleEnum.SNCF){
                         return new SncfReseau();
                    }
                    return new DefaultReseau();
            }
}
