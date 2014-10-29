package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

import ee.ut.math.tvt.salessystem.ui.model.HistoryTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {
    
    // TODO - implement!
	private SalesSystemModel model;
	private static HistoryTableModel Hmodel = new HistoryTableModel();
	
	public HistoryTab(SalesSystemModel model) {
	    this.model = model;
	  }

	//model = HistoryTableModel
    public HistoryTab() {} 
    
    public Component draw() {
        JPanel panel = new JPanel();
        //This line breaks the code:
        //JTable table = new JTable(model.getHistoryTableModel());
        final JTable table = new JTable(Hmodel);
        //panel.add(table);
        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
            	openPurchaseInfo(Hmodel.getOrder(table.getSelectedRow()).getProducts());
            }

			private void openPurchaseInfo(String info) {
				
				// TODO Auto-generated method stub
				
			}
        });

        JScrollPane scrollPane = new JScrollPane(table);

        GridBagConstraints gc = new GridBagConstraints();
        GridBagLayout gb = new GridBagLayout();
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;
        gc.weighty = 1.0;

        panel.setLayout(gb);
        panel.add(scrollPane, gc);

        panel.setBorder(BorderFactory.createTitledBorder("Purchase History"));
        
        // TODO - Sales history tabel
        return panel;
    }

	public static HistoryTableModel getHmodel() {
		return Hmodel;
	}
}

