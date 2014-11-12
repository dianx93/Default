package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

public class StockTab {

  private JButton addItem;

  private SalesSystemModel model;

  private final SalesDomainController domainController;

  private JFrame frame;
  
  private JTextField nameField;
  private JTextField priceField;
  private JTextField quantityField;
  private JTextField barcodeField;
  
  
  public StockTab(SalesDomainController domainController, SalesSystemModel model) {
    this.model = model;
    this.domainController = domainController;
  }

  // warehouse stock tab - consists of a menu and a table
  public Component draw() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    GridBagLayout gb = new GridBagLayout();
    GridBagConstraints gc = new GridBagConstraints();
    panel.setLayout(gb);

    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 0d;

    panel.add(drawStockMenuPane(), gc);

    gc.weighty = 1.0;
    gc.fill = GridBagConstraints.BOTH;
    panel.add(drawStockMainPane(), gc);
    return panel;
  }

  // warehouse menu
  private Component drawStockMenuPane() {
    JPanel panel = new JPanel();

    GridBagConstraints gc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();

    panel.setLayout(gb);

    gc.anchor = GridBagConstraints.NORTHWEST;
    gc.weightx = 0;

    addItem = new JButton("Add");
    gc.gridwidth = GridBagConstraints.RELATIVE;
    gc.weightx = 1.0;
    panel.add(addItem, gc);
    
    addItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            addItemEventHandler();
        }	
    });
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    return panel;
  }


  protected void addItemEventHandler() {
	  openAddItemWindow();
	
}

//window that asks what item to add to stock
private void openAddItemWindow() {
	frame = new JFrame("Add item");
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((screen.width - 450) / 2, (screen.height - 200) / 2);
    JPanel addItemJPanel = new JPanel();
    GridLayout addItemGridLayout = new GridLayout(5,2);
    frame.add(addItemJPanel);
    addItemJPanel.setLayout(addItemGridLayout);
    addItemJPanel.setBorder(new EmptyBorder(10,10,10,10));
    addItemJPanel.add(new JLabel("Barcode:   ", SwingConstants.RIGHT));
    barcodeField = new JTextField("");
    addItemJPanel.add(barcodeField);
    addItemJPanel.add(new JLabel("Name:  ", SwingConstants.RIGHT));
    nameField = new JTextField("");
    addItemJPanel.add(nameField);
    addItemJPanel.add(new JLabel("Price:  ", SwingConstants.RIGHT));
    priceField = new JTextField(20);
    addItemJPanel.add(priceField);
    addItemJPanel.add(new JLabel("Quantity:  ", SwingConstants.RIGHT));
    quantityField = new JTextField(20);
    addItemJPanel.add(quantityField);
    addItemJPanel.add(createCancelAddItemButton());
    addItemJPanel.add(createAddItemButton());
    frame.pack();
    frame.setVisible(true);
	
}

//add item window buttons

private JButton createCancelAddItemButton() {
    JButton b = new JButton("Cancel");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        cancelAddItemButtonClicked();
      }

      private void cancelAddItemButtonClicked() {
    	  frame.dispose();
      }
	
    });
    return b;
}

private JButton createAddItemButton() {
    JButton b = new JButton("Add");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  addItemButtonClicked();
      }
      
      //adds item that has been given in fields
	private void addItemButtonClicked() {
		String itemName = nameField.getText();
		double itemPrice;
		int itemQuantity;
		long itemBarcode;
		if(!barcodeField.getText().equals("") || !nameField.getText().equals("")){
			try {
				itemPrice = Double.parseDouble(priceField.getText());
			} catch (NumberFormatException ex) {
	            itemPrice = 0.0;
	        }
			try {
				itemQuantity = Integer.parseInt(quantityField.getText());
			} catch (NumberFormatException ex) {
				itemQuantity = 1;
			}
			try {
				itemBarcode = Long.parseLong(barcodeField.getText());
			} catch (NumberFormatException ex) {
				itemBarcode = model.getWarehouseTableModel().getRowCount()+1;
				for(int i = 1; i<=model.getWarehouseTableModel().getRowCount(); i++){
					try{
						model.getWarehouseTableModel().getItemById(i);
					} catch(NoSuchElementException ex1){
						itemBarcode=i;
						break;
					}
				}
			}
			StockItem addedItem = new StockItem(itemBarcode, itemName, "", itemPrice, itemQuantity);
			model.getWarehouseTableModel().addItem(addedItem);
			domainController.addItem(addedItem); //works only if item is not in database TODO: FIX
			
  		}
		frame.dispose();
	}
    });
    return b;
} 

// table of the warehouse stock
  private Component drawStockMainPane() {
    JPanel panel = new JPanel();

    JTable table = new JTable(model.getWarehouseTableModel());

    JTableHeader header = table.getTableHeader();
    header.setReorderingAllowed(false);

    JScrollPane scrollPane = new JScrollPane(table);

    GridBagConstraints gc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();
    gc.fill = GridBagConstraints.BOTH;
    gc.weightx = 1.0;
    gc.weighty = 1.0;

    panel.setLayout(gb);
    panel.add(scrollPane, gc);

    panel.setBorder(BorderFactory.createTitledBorder("Warehouse status"));
    return panel;
  }

}
