package application.back.simulation.items;

import application.back.SimulationMain;
import application.back.models.ArretModel;
import application.back.simulation.reseau.Reseau;
import com.mxgraph.view.mxGraph;

public class ArretSimulation extends SimulationObject {

    private static Reseau reseau = SimulationMain.getInstance();
    private Object node;
    private int width,height;

    public ArretSimulation(ArretModel model) {
            super(model);
            this.width = 80;
            this.height = 15;
            this.node = createCurrentNodeScene();
    }

    public ArretSimulation(ArretModel model,int width,int height) {
        super(model);
        this.width = width;
        this.height = height;
        this.node = createCurrentNodeScene();
    }

    @Override
    public String toString() {
        ArretModel arretModel = getModel();
        return arretModel.toString();
    }

    private synchronized Object createCurrentNodeScene(){
        mxGraph graph = reseau.getGraph();
        ArretModel model = getModel();
        return graph.insertVertex(reseau.getParent() ,null,model.getNom(), model.getLatitude() , model.getLongitude() , width, height);
    }

    public Object getNodeScene(){return node;}


    @Override
    public ArretModel getModel() {
        return ((ArretModel)model);
    }

}
