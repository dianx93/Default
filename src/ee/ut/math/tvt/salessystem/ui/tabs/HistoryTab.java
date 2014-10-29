package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;

import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {
    
    // TODO - implement!
	private SalesSystemModel model;
	
	public HistoryTab(SalesSystemModel model) {
	    this.model = model;
	  }

	//model = HistoryTableModel
    public HistoryTab() {} 
    
    public Component draw() {
        JPanel panel = new JPanel();
        //JTable table = new JTable(model.getHistoryTableModel());
        //panel.add(table);
        
        // TODO - Sales history tabel
        return panel;
    }
}