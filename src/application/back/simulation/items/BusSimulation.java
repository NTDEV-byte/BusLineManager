package application.back.simulation.items;

import application.back.models.BusModel;
import application.back.simulation.items.state.*;

public class BusSimulation extends SimulationObject {

    private int maxRandomDelaiAdded = 5000;
    private int minDelaiChargementPassagers = 2000;
    private int minDelaiEntreChaqueArret = 3000;
    private int indexCurrentArret;
    private boolean enCirculation;
    private BusState currentState;

    private BusState chargePassagerBusState,
                     circuleBusSate,
                     depotBusState,
                     enPanneBusState;

    private LigneSimulation currentligne;
    private ArretSimulation currentArret;
    private Thread thread;

    public BusSimulation(BusModel model) {
        super(model);
        this.chargePassagerBusState = new ChargePassagersBusState(this);
        this.circuleBusSate = new CirculeBusState(this);
        this.depotBusState = new DepotBusState(this);
        this.enPanneBusState = new EnPanneBusState(this);
        this.changeState(depotBusState);
        this.initBehaviour();
        this.minDelaiEntreChaqueArret += Math.random() * maxRandomDelaiAdded;
    }

    public void changeState(BusState newState){
        this.currentState = newState;
    }

    public void onSortieDuDepotAction(LigneSimulation ligneAffecter){
            this.currentligne = ligneAffecter;
            this.currentArret = ligneAffecter.getArrets().get(0);
            this.enCirculation = true;
            this.changeState(chargePassagerBusState);
            this.indexCurrentArret = 0;
            this.thread.start();
    }

    public void onSortieDuDepotAction(LigneSimulation ligneAffecter,ArretSimulation arretDebut){
            this.currentligne = ligneAffecter;
            this.currentArret = arretDebut;
            this.enCirculation = true;
            this.changeState(chargePassagerBusState);
            this.indexCurrentArret = ligneAffecter.getArrets().indexOf(arretDebut);
            this.thread.start();
    }

    public void onRentreAuDepotAction(){
            this.currentligne = null;
            this.currentArret = null;
            this.enCirculation = false;
            this.changeState(depotBusState);
    }

    public ArretSimulation getCurrentArret(){
        return currentligne.getArrets().get(indexCurrentArret);
    }

    public ArretSimulation getNextArret(){
         int totalArrets = currentligne.getArrets().size();
         if(indexCurrentArret < totalArrets - 1){
               return currentligne.getArrets().get(indexCurrentArret + 1);
         }
         return currentligne.getArrets().get(0);
    }

    private synchronized void initBehaviour(){
        thread = new Thread(() -> {
            while(enCirculation){
                circuleEntreArrets();
                chargePassagers();
                avanceVersProchainArret();
            }
        });
    }

    private void circuleEntreArrets(){
        changeState(circuleBusSate);
        this.currentState.display();
        waitFor(minDelaiEntreChaqueArret);
    }

    private void avanceVersProchainArret(){
        indexCurrentArret++;
        if(indexCurrentArret >= currentligne.getArrets().size()) indexCurrentArret = 0;
        this.currentArret = currentligne.getArrets().get(indexCurrentArret);
    }

    private void chargePassagers(){
        changeState(chargePassagerBusState);
        this.currentState.display();
        waitFor(minDelaiChargementPassagers);
    }

    private void tombeEnPanne(){ // 80 % de chance de tomber en panne si c'est un MAN non je rigole : )

    }

    private void rentreAuDepot(){ // rentre après X tours
            onRentreAuDepotAction();
            this.currentState.display();
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

    @Override
    public String toString() {
        BusModel busModel = getModel();
        return busModel.toString();
    }

    public String getId(){
        BusModel busModel = getModel();
        return busModel.getId()+"";
    }

    public String getCurrentArretNom(){
            return getCurrentArret().getModel().getNom();
    }

    public String getNextArretNom(){
        return getNextArret().getModel().getNom();
    }

    public String getCurrentLigneId(){
        return currentligne.getModel().getId()+"";
    }

    @Override
    public BusModel getModel() {
        return ((BusModel)model);
    }

}
