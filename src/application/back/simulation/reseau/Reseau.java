package application.back.simulation.reseau;

import application.back.models.ArretModel;
import application.back.models.LigneModel;
import application.back.simulation.items.ArretSimulation;
import application.back.simulation.items.BusSimulation;
import application.back.simulation.items.Depot;
import application.back.simulation.items.LigneSimulation;
import java.util.ArrayList;
import java.util.List;

public class Reseau implements IReseau {

    public static Reseau INSTANCE = null;

    private List<LigneSimulation> lignes;
    private List<BusSimulation> busEnCirculation;

    private Depot depot;

    public Reseau() {
        this.lignes = new ArrayList<>();
        this.depot = new Depot();
        this.busEnCirculation = new ArrayList<>();
    }

    @Override
    public void constructReseau() {

        ArretSimulation a1 = new ArretSimulation(new ArretModel(1,"A1",3,5));
        ArretSimulation a2 = new ArretSimulation(new ArretModel(2,"A2",15,25));
        ArretSimulation a3 = new ArretSimulation(new ArretModel(3,"A3",40,45));
        ArretSimulation a4 = new ArretSimulation(new ArretModel(4,"A4",65,65));
        ArretSimulation a5 = new ArretSimulation(new ArretModel(5,"A5",85,85));

        LigneSimulation l1 = new LigneSimulation(new LigneModel(1,"L1"));
        l1.addArret(a1);
        l1.addArret(a2);
        l1.addArret(a3);
        l1.addArret(a4);
        l1.addArret(a5);

      //  busEnCirculation.add(depot.affecteBusAlALigneDuReseauEtArret(1,l1 , a1));

        depot.affecteBusAlALigneDuReseauEtArret(3,l1 , a1);
    }

    @Override
    public void loadReseau() {

    }

    @Override
    public void displayInConsole() {

    }

    @Override
    public void displayGUI() {

    }

    public static Reseau getInstance(){
        if(INSTANCE == null){
             INSTANCE = new Reseau();
        }
        return INSTANCE;
    }

}
