package com.anfesoft.jcomponents;

import java.awt.Font;
import java.lang.reflect.Array;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomeScrollTableFactory extends JScrollPane {

	private static long serialVersionUID = -166871306374058602L;
	
	private static String[] columnsSets = new String[] {};
	private static Object[][] data = new Array[1][2];
	private static DefaultTableModel tableModel = new DefaultTableModel(data , columnsSets);
	private static JTable tableSample = new JTable(tableModel);


	public JScrollPane createJscrollTable()
	{
		tableSample.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		tableSample.setAutoCreateRowSorter(true);
		JScrollPane scrollTableSample = new JScrollPane(tableSample);
		return scrollTableSample;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	public static void setColumnsSets(String[] columnsSets) {
		CustomeScrollTableFactory.columnsSets = columnsSets;
	}

	public static void setData(Object[][] data) {
		CustomeScrollTableFactory.data = data;
	}

	public static void setTableModel(DefaultTableModel tableModel) {
		CustomeScrollTableFactory.tableModel = tableModel;
	}

	public static void setTableSample(JTable tableSample) {
		CustomeScrollTableFactory.tableSample = tableSample;
	}

}
