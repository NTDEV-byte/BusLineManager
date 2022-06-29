package application.view;

import application.temp.Reseau;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;

public class WindowBusManager extends JFrame {


        public WindowBusManager(){
            super("JGrapghX tutoriel: Exemple 1");

            Reseau reseau = null;
           try {
                  reseau =  Reseau.getInstance();
                  reseau.initReseau();

            } finally {

            }
            mxGraphComponent graphComponent = new mxGraphComponent(reseau.getGraph());
            getContentPane().add(graphComponent);
            setPreferredSize(new Dimension(800,600));
            setSize(new Dimension(800,600));
            setLocationRelativeTo(null);
            setResizable(false);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }


        private static void createDefault(){

            mxGraph graph = new mxGraph();
            Object parent = graph.getDefaultParent();

            graph.getModel().beginUpdate();
            try {
                Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80, 30);
                Object v2 = graph.insertVertex(parent, null, "World!", 240, 150, 80, 30);
                graph.insertEdge(parent, null, "Edge", v1, v2);
            } finally {
                graph.getModel().endUpdate();
            }
        }
}
