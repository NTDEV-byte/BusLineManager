package application.back;

import application.back.simulation.reseau.Reseau;
import application.back.simulation.reseau.ReseauDisponibleEnum;
import application.back.simulation.reseau.ReseauFactory;

import java.util.Scanner;

import static application.back.simulation.reseau.ReseauDisponibleEnum.*;

public class SimulationMain {

            private static Reseau reseau = null;

            public static void main(String[] args){
                     createReseau();
            }

            private static void createReseau(){
                 System.out.println("Choisir un Réseau AMETIS (0) ");
                 System.out.println("Choisir un Réseau SNCF   (1) ");
                 System.out.println("Réseau par defaut  (-1) ");

                 Scanner sc = new Scanner(System.in);
                 int choix = sc.nextInt();

                 ReseauDisponibleEnum choixEnum;

                if(choix >= values().length || choix < 0){
                    System.err.println("choix incorrect doit être entre 0 et "+(values().length -1));
                    choixEnum = DEFAULT;
                    System.out.println("choix mis sur défault");
                }
                else{
                    choixEnum = ReseauDisponibleEnum.values()[choix];
                }

                 switch(choixEnum){
                     case AMETIS:
                         reseau = ReseauFactory.createReseau(AMETIS);
                         break;
                     case SNCF:
                         reseau = ReseauFactory.createReseau(SNCF);
                         break;
                     default:
                         reseau = ReseauFactory.createReseau(DEFAULT);
                         break;
                 }
                 reseau.loadReseau();
            }

            public static Reseau getInstance(){
                return reseau;
            }
}
