package com.anfesoft.training;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class SimplePane {
//	public static String[] labels = Queries.query2();
    Border blackline, raisedetched, loweredetched, raisedbevel, loweredbevel, empty;
    
    void addCompForBorder(Border border, String description, Container container) {
        JPanel comp = new JPanel(new GridLayout(1,1), false);
        JLabel label = new JLabel(description, JLabel.LEFT);
        comp.add(label);
        comp.setBorder(border);
        comp.add(Cobox.combobox(null));
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(comp);
        
    }
    
    public JPanel SimplePaneCreate(Border paneEdge, Border blackline,String label) {
        JPanel simpleBorders = new JPanel();
        simpleBorders.setBorder(paneEdge);
        addCompForBorder(blackline, label,simpleBorders);
        return simpleBorders;
    }
    
    
    
    public JScrollPane textPaneCreate(Border paneEdge, Border blackline,String label) {
    	
		JTextArea np = new JTextArea(5,5);
		np.setLayout(null);
		np.setFont(new Font("Serif", Font.PLAIN, 16));
		np.setBounds(10, 10, 10, 10);
		np.setVisible(true);
		JScrollPane texpane = new JScrollPane(np); 
		texpane.setLayout(null);
		np.setEditable(true);
        
        return texpane;
    }
    

    public JPanel PanelCreate(Border paneEdge, Border blackline,String label) {
        JPanel simpleBorders = new JPanel();
        simpleBorders.setBorder(paneEdge);
        return simpleBorders;
    }
}