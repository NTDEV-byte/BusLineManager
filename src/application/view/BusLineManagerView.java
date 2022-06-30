package application.view;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;

public class BusLineManagerView extends JFrame {

        public BusLineManagerView(mxGraph graph){
            super("BusLineManager | Graph");
            mxGraphComponent graphComponent = new mxGraphComponent(graph);
            getContentPane().add(graphComponent);
            setPreferredSize(new Dimension(800,600));
            setSize(new Dimension(800,600));
            setLocationRelativeTo(null);
            setResizable(false);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
}
