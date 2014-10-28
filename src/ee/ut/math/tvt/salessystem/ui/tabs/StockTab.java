package ee.ut.math.tvt.salessystem.ui.tabs;

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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;


public class StockTab {

  private JButton addItem;

  private SalesSystemModel model;

  private JFrame frame;
  
  private JTextField nameField;
  private JTextField priceField;
  private JTextField quantityField;
  
  
  public StockTab(SalesSystemModel model) {
    this.model = model;
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

private void openAddItemWindow() {
	frame = new JFrame("Add item");
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((screen.width - 450) / 2, (screen.height - 200) / 2);
    JPanel addItemJPanel = new JPanel();
    GridLayout addItemGridLayout = new GridLayout(4,2);
    frame.add(addItemJPanel);
    addItemJPanel.setLayout(addItemGridLayout);
    addItemJPanel.setBorder(new EmptyBorder(10,10,10,10));
    addItemJPanel.add(new JLabel("Name:  ", SwingConstants.RIGHT));
    nameField = new JTextField(20);
    addItemJPanel.add(nameField);
    addItemJPanel.add(new JLabel("Price:  ", SwingConstants.RIGHT));
    priceField = new JTextField(20);
    addItemJPanel.add(priceField);
    addItemJPanel.add(new JLabel("Quantity:  ", SwingConstants.RIGHT));
    quantityField = new JTextField(0);
    addItemJPanel.add(quantityField);
    addItemJPanel.add(createCancelAddItemButton());
    addItemJPanel.add(createAddItemButton());
    frame.pack();
    frame.setVisible(true);
	
}

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
//TODO: addItem();
	private void addItemButtonClicked() {
		//model.getWarehouseTableModel().addItem(stockItem);
		frame.dispose();
	}
    });
    return b;
} 

// table of the wareshouse stock
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
