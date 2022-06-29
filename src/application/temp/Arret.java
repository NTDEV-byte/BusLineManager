package application.temp;

import com.mxgraph.view.mxGraph;

public class Arret {


        private static Reseau reseau = Reseau.getInstance();

        private String nom;
        private String busPresent;
        private int x,y;
        private int width,height;
        private Object node;


        public Arret(String nom, int x, int y, int width, int height) {
                this.nom = nom;
                this.x = x;
                this.y = y;
                this.width = width;
                this.height = height;
                this.node = createCurrentNodeScene();
        }

        private Object createCurrentNodeScene(){
                mxGraph graph = reseau.getGraph();
                return graph.insertVertex(reseau.getParent() ,null,nom, x , y , width, height);
        }

        public Object getNodeScene(){return node;}

        public String toString(){
                return "Nom: "+nom;
        }
}
