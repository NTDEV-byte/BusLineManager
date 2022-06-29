package application.back.models.reseau;

public interface IReseau {

            // construction
            public void constructReseau();
            public void loadReseau();

            // methode de construction
            public void addNode(INoeud node);
            public void displayInConsole();
            public void displayGUI();


}
