package com.anfesoft.training;

import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import com.anfesoft.commons.FormatHelpers;

public class Cobox {

	static private String selectedString(ItemSelectable is) {
		Object selected[] = is.getSelectedObjects();
		return ((selected.length == 0) ? "null" : (String) selected[0]);
	}

	public static JComboBox<String> combobox(String args[]) {
		String labels[] = FormatHelpers.makeSimpleArray(Queries.getAccountsData());
		JComboBox<String> comboBox = new JComboBox<String>(labels);
		ItemListener itemListener = new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				int state = itemEvent.getStateChange();
				System.out.println((state == ItemEvent.SELECTED) ? "Selected" : "Deselected");
				System.out.println("Item: " + itemEvent.getItem());
				ItemSelectable is = itemEvent.getItemSelectable();
				System.out.println(", Selected: " + selectedString(is));
			}
		};
		comboBox.addItemListener(itemListener);
		return comboBox;

	}
}
