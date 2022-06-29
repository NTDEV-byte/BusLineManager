package application.back.models.reseau;

import application.back.models.Arret;
import application.back.models.LigneBus;

import java.util.ArrayList;
import java.util.List;

public class Reseau implements IReseau {

        private static Reseau INSTANCE =  null;

        private List<INoeud> reseauComponents;

        private Reseau(){
                this.reseauComponents = new ArrayList<>();
        }

        @Override
        public void constructReseau() {

                 LigneBus l = new LigneBus(1 , "L1");
                 l.ajouteArret(new Arret(1,"A1" , 20  , 20));
                 l.ajouteArret(new Arret(2,"A2" , 80  , 60));
                 l.ajouteArret(new Arret(3,"A3" , 140 , 120));
                 l.ajouteArret(new Arret(4,"A4" , 200 , 240));
                 addNode(l);


                LigneBus l2 = new LigneBus(2 , "L2");
                l2.ajouteArret(new Arret(1,"A2-1" , 20  , 20));
                l2.ajouteArret(new Arret(2,"A2-2" , 80  , 60));
                l2.ajouteArret(new Arret(3,"A2-3" , 140 , 120));
                l2.ajouteArret(new Arret(4,"A2-4" , 200 , 240));
                addNode(l2);

                System.out.println(l.toString());
                System.out.println(l2.toString());

        }

        @Override
        public void loadReseau() {

        }

        @Override
        public void addNode(INoeud node) {
                reseauComponents.add(node);
        }

        @Override
        public void displayInConsole() {

        }

        @Override
        public void displayGUI() {

        }


        public static Reseau getInstance(){
                if(INSTANCE == null){
                        INSTANCE = new Reseau();
                }
                return INSTANCE;
        }
}
