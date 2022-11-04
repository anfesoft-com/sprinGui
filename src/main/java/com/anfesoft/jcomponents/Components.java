package com.anfesoft.jcomponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.anfesoft.commons.FormatHelpers;
import com.anfesoft.entities.JournalTrades;
import com.anfesoft.training.Queries;



public class Components {

	private static JTextArea textArea = new JTextArea();

	public static JSplitPane createSplitPaneWithTextArea() {

		JSplitPane p = new JSplitPane();

		p.setRightComponent(textArea);
		JPanel panel = new JPanel();
		p.setLeftComponent(panel);
		
		panel.setLayout(new BorderLayout());
		
		JButton btnSave = new JButton("     Save Log  ");
		
		panel.add(btnSave, BorderLayout.NORTH);
		
		//adding table
		
		JTable tableLog = createJournalListTable();
		panel.add(tableLog,BorderLayout.CENTER);
		
		
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.out.println("Mose clicked:"+ textArea.getText());
				if(!textArea.getText().isEmpty())
				{
				Queries.insertEntryJournalTrades(textArea.getText(),tableLog);
				}
			}
		});
		setTextArea(textArea);
		return p;
	}

	
	public static JTextArea getTextArea() {
		return textArea;
	}


	public static void setTextArea(JTextArea textArea) {
		Components.textArea = textArea;
	}


	private static JTable createJournalListTable() {
		JTable tableLog = new JTable();
		tableLog.setCellSelectionEnabled(false);
		
		 ListSelectionModel cellSelectionModel = tableLog.getSelectionModel();
		 cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 
		 
		tableLog.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableLog.setToolTipText("e");
		tableLog.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] columns = new String[] {"Time"};
		Object[][] data = FormatHelpers.getTradingLogs(columns);
		DefaultTableModel tbmodel = new DefaultTableModel(data, columns);
		tableLog.setModel(tbmodel);
		tableLog.getColumnModel().getColumn(0).setPreferredWidth(40);
	     cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent e) {
	          
	        int selection = tableLog.getSelectedRow();
	       Object date = tableLog.getValueAt(selection, 0);
	        getTextArea().setText(Queries.getEntryJournalLog(date));
	        }
	      });

		return tableLog;
	}

}
