package com.anfesoft.jcomponents;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.lang.reflect.Array;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomeJpanelTableFactory extends JScrollPane {

	private static long serialVersionUID = 1819108440955492939L;

	
	private static String[] columnsSets = new String[] {};
	private static Object[][] data = new Array[1][2];
	private static DefaultTableModel tableModel = new DefaultTableModel(data , columnsSets);
	private static JTable tableSample = new JTable(tableModel);


	public JPanel createTable()
	{
		tableSample.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		tableSample.setAutoCreateRowSorter(true);
		JPanel p = new JPanel(new GridBagLayout());
		return p;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	public static void setColumnsSets(String[] columnsSets) {
		CustomeJpanelTableFactory.columnsSets = columnsSets;
	}

	public static void setData(Object[][] data) {
		CustomeJpanelTableFactory.data = data;
	}

	public static void setTableModel(DefaultTableModel tableModel) {
		CustomeJpanelTableFactory.tableModel = tableModel;
	}

	public static void setTableSample(JTable tableSample) {
		CustomeJpanelTableFactory.tableSample = tableSample;
	}

}
