package application.back.simulation.items;

import application.back.models.BusModel;
import application.back.simulation.state.*;

public class BusSimulation extends SimulationObject {

    private int maxRandomDelaiAdded = 5000;
    private int minDelaiChargementPassagers = 2000;
    private int minDelaiEntreChaqueArret = 3000;
    private boolean enCirculation;

    private BusState chargePassagerBusState,
                     circuleBusSate,
                     depotBusState,
                     enPanneBusState;

    private BusState currentState;
    private LigneSimulation currentligne;
    private ArretSimulation currentArret;
    private Thread thread;
    private int tours = 0;


    public BusSimulation(BusModel model) {
        super(model);
        this.chargePassagerBusState = new ChargePassagersBusState(this);
        this.circuleBusSate = new CirculeBusState(this);
        this.depotBusState = new DepotBusState(this);
        this.enPanneBusState = new EnPanneBusState(this);
        this.changeState(depotBusState);
        this.initBehaviour();
    }

    public void changeState(BusState newState){
        this.currentState = newState;
    }

    public void onSortieDuDepotAction(LigneSimulation ligneAffecter){
            this.currentligne = ligneAffecter;
            this.currentArret = ligneAffecter.getArrets().get(0);
            this.enCirculation = true;
            this.changeState(chargePassagerBusState);
            this.thread.start();
    }

    public void onSortieDuDepotAction(LigneSimulation ligneAffecter,ArretSimulation arretDebut){
            this.currentligne = ligneAffecter;
            this.currentArret = arretDebut;
            this.enCirculation = true;
            this.changeState(chargePassagerBusState);
            this.thread.start();
    }

    public void onRentreAuDepotAction(){
            this.currentligne = null;
            this.currentArret = null;
            this.enCirculation = false;
            this.changeState(depotBusState);
    }

    private synchronized void initBehaviour(){
        thread = new Thread(() -> {
            while(enCirculation){

                changeState(chargePassagerBusState);
                this.currentState.display();
                waitFor(minDelaiChargementPassagers);

                changeState(circuleBusSate);
                this.currentState.display();
                waitFor(minDelaiEntreChaqueArret);

                tours++;
                if(tours >= 3) {
                    onRentreAuDepotAction();
                    this.currentState.display();
                }
            }
        });
    }

    private void waitFor(int temps){
        try {
            Thread.sleep(temps);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getLigneServieInformations(){
         if(currentligne  == null){
             System.err.println("Ce bus ne dessert aucune ligne !");
             return "";
         }
         return currentligne.toString();
    }

    public String getCurrentArretInformations(){
        if(currentArret  == null){
            System.err.println("Ce bus ne dessert aucune ligne !");
        }
        return currentArret.toString();
    }

    public ArretSimulation getArretPresent(){
         return currentArret;
    }

    @Override
    public String toString() {
        BusModel busModel = getModel();
        return busModel.toString();
    }

    @Override
    public BusModel getModel() {
        return ((BusModel)model);
    }

}
