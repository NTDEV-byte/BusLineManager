package application.back.simulation.items;

import application.back.models.BusModel;
import application.back.simulation.BusNotification;
import application.back.simulation.items.state.*;

import java.util.concurrent.SubmissionPublisher;

public class BusSimulation extends SimulationObject {

    private int maxRandomDelaiAdded = 3000;
    private int minDelaiChargementPassagers = 2000;
    private int minDelaiEntreChaqueArret = 2000;
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
    private SubmissionPublisher<BusNotification> notifier;


    public BusSimulation(BusModel model) {
        super(model);
        this.chargePassagerBusState = new ChargePassagersBusState(this);
        this.circuleBusSate = new CirculeBusState(this);
        this.depotBusState = new DepotBusState(this);
        this.enPanneBusState = new EnPanneBusState(this);
        this.changeState(depotBusState);
        this.initBehaviour();
        this.minDelaiEntreChaqueArret += Math.random() * maxRandomDelaiAdded;
        this.notifier = new SubmissionPublisher<>();
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
            this.notifier.subscribe(ligneAffecter);
            this.notifyOnStart(currentArret);
    }

    public void onSortieDuDepotAction(LigneSimulation ligneAffecter,ArretSimulation arretDebut){
            this.currentligne = ligneAffecter;
            this.currentArret = arretDebut;
            this.enCirculation = true;
            this.changeState(chargePassagerBusState);
            this.indexCurrentArret = ligneAffecter.getArrets().indexOf(arretDebut);
            this.thread.start();
            this.notifier.subscribe(ligneAffecter);
            this.notifyOnStart(currentArret);
    }

    public void onRentreAuDepotAction(){
            this.currentligne = null;
            this.currentArret = null;
            this.enCirculation = false;
            this.changeState(depotBusState);
    }

    private void notifyOnStart(ArretSimulation arretDebut){
        int arretID = arretDebut.getModel().getId();
        this.getNotifier().submit(new BusNotification(BusNotification.STATE_CHARGEMENT_PASSAGERS,indexCurrentArret, "Arrêt "+arretID+" - Bus "+getModel().getId()));
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
                chargePassagers();
                circuleEntreArrets();
                avanceVersProchainArret();
            }
        });
    }

    private void circuleEntreArrets(){
        changeState(new CirculeBusState(this));
        this.currentState.display();
        waitFor(100);
        tombeEnPanne();
        waitFor(minDelaiEntreChaqueArret);
    }

    public int getIndexCurrentArret() {
        return indexCurrentArret;
    }

    private void avanceVersProchainArret(){
        if (indexCurrentArret + 1 >= currentligne.getArrets().size()) {
            indexCurrentArret = 0;
        } else {
            indexCurrentArret++;
        }

        this.currentArret = currentligne.getArrets().get(indexCurrentArret);
    }

    private void chargePassagers(){
        changeState(new ChargePassagersBusState(this));
        this.currentState.display();
        waitFor(minDelaiChargementPassagers);
    }

    private void tombeEnPanne() { // 80 % de chance de tomber en panne si c'est un MAN non je rigole : )
        if (Math.random() <= 0.2) {
            changeState(new EnPanneBusState(this));
            this.currentState.display();
            waitFor(5000);
        }
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


    public SubmissionPublisher<BusNotification> getNotifier() {
        return notifier;
    }

}
