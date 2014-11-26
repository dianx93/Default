package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labeled "History" in the menu).
 */
public class HistoryTab {
    
	private SalesSystemModel model;
	private final SalesDomainController domainController;
	private JFrame infoFrame;
	
	public HistoryTab(SalesDomainController domainController, SalesSystemModel model) {
	    this.model = model;
	    this.domainController = domainController;
	  }

	//model = HistoryTableModel

    
    public Component draw() {
        JPanel panel = new JPanel();
        final JTable table = new JTable(model.getHistoryTableModel());
        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
            	if(event.getValueIsAdjusting()){
            		//so it would not trigger when no line is selected but purchase has been made.
            		if(table.getSelectedRow()>-1)
            			infoFrame = openOrderInfo(model.getHistoryTableModel().getOrder(table.getSelectedRow()));
            	}
            }
            
            
            /**
             * When individual order is clicked, creates new window to show individual SoldItems in it
             * @author Ott
             * @param products
             * @return
             */
            private JFrame openOrderInfo(Order order) {
            	
            	final JFrame infoFrame = new JFrame("Order info");
            	JPanel infoJPanel = new JPanel();
            	JTable table = new JTable(model.getCurrentOrderTableModel());
                JScrollPane scrollPane = new JScrollPane(table);
                infoJPanel.add(scrollPane, getBacketScrollPaneConstraints());
            	infoFrame.add(infoJPanel);
            	for (SoldItem item : domainController.loadSoldItems()) {
					if (item.getSale_id() == order.getId()) {
						model.getCurrentOrderTableModel().addItem(item);
					}
				}
            	JButton closeButton = new JButton("   Close   ");
 	            closeButton.addActionListener(new ActionListener() {
 	                public void actionPerformed(ActionEvent e) {
 	                  closeButtonClicked();
 	                  model.getCurrentOrderTableModel().clear();
 	                }

 	                private void closeButtonClicked() {
 	              	  infoFrame.dispose();
 	                }
 	          	
 	              });
 	            infoJPanel.add(closeButton);
 	            infoFrame.pack();
 	            infoFrame.setVisible(true);
 	            return infoFrame;
            }
            
            private GridBagConstraints getBacketScrollPaneConstraints() {
                GridBagConstraints gc = new GridBagConstraints();

                gc.fill = GridBagConstraints.BOTH;
                gc.weightx = 1.0;
                gc.weighty = 1.0;

                return gc;
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
        
        return panel;
    }

}

