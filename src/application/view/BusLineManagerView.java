package application.view;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BusLineManagerView extends JFrame {


        public static InputHandler input = new InputHandler();



        public BusLineManagerView(mxGraph graph){
            super("BusLineManager | Graph");
            mxGraphComponent graphComponent = new mxGraphComponent(graph);
            getContentPane().add(graphComponent);
            setSize(new Dimension(1200,800));
            setLocationRelativeTo(null);
            setVisible(true);
            addMouseListener(input);
            addMouseMotionListener(input);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }



        public static class InputHandler implements MouseListener, MouseMotionListener {


            public static int mouseX,mouseY,mouseB;

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                    mouseB = e.getButton();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                    mouseB = -1;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                    mouseX = e.getX();
                    mouseY = e.getY();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                    mouseX = e.getX();
                    mouseY = e.getY();
            }
        }
}
