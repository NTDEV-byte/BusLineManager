package application.temp;

import com.mxgraph.view.mxGraph;

import java.util.ArrayList;
import java.util.List;

public class Reseau {

        private static Reseau RESEAU_INSTANCE = null;

        private mxGraph graph;
        private Object parent;
        private List<Ligne> lignes;
        private Ligne ligne;

        private Reseau() {
            this.graph = new mxGraph();
            this.parent = graph.getDefaultParent();
            this.lignes = new ArrayList<>();
            this.ligne = new Ligne();
        }

        public void initReseau(){
               graph.getModel().beginUpdate();
                try{

//                    Arret a1 = new Arret("Arret 1 ", 150 , 150 , 40 ,40);
//                    Arret a2 = new Arret("Arret 2 ", 180 , 150 , 40 ,40);
//
//                    graph.insertEdge(parent,null,"Lien" , a1.getNodeScene() , a2.getNodeScene());

                        ligne.addArret(new Arret("Arret 1 ", 150 , 150 , 40 ,40));
                        ligne.addArret(new Arret("Arret 2 ", 180 , 80 , 40 ,40));
                        ligne.addArret(new Arret("Arret 3 ", 300 , 120 , 40 ,40));
                        ligne.addArret(new Arret("Arret 4 ", 350 , 140 , 40 ,40));
                        ligne.addArret(new Arret("Arret 5 ", 400 , 150 , 40 ,40));
                        ligne.addArret(new Arret("Arret 6 ", 450 , 160 , 40 ,40));
                        ligne.addArret(new Arret("Arret 7 ", 500, 180 , 40 ,40));
                        ligne.linkLast();
                        threadSimulation();


                } finally {
                graph.getModel().endUpdate();
                }
        }


        private void threadSimulation(){
            new Thread(() -> {
                int index = 0;
                while(true){
                    index++;
                    if(index % 2 == 0){
                    System.out.println("Bus 1 ...");
                    }
                    else{
                        System.out.println("Bus 2 ...");
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            } , "Thread-Simulation").start();
        }

        public mxGraph getGraph() {
                return graph;
        }

        public Object getParent() {
                return parent;
        }

        public static Reseau getInstance(){
                if(RESEAU_INSTANCE == null){
                        RESEAU_INSTANCE = new Reseau();
                }
                return RESEAU_INSTANCE;
        }
}
