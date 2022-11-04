package com.anfesoft.tabs;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.anfesoft.commons.FormatHelpers;
import com.anfesoft.jcomponents.CustomeScrollTableFactory;
import com.anfesoft.training.Queries;

public class ImaginalActsViewTable extends CustomeScrollTableFactory {
	private static final long serialVersionUID = 1L;

	private final static String IA = "Imaginal act";
	private final static String DONE = "Done";

	private final static String[] columnsSets = new String[] { DONE, IA };

	private final static Object[][] data = FormatHelpers.getTableImaginalActs(columnsSets);

	private JTable tableSample = new JTable(new BooleanTableModel());
	


	public JPanel createJpaneView() {
		JTextField t = new JTextField("", 40);
		JLabel l = new JLabel("Imaginal Act:");
		JButton b = new JButton("+");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!t.getText().isEmpty()) {
					Queries.insertEntryImaginalActs(t.getText(), tableSample);
				}
			}
		});
		JPanel x = new JPanel(new GridBagLayout());
		x.add(l, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		x.add(t, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		x.add(b, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.6, GridBagConstraints.EAST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		x.add(createJscrollTable(), new GridBagConstraints(0, 1, 3, 1, 1.0, 0.6, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		return x;
	}

	@Override
	public JScrollPane createJscrollTable() {
		this.tableSample.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		this.tableSample.setAutoCreateRowSorter(true);
		this.tableSample.setFillsViewportHeight(true);
		this.tableSample.getColumnModel().getColumn(0).setPreferredWidth(40);
		this.tableSample.getColumnModel().getColumn(0).setMaxWidth(40);
		this.tableSample.setEditingColumn(0);
		this.tableSample.setBackground(Color.cyan);
		this.tableSample.setEnabled(true);
		this.tableSample.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				String textRow = tableSample.getValueAt(tableSample.getSelectedRow(), 1).toString();
				int rowId = Queries.getImaginalActsRowid(textRow);
				if (e.getButton() == 1) {
					if ((boolean) tableSample.getValueAt(tableSample.getSelectedRow(),
							tableSample.getSelectedColumn())) {
						Queries.imaginalActUpdateDone(tableSample.getModel(), false, rowId,tableSample);
					} else if (!(boolean) tableSample.getValueAt(tableSample.getSelectedRow(),
							tableSample.getSelectedColumn())) {
						Queries.imaginalActUpdateDone(tableSample.getModel(), true, rowId,tableSample);
					}
				}
	
				int rcount = Queries.getImaginalActsData().size();
				DefaultTableModel tmodel = new DefaultTableModel(columnsSets,rcount);
				tmodel.setRowCount(0);
				for (Map<String,Object> x : Queries.getImaginalActsData()) {
					tmodel.addRow(new Object[] {(boolean)x.get("DONE"),(String)x.get("ENTRY")});
				}
				
				JTable theTable = new JTable(new BooleanTableModel());
				theTable.setModel(tmodel);
				tableSample.setModel(tmodel);
				tableSample.setFont(new Font("Times New Roman", Font.PLAIN, 13));
				tableSample.setAutoCreateRowSorter(true);
				tableSample.setFillsViewportHeight(true);	
				tableSample.getColumnModel().getColumn(0).setPreferredWidth(40);
				tableSample.getColumnModel().getColumn(0).setMaxWidth(40);
				tableSample.setEditingColumn(0);
				tableSample.setBackground(Color.cyan);
				tableSample.setEnabled(true);
				
				theTable.setModel(tmodel);
				
			}

		});
		JScrollPane scrollTableSample = new JScrollPane(tableSample);

		return scrollTableSample;
	}

	static class BooleanTableModel extends AbstractTableModel {

		public int getRowCount() {
			// TODO Auto-generated method stub
			return data.length;
		}

		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columnsSets.length;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return data[rowIndex][columnIndex];
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// TODO Auto-generated method stub
			return data[0][columnIndex].getClass();
		}

		@Override
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			return columnsSets[column];
		}

	}
}
