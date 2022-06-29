package application.temp;

import com.mxgraph.view.mxGraph;

import java.util.ArrayList;
import java.util.List;

public class Ligne {

        private static Reseau reseau = Reseau.getInstance();
        private List<Arret> arrets;


        public Ligne(){
            arrets = new ArrayList<>();
        }

        public void addArret(Arret a){
            arrets.add(a);
            if(arrets.size() > 1){
                Arret a1 = arrets.get(arrets.size() - 1);
                Arret a2 = arrets.get(arrets.size() - 2);
                mxGraph graph = Reseau.getInstance().getGraph();
                Object parent = Reseau.getInstance().getParent();
                graph.insertEdge(parent,null,"Lien" ,a2.getNodeScene() , a1.getNodeScene());
            }
        }


        public void linkLast(){

            Arret start = arrets.get(0);
            Arret last = arrets.get(arrets.size() - 1);

            mxGraph graph = Reseau.getInstance().getGraph();
            Object parent = Reseau.getInstance().getParent();
            graph.insertEdge(parent,null,"Lien" , last.getNodeScene() ,start.getNodeScene());
        }
        public void removeArret(Arret a){
            arrets.remove(a);
        }


}
