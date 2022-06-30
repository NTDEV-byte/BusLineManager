package application.back.simulation.items;

import application.back.models.ArretModel;
import application.back.models.LigneModel;
import application.back.simulation.reseau.Reseau;
import com.mxgraph.view.mxGraph;

import java.util.ArrayList;
import java.util.List;

public class LigneSimulation extends SimulationObject {

    private static Reseau reseau = Reseau.getInstance();
    private List<BusSimulation> bus;
    private List<ArretSimulation> arrets;

    public LigneSimulation(LigneModel model) {
        super(model);
        this.bus = new ArrayList<>();
        this.arrets = new ArrayList<>();
    }

    public void addArret(ArretSimulation arret){
        LigneModel ligneModel = (LigneModel) model;
        ligneModel.ajouteArret((ArretModel) arret.getModel());
        this.arrets.add(arret);
        createEdgeOnAdd();
    }

    public void removeArret(ArretSimulation arret){
        LigneModel ligneModel = (LigneModel) model;
        ligneModel.supprimeArret((ArretModel) arret.getModel());
        this.arrets.remove(arret);
    }

    public void linkLast(){
        ArretSimulation start = arrets.get(0);
        ArretSimulation last = arrets.get(arrets.size() - 1);

        mxGraph graph = Reseau.getInstance().getGraph();
        Object parent = Reseau.getInstance().getParent();
        graph.insertEdge(parent,null,"Lien" , last.getNodeScene() ,start.getNodeScene());
    }

    private void createEdgeOnAdd(){
        mxGraph graph = Reseau.getInstance().getGraph();
        if(arrets.size() > 1){
            ArretSimulation a1 = arrets.get(arrets.size() - 1);
            ArretSimulation a2 = arrets.get(arrets.size() - 2);
            Object parent = Reseau.getInstance().getParent();
            graph.insertEdge(parent,null,"Lien" ,a2.getNodeScene() , a1.getNodeScene());
        }
    }


    public void addBus(BusSimulation bus){
           this.bus.add(bus);
    }

    public void removeBus(BusSimulation bus){
            this.bus.add(bus);
    }


    public ArretSimulation getDepart(){
            return arrets.get(0);
    }

    public ArretSimulation getArriver(){
            return arrets.get(arrets.size() - 1);
    }

    public String getBusInfo(){
        if(bus.size() == 0){
            System.err.println("Aucun bus est affecté a cette ligne !");
            return "";
        }
        return bus.toString();
    }

    @Override
    public String toString() {
        LigneModel ligneModel = getModel();
        return ligneModel.toString();
    }

    @Override
    public LigneModel getModel() {
        return ((LigneModel)model);
    }

    public List<BusSimulation> getBus() {
        return bus;
    }

    public void setBus(List<BusSimulation> bus) {
        this.bus = bus;
    }

    public List<ArretSimulation> getArrets() {
        return arrets;
    }

    public void setArrets(List<ArretSimulation> arrets) {
        this.arrets = arrets;
    }
}
