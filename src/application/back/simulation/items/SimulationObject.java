package application.back.simulation.items;

import application.back.models.Model;

public class SimulationObject  {

        protected Model model;

        public SimulationObject(Model model){
            this.model = model;
        }

        public Model getModel() {
            return model;
        }

        public void setModel(Model model) {
            this.model = model;
        }

        @Override
        public String toString() {
            return "";
        }
}
