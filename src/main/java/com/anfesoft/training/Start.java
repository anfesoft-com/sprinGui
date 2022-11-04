package com.anfesoft.training;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.anfesoft.jcomponents.Components;
import com.anfesoft.tabs.BalancesCrypto;
import com.anfesoft.tabs.ImaginalActsViewTable;
import com.anfesoft.tabs.Web2pyManagement;

@SpringBootApplication
public class Start extends JPanel {
	private static final long serialVersionUID = 1579937831249206238L;

	public Start() {
	
		BalancesCrypto bc = new BalancesCrypto();
		ImaginalActsViewTable ia = new ImaginalActsViewTable();
		Web2pyManagement wm = new Web2pyManagement();
		
		JSplitPane notepadPane = Components.createSplitPaneWithTextArea();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		tabbedPane.addTab("Docu", null, wm.createJPanelViewTitles(), null);
		tabbedPane.addTab("Balances", null, bc.createComposedComponent(), null);
		tabbedPane.addTab("Journal", null, notepadPane, null);
		tabbedPane.addTab("IA", null, ia.createJpaneView(), null);
		

		tabbedPane.setSelectedIndex(0);
		add(tabbedPane);
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("theGui");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Start newContentPane = new Start();
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}