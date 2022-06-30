package application.back.simulation.reseau;

import application.back.models.ArretModel;
import application.back.models.LigneModel;
import application.back.simulation.items.ArretSimulation;
import application.back.simulation.items.BusSimulation;
import application.back.simulation.items.DepotSimulation;
import application.back.simulation.items.LigneSimulation;
import application.view.BusLineManagerView;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Reseau implements IReseau {

    public static Reseau INSTANCE = null;

    private List<LigneSimulation> lignes;
    private List<BusSimulation> busEnCirculation;
    private DepotSimulation depot;
    private mxGraph graph;
    private Object parent;

    public Reseau() {
        this.lignes = new ArrayList<>();
        this.depot = new DepotSimulation();
        this.busEnCirculation = new ArrayList<>();
        this.graph = new mxGraph();
        this.parent = graph.getDefaultParent();
    }

    private void config(){
        Map<String, Object> style = graph.getStylesheet().getDefaultEdgeStyle();
        style.put(mxConstants.STYLE_ROUNDED, true);
        style.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_TOPTOBOTTOM);
    }



    @Override
    public void constructReseau() {

        LigneSimulation l1 = new LigneSimulation(new LigneModel(1,"L1"));

        ArretSimulation a1 = new ArretSimulation(new ArretModel(1,"L1-A1",3,5));
        ArretSimulation a2 = new ArretSimulation(new ArretModel(2,"L1-A2",15,25));
        ArretSimulation a3 = new ArretSimulation(new ArretModel(3,"L1-A3",40,45));
        ArretSimulation a4 = new ArretSimulation(new ArretModel(4,"L1-A4",65,65));
        ArretSimulation a5 = new ArretSimulation(new ArretModel(5,"L1-A5",85,85));

        l1.addArret(a1);l1.addArret(a2);l1.addArret(a3);l1.addArret(a4);l1.addArret(a5);


//        LigneSimulation l2 = new LigneSimulation(new LigneModel(2,"L2"));
//
//        ArretSimulation a21 = new ArretSimulation(new ArretModel(1,"L2-A1",3,5));
//        ArretSimulation a22 = new ArretSimulation(new ArretModel(2,"L2-A2",15,25));
//        ArretSimulation a23 = new ArretSimulation(new ArretModel(3,"L2-A3",40,45));
//        ArretSimulation a24 = new ArretSimulation(new ArretModel(4,"L2-A4",65,65));
//        ArretSimulation a25 = new ArretSimulation(new ArretModel(5,"L2-A5",85,85));
//
//        l2.addArret(a21);l2.addArret(a22);l2.addArret(a23);l2.addArret(a24);l2.addArret(a25);
//
//
//        LigneSimulation l3 = new LigneSimulation(new LigneModel(2,"L3"));
//
//        ArretSimulation a31 = new ArretSimulation(new ArretModel(1,"L3-A1",3,5));
//        ArretSimulation a32 = new ArretSimulation(new ArretModel(2,"L3-A2",15,25));
//        ArretSimulation a33 = new ArretSimulation(new ArretModel(3,"L3-A3",40,45));
//        ArretSimulation a34 = new ArretSimulation(new ArretModel(4,"L3-A4",65,65));
//        ArretSimulation a35 = new ArretSimulation(new ArretModel(5,"L3-A5",85,85));
//
//        l3.addArret(a31);l3.addArret(a32);l3.addArret(a33);l3.addArret(a34);l3.addArret(a35);

      //  depot.affecteBusAlALigneDuReseauEtArret(1,l1 , a1);
       // depot.affecteBusAlALigneDuReseauEtArret(2,l2 , a21);
       // depot.affecteBusAlALigneDuReseauEtArret(3,l3 , a31);
    }

    @Override
    public void loadReseau() {

    }

    @Override
    public void displayInConsole() {

    }

    @Override
    public void displayGUI() {
        graph.getModel().beginUpdate();
        try{
            constructReseau();
            new BusLineManagerView(graph);

        } finally {
            graph.getModel().endUpdate();
        }
    }

    public mxGraph getGraph() {
        return graph;
    }

    public Object getParent() {
        return parent;
    }

    public static Reseau getInstance(){
        if(INSTANCE == null){
             INSTANCE = new Reseau();
        }
        return INSTANCE;
    }

}
