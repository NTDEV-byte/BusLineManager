package application.back.simulation;

public class BusNotification {

            public static final int STATE_EN_CIRCULATION = 1;
            public static final int STATE_CHARGEMENT_PASSAGERS = 2;
            public static final int STATE_BUS_EN_PANNE = 3;

            private int typeEvent;
            private int arretIndex;
            String information;

            public BusNotification(int typeEvent,int arretIndex, String information) {
                this.typeEvent = typeEvent;
                this.arretIndex = arretIndex;
                this.information = information;
            }

            public String toString(){
                 return "Arrêt Index: "+ arretIndex+ "Information: "+information;
            }

            public int getArretIndex() {
                return arretIndex;
            }

            public String getInformation() {
                return information;
            }

            public int getTypeEvent() {
                return typeEvent;
            }
}
