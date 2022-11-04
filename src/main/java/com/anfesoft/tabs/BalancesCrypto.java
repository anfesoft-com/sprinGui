package com.anfesoft.tabs;


import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjuster;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.anfesoft.commons.FormatHelpers;
import com.anfesoft.commons.Konstants;
import com.anfesoft.jcomponents.CustomeScrollTableFactory;
import com.anfesoft.training.Queries;

import net.bytebuddy.utility.dispatcher.JavaDispatcher.Instance;

public class BalancesCrypto extends CustomeScrollTableFactory implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private final String CALCULATE_TOTALS = "CALCULATE_TOTALS";
	private final String UPDATE_ROW = "UPDATE_ROW";
	
	
	private final String ID = "Id";
	private final String ACCOUNT = "Account";
	private final String START = "Start";
	private final String NETTO_REALIZED = "Net win";
	private final String BRUTTO_REALIZED = "Brut win";
	
    private static final DecimalFormat df = new DecimalFormat("0.00");

	
	private final String[] columnsSets = new String[] { ID, ACCOUNT, START, NETTO_REALIZED, BRUTTO_REALIZED };
	
	private final Object[][] data = FormatHelpers.getTableTradingAccounts(columnsSets);
	private final DefaultTableModel tabelModel = new DefaultTableModel(data , columnsSets);
	private final JTable tableSample = new JTable(tabelModel);
	private JLabel balancesLbl = new JLabel("General Balance:");
	private JLabel sincelbl = new JLabel("Since:");
	
	//CONTEXT MENU Definition
	private JPopupMenu popupMenu = new JPopupMenu();
	private JMenuItem menuItem1 = new JMenuItem(CALCULATE_TOTALS);

	
	private JMenuItem menuItem2 = new JMenuItem(UPDATE_ROW);
	
	@Override
	public JScrollPane createJscrollTable() {
		calculateBalance();
		this.tableSample.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		this.tableSample.setAutoCreateRowSorter(true);
		JScrollPane scrollTableSample = new JScrollPane(tableSample);
		
		//Context menu injection
		menuItem1.addActionListener(this);
		popupMenu.add(menuItem1);
		
		menuItem2.addActionListener(this);
		popupMenu.add(menuItem2);
		
		this.tableSample.setComponentPopupMenu(popupMenu);
		return scrollTableSample;
	}

	public JPanel createComposedComponent()
	{
		setSincelbl();
		JPanel jp = new JPanel(new GridBagLayout());
		JTextField searchBox = new JTextField();
		JButton btnSearch = new JButton("+");
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			Queries.getBalancesCryptoSearch(tableSample,searchBox.getText());
				
			}
		});
		/*GridBagConstraints(0, 0, 1, 1, 1.0, 0.6,
		 * the first 0 applies to the column
		 * the second field applies to the row.
		 * the thirth applies to the colspan
		 * 
		 */
		jp.add(sincelbl, new GridBagConstraints(0, 0, 0, 1, 0.5, 0.3, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
		jp.add(balancesLbl, new GridBagConstraints(0, 1, 1, 1, 0.5, 0.3, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		jp.add(searchBox, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
		jp.add(btnSearch, new GridBagConstraints(2, 0, 1, 1, 0.5, 0.3, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		jp.add(this.createJscrollTable(), new GridBagConstraints(0, 2, 3, 1,0.5, 0.3, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
		return jp;
	}

	 private void calculateBalance()
	{
		 Double profitOrloss = 0.0;
			Double total = 0.0;
			Double initial = 0.0;
			for(Object[] x: FormatHelpers.getTableTradingAccounts(columnsSets))
			{
				total += (Double) x[4];
				initial += (Double) x[2];
			}
			balancesLbl.setText("General Balance:"+df.format(total - initial));

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(CALCULATE_TOTALS))
		{
			calculateBalance();
		}		
		
		if(e.getActionCommand().equals(UPDATE_ROW))
		{
		 Queries.updateRow(tableSample, tableSample.getSelectedRow());	
		}
		
	}

	public JLabel getSincelbl() {
		return sincelbl;
	}

	public void setSincelbl() {
		Clock testClock = Clock.fixed(Instant.EPOCH, ZoneOffset.UTC);
		LocalDate clockdate = LocalDate.now(testClock);
		
		LocalDate nextMonday = clockdate.with(clockdate);
		this.sincelbl.setText(""+clockdate.toString()+"");
	}

}
