package application.back.simulation.items;

import application.back.models.ArretModel;
import application.back.simulation.reseau.Reseau;
import com.mxgraph.view.mxGraph;

public class ArretSimulation extends SimulationObject {

    private static Reseau reseau = Reseau.getInstance();
    private Object node;

    public ArretSimulation(ArretModel model) {
            super(model);
            this.node = createCurrentNodeScene();
    }

    @Override
    public String toString() {
        ArretModel arretModel = getModel();
        return arretModel.toString();
    }



    private Object createCurrentNodeScene(){
        mxGraph graph = reseau.getGraph();
        ArretModel model = getModel();
        return graph.insertVertex(reseau.getParent() ,null,model.getNom(), model.getLatitude() , model.getLongitude() , 80, 80);
    }

    public Object getNodeScene(){return node;}


    @Override
    public ArretModel getModel() {
        return ((ArretModel)model);
    }

}
