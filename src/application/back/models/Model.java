package application.back.models;

public abstract class Model {

        protected int id;

        public Model(int id){
                this.id = id;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }


}
