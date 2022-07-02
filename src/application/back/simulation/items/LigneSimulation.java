package application.back.simulation.items;

import application.back.models.LigneModel;
import application.back.simulation.BusNotification;
import application.back.simulation.reseau.Reseau;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

public class LigneSimulation extends SimulationObject implements Flow.Subscriber<BusNotification>{

    private static Reseau reseau = Reseau.getInstance();
    private List<BusSimulation> bus;
    private List<ArretSimulation> arrets;
    private List<Object> edges;
    private String color = "=#";
    private Flow.Subscription subscription;



    public LigneSimulation(LigneModel model) {
        super(model);
        this.bus = new ArrayList<>();
        this.arrets = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.color += model.getCouleur();
    }

    public LigneSimulation(LigneModel model,String colorHexCode) {
        super(model);
        this.bus = new ArrayList<>();
        this.arrets = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.color += colorHexCode;
    }

    public void addArret(ArretSimulation arret){
        LigneModel ligneModel = (LigneModel) model;
        ligneModel.ajouteArret(arret.getModel());
        this.arrets.add(arret);
        this.createEdgeOnAdd();
    }

    public void removeArret(ArretSimulation arret){
        LigneModel ligneModel = (LigneModel) model;
        ligneModel.supprimeArret(arret.getModel());
        this.arrets.remove(arret);
    }

    private void createEdgeOnAdd(){
        mxGraph graph = Reseau.getInstance().getGraph();
        if(arrets.size() > 1){
            ArretSimulation a1 = arrets.get(arrets.size() - 1);
            ArretSimulation a2 = arrets.get(arrets.size() - 2);
            Object parent = Reseau.getInstance().getParent();
            Object createdEdge = graph.insertEdge(parent,null,"Lien" ,a2.getNodeScene() , a1.getNodeScene(),mxConstants.STYLE_STROKECOLOR+color);
            edges.add(createdEdge);
        }
    }

    public void linkLast(){
        ArretSimulation start = arrets.get(0);
        ArretSimulation last = arrets.get(arrets.size() - 1);

        mxGraph graph = Reseau.getInstance().getGraph();
        Object parent = Reseau.getInstance().getParent();
        Object lastEdge = graph.insertEdge(parent,null,"Lien" , last.getNodeScene() ,start.getNodeScene(),mxConstants.STYLE_STROKECOLOR+color);
        edges.add(lastEdge);
    }



    public void changeArretInformation(int index,String newValue){
        if(index < 0 || index > edges.size()) {
            System.err.println("Index Arrêt invalide ça doit être entre 0 et"+(edges.size() - 1));
            return;
        }
        if(index >= 1){
            // effacement du nom du bus sur le lien lorsque il arrive sur l'arrêt suivant
            mxCell lienEntreDeuxArrêts  = ((mxCell)getEdges().get(index - 1));
            lienEntreDeuxArrêts.setValue("");

            //System.out.println("index: "+index);

            mxCell arretPresent  = ((mxCell)getArrets().get(index).getNodeScene());
            arretPresent.setValue(newValue);
        }
        else{
            //System.out.println("index: "+index);
            mxCell arretPresent  = ((mxCell)getArrets().get(index).getNodeScene());
            arretPresent.setValue(newValue);
        }
    }


    public void changeEdgeInformation(int index,String newValue){
        if(index < 0 || index > edges.size()) {
            System.err.println("Index Edge invalide ça doit être entre 0 et"+(edges.size() - 1));
            return;
        }
        if(index >= 1){

            // effacement du nom de l'arrêt - Bus lorsque ce dernier quit l'arrêt
            mxCell arretPrecedent  = ((mxCell)getArrets().get(index - 1).getNodeScene());
            ArretSimulation ars = arrets.get(index - 1);
            arretPrecedent.setValue(ars.getModel().getNom());

           mxCell cell = (mxCell)getEdges().get(index - 1);
           cell.setValue(newValue);
        }
        else{
           // System.out.println("index: "+index);
            mxCell arretPresent  = ((mxCell)getArrets().get(index).getNodeScene());
            arretPresent.setValue(newValue);
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
    public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
    }

    @Override
    public void onNext(BusNotification item) {
            if(item.getTypeEvent() == BusNotification.STATE_CHARGEMENT_PASSAGERS){
                changeArretInformation(item.getArretIndex() , item.getInformation());
            }
            else if(item.getTypeEvent() == BusNotification.STATE_EN_CIRCULATION){
                changeEdgeInformation(item.getArretIndex() , item.getInformation());
            }
            System.out.println("HIT");
            subscription.request(1);
            reseau.getGraph().refresh();
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {
        System.out.println("Completed !");
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

    public List<Object> getEdges() {
        return edges;
    }

    public String getColor() {
        return color;
    }


}
