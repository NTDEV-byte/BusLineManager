package application.back.simulation.reseau;
import application.back.models.ArretModel;
import application.back.models.LigneModel;
import application.back.models.ReseauModel;
import application.back.simulation.items.ArretSimulation;
import application.back.simulation.items.BusSimulation;
import application.back.simulation.items.DepotSimulation;
import application.back.simulation.items.LigneSimulation;
import application.view.BusLineManagerView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public abstract class Reseau implements IReseau {





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
        this.graph.setCellsEditable(false);
        //this.configJGraphX();
    }

    protected void configJGraphX(){
        Map<String, Object> style = graph.getStylesheet().getDefaultEdgeStyle();
        style.put(mxConstants.STYLE_ROUNDED, true);
        style.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_ENTITY_RELATION);
        this.graph.setCellsEditable(false);
    }


    private int randX(){
        return (int)(Math.random() * 800);
    }
    private int randY(){
        return (int)(Math.random() * 600);
    }

    @Override
    public void constructReseau() {

        LigneSimulation l1 = new LigneSimulation(new LigneModel(1,"L1") , "0000ff");

        ArretSimulation a1 = new ArretSimulation(new ArretModel(1,"L1-A1",600,100));
        ArretSimulation a2 = new ArretSimulation(new ArretModel(2,"L1-A2",100,200));
        ArretSimulation a3 = new ArretSimulation(new ArretModel(3,"L1-A3",200 ,300));
        ArretSimulation a4 = new ArretSimulation(new ArretModel(4,"L1-A4",300 ,400));
        ArretSimulation a5 = new ArretSimulation(new ArretModel(5,"L1-A5",400,500));
        l1.addArret(a1);l1.addArret(a2);l1.addArret(a3);l1.addArret(a4);l1.addArret(a5);
        l1.linkLast();

        LigneSimulation l2 = new LigneSimulation(new LigneModel(2,"L2") , "00ff00");

        ArretSimulation a21 = new ArretSimulation(new ArretModel(1,"L2-A1",randX(),randY()));
      //  l2.addArret(a1);
        ArretSimulation a22 = new ArretSimulation(new ArretModel(2,"L2-A2",randX(),randY()));
        ArretSimulation a23 = new ArretSimulation(new ArretModel(3,"L2-A3",randX(),randY()));
        ArretSimulation a24 = new ArretSimulation(new ArretModel(4,"L2-A4",randX(),randY()));
        ArretSimulation a25 = new ArretSimulation(new ArretModel(5,"L2-A5",randX(),randY()));
        l2.addArret(a21);l2.addArret(a22);l2.addArret(a23);l2.addArret(a24);l2.addArret(a25);
        l2.linkLast();

//        LigneSimulation l3 = new LigneSimulation(new LigneModel(2,"L3") , "ff0000");
//        l3.addArret(a1);
//        ArretSimulation a31 = new ArretSimulation(new ArretModel(1,"L3-A1",randX(),randY()));
//        ArretSimulation a32 = new ArretSimulation(new ArretModel(2,"L3-A2",randX(),randY()));
//        ArretSimulation a33 = new ArretSimulation(new ArretModel(3,"L3-A3",randX(),randY()));
//        ArretSimulation a34 = new ArretSimulation(new ArretModel(4,"L3-A4",randX(),randY()));
//        ArretSimulation a35 = new ArretSimulation(new ArretModel(5,"L3-A5",randX(),randY()));
//        l3.addArret(a31);l3.addArret(a32);l3.addArret(a33);l3.addArret(a34);l3.addArret(a35);
//        l3.linkLast();

         depot.affecteBusAlALigneDuReseauEtArret(1,l1 , a1);
         depot.affecteBusAlALigneDuReseauEtArret(2,l2 , a21);
//        depot.affecteBusAlALigneDuReseauEtArret(3,l1 , a1);
        //depot.affecteBusAlALigneDuReseauEtArret(3,l3 , a1);

         displayGUI();
    }

    @Override
    public abstract void loadReseau();


    protected void affectationBus()
    {
        depot.affecteBusAlALigneDuReseau(1 , lignes.get(0));
        depot.affecteBusAlALigneDuReseau(3 , lignes.get(1));
        depot.affecteBusAlALigneDuReseau(2 , lignes.get(2));
    }

    protected void createReseauUsingFile(String pathReseau){
        ReseauModel model = loadReseauConfig(pathReseau);
        List<LigneModel> lignesModels = model.getLignes();
        LigneSimulation ligneActuelleSimulation = null;

        int ligneTotal = lignesModels.size();
        int arretTotal;


        // création des lignes pricipales du réseau
        for(int ligneIndex = 0; ligneIndex < ligneTotal; ligneIndex++){

            ligneActuelleSimulation = new LigneSimulation(lignesModels.get(ligneIndex));

            List<ArretModel> arrets = lignesModels.get(ligneIndex).getArrets();
            arretTotal = arrets.size();

            for(int arretIndex = 0; arretIndex < arretTotal; arretIndex++)
            {
                ligneActuelleSimulation.addArret(new ArretSimulation(arrets.get(arretIndex)));
            }

            ligneActuelleSimulation.linkLast();
            lignes.add(ligneActuelleSimulation);
        }
    }

    @Override
    public void displayGUI() {
        graph.getModel().beginUpdate();
        try{
           // constructReseau();

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



    private static ReseauModel loadReseauConfig(String pathReseau){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String data = "";
        try {
            data =  Files.readString(Path.of(pathReseau));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type reseauType = new TypeToken<ReseauModel>(){}.getType();
        ReseauModel model = gson.fromJson(data , reseauType);
        System.out.println("Arrêts: "+model.getArretsCommuns().toString());
        return model;
    }

}
