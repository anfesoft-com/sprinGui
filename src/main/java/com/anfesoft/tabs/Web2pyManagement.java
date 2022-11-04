package com.anfesoft.tabs;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.anfesoft.commons.FormatHelpers;
import com.anfesoft.jcomponents.CustomeScrollTableFactory;
import com.anfesoft.training.Queries;

public class Web2pyManagement extends CustomeScrollTableFactory implements ActionListener{
   private static final long serialVersionUID = 1L;
   private final String ID = "ID";
   private final String BODY = "Body";
   private final String TAGS = "Tags";
   private final String URL = "Url";
   private final String TITLE = "TITLE";
   
   private final String[] columnsSets = new String[] { TITLE };
   private Object[][] data = FormatHelpers.getWeb2pyDocumentationTitles(columnsSets); 
   private JTextField textSearchField =  new JTextField();
   private JLabel htmlContent = new JLabel("Double klick data view/text");
   private JPopupMenu popupMenu = new JPopupMenu();
   

   
   JTabbedPane pestanas = new JTabbedPane();
   
   public Object[][] getData() {
	return data;
}

public void filterByTitle(String searchText) 
{
Queries.getWeb2pyDocumentationTitlesSearch(tabelModel, searchText);
}

private final DefaultTableModel tabelModel = new DefaultTableModel(data , columnsSets);
   private JTable tableTitles = new JTable(tabelModel);
   private JButton searchBtn = new JButton("+");
   private JEditorPane html = new JEditorPane();
   
   public JTable createJtableView()
   {
	   return tableTitles;
   }
   
   public JPanel createJPanelViewTitles()
   {
	   searchBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		filterByTitle(textSearchField.getText());	
			
			
		}
	});
	   this.tableTitles.setFont(new Font("Times New Roman", Font.PLAIN, 13));
	   this.tableTitles.setAutoCreateRowSorter(true);
	   
	   

	   
	   
	   
	    JScrollPane scrollPane = new JScrollPane(html);
	    scrollPane.setPreferredSize(new Dimension(800, 230));
	    add(scrollPane);
	    html.scrollToReference("three");
	   

	   pestanas.addTab("View", null, createJscrollTable(), null);
	   pestanas.addTab("Content", null, scrollPane, null);
	   
	   JPanel x = new JPanel(new GridBagLayout());
	   
	   
	   
       x.add(new JLabel("Search"), new GridBagConstraints(0, 0, 1, 1, 0.5, 0.3, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
       x.add(textSearchField, new GridBagConstraints(1, 0, 1, 1, 0.5, 0.3, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
       x.add(searchBtn, new GridBagConstraints(2, 0, 1, 1, 0.5, 0.3, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
       x.add(pestanas, new GridBagConstraints(0, 1, 3, 1, 0.5, 0.3, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
	   return x;
   }

@Override
public JScrollPane createJscrollTable() {
	this.tableTitles.setFont(new Font("Times New Roman", Font.PLAIN, 13));
	this.tableTitles.setAutoCreateRowSorter(true);
	JScrollPane scrollTableSample = new JScrollPane(tableTitles);
	 JMenuItem menuItem1 = new JMenuItem("View");
	 menuItem1.addActionListener(this);
	 popupMenu.add(menuItem1);
	this.tableTitles.setComponentPopupMenu(popupMenu);
	return scrollTableSample;
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getActionCommand().equals("View"))
	{	
		String selectedTitle = tableTitles.getValueAt(tableTitles.getSelectedRow(), 0).toString();
		String entryContent = Queries.getWeb2pyDocumentationGetBodyByTitle(selectedTitle);
			this.htmlContent.setText("<html>"+entryContent+"</html>");
		   html.setContentType("text/html");
		   StringReader reader = new StringReader(htmlContent.getText());
		        try {
					html.read(reader, null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

	}
	
}
	
	

}
