package application.back.models.reseau;

public class ReseauDecorator implements INoeud {

        protected INoeud noeud;

        public ReseauDecorator(INoeud noeud) {
            this.noeud = noeud;
        }

        @Override
        public String getInformation() {
           return noeud.getInformation();
        }


}
