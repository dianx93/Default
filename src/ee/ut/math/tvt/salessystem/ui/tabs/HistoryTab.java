package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
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
	private JFrame infoFrame;
	
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
            	if(event.getValueIsAdjusting()){
            	infoFrame = openOrderInfo(Hmodel.getOrder(table.getSelectedRow()).getProducts());
            	}
            }

			private JFrame openOrderInfo(String products) {
				final JFrame infoFrame = new JFrame("Order info");
	        	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	            infoFrame.setLocation((screen.width - 450) / 2, (screen.height - 200) / 2);
	            JPanel infoJPanel = new JPanel();
	            infoFrame.add(infoJPanel);
	            FlowLayout infoLayout = new FlowLayout();
	            infoJPanel.setLayout(infoLayout);
	            JTextPane textPane = new JTextPane();
	            textPane.setText(products);
	            textPane.setEditable(false);
	            infoJPanel.add(textPane);
	            infoLayout.setHgap(5);
	            JButton closeButton = new JButton("   Close   ");
	            closeButton.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                  closeButtonClicked();
	                }

	                private void closeButtonClicked() {
	              	  infoFrame.dispose();
	              	  table.clearSelection();
	                }
	          	
	              });
	            infoJPanel.add(closeButton);
	            infoFrame.pack();
	            infoFrame.setVisible(true);
	            return infoFrame;
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

