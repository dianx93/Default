package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.panels.PurchaseItemPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.EventListener;
import java.util.List;

import javafx.scene.input.KeyCode;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "Point-of-sale" in the menu).
 */
public class PurchaseTab {

  private static final Logger log = Logger.getLogger(PurchaseTab.class);

  private final SalesDomainController domainController;

  private JButton newPurchase;

  private JButton submitPurchase;

  private JButton cancelPurchase;

  private PurchaseItemPanel purchasePane;

  private SalesSystemModel model;
  
  private JFrame frame;
  
  private JLabel sumField = new JLabel();
  
  private JTextField paymentAmountField = new JTextField();
  
  private JLabel changeAmountField = new JLabel();


  public PurchaseTab(SalesDomainController controller,
      SalesSystemModel model)
  {
    this.domainController = controller;
    this.model = model;
  }


  /**
   * The purchase tab. Consists of the purchase menu, current purchase dialog and
   * shopping cart table.
   */
  public Component draw() {
    JPanel panel = new JPanel();

    // Layout
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    panel.setLayout(new GridBagLayout());

    // Add the purchase menu
    panel.add(getPurchaseMenuPane(), getConstraintsForPurchaseMenu());

    // Add the main purchase-panel
    purchasePane = new PurchaseItemPanel(model);
    panel.add(purchasePane, getConstraintsForPurchasePanel());

    return panel;
  }




  // The purchase menu. Contains buttons "New purchase", "Submit", "Cancel".
  private Component getPurchaseMenuPane() {
    JPanel panel = new JPanel();

    // Initialize layout
    panel.setLayout(new GridBagLayout());
    GridBagConstraints gc = getConstraintsForMenuButtons();

    // Initialize the buttons
    newPurchase = createNewPurchaseButton();
    submitPurchase = createConfirmButton();
    cancelPurchase = createCancelButton();

    // Add the buttons to the panel, using GridBagConstraints we defined above
    panel.add(newPurchase, gc);
    panel.add(submitPurchase, gc);
    panel.add(cancelPurchase, gc);

    return panel;
  }


  // Creates the button "New purchase"
  private JButton createNewPurchaseButton() {
    JButton b = new JButton("New purchase");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        newPurchaseButtonClicked();
      }
    });

    return b;
  }

  // Creates the "Confirm" button
  private JButton createConfirmButton() {
    JButton b = new JButton("Confirm");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        submitPurchaseButtonClicked();
      }
    });
    b.setEnabled(false);

    return b;
  }


  // Creates the "Cancel" button
  private JButton createCancelButton() {
    JButton b = new JButton("Cancel");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        cancelPurchaseButtonClicked();
      }
    });
    b.setEnabled(false);

    return b;
  }



  //payment window Cancel and Accept buttons
  
  private JButton createCancelPaymentButton() {
	    JButton b = new JButton("Cancel");
	    b.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        cancelPaymentButtonClicked();
	      }

		private void cancelPaymentButtonClicked() {
			frame.dispose();
		}
	    });
	    b.setEnabled(false);

	    return b;
} 

  private JButton createPaymentAcceptButton() {
	    JButton b = new JButton("Accept");
	    b.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        acceptPaymentButtonClicked();
	      }

		private void acceptPaymentButtonClicked() {
			double payed; 
			try {
        	    payed = Double.parseDouble(paymentAmountField.getText());
        	    if(payed>=Double.parseDouble(sumField.getText())){
        	    	try {
        	    		frame.dispose();
        				log.info("Sale accepted");
        				domainController.submitCurrentPurchase(
        		              model.getCurrentPurchaseTableModel().getTableRows()
        		          );
        				//TODO: add to history
        				endSale();
        				model.getCurrentPurchaseTableModel().clear();
        		    } catch (VerificationFailedException e1) {
        		      log.error(e1.getMessage());
        		    }
        	    }
			}
			catch(NumberFormatException ex){}
		}
	    });
	    b.setEnabled(false);

	    return b;
	  }

  /* === Event handlers for the menu buttons
   *     (get executed when the buttons are clicked)
   */


  /** Event handler for the <code>new purchase</code> event. */
  protected void newPurchaseButtonClicked() {
    log.info("New sale process started");
    try {
      domainController.startNewPurchase();
      startNewSale();
    } catch (VerificationFailedException e1) {
      log.error(e1.getMessage());
    }
  }


  /**  Event handler for the <code>cancel purchase</code> event. */
  protected void cancelPurchaseButtonClicked() {
    log.info("Sale cancelled");
    try {
      domainController.cancelCurrentPurchase();
      resetStock();
      endSale();
      model.getCurrentPurchaseTableModel().clear();
    } catch (VerificationFailedException e1) {
      log.error(e1.getMessage());
    }
  }


  /** Event handler for the <code>submit purchase</code> event. */
  protected void submitPurchaseButtonClicked() {
	  log.info("Sale submitted");
      frame = createPaymentFrame();
      log.debug("Contents of the current basket:\n" + model.getCurrentPurchaseTableModel());
      
  }



  /* === Helper methods that bring the whole purchase-tab to a certain state
   *     when called.
   */

  // switch UI to the state that allows to proceed with the purchase
  private void startNewSale() {
    purchasePane.reset();

    purchasePane.setEnabled(true);
    submitPurchase.setEnabled(true);
    cancelPurchase.setEnabled(true);
    newPurchase.setEnabled(false);
  }

  // switch UI to the state that allows to initiate new purchase
  private void endSale() {
    purchasePane.reset();

    cancelPurchase.setEnabled(false);
    submitPurchase.setEnabled(false);
    newPurchase.setEnabled(true);
    purchasePane.setEnabled(false);
  }

  private void resetStock() {
	  purchasePane.resetStock();
  }

  
  /* === Next methods just create the layout constraints objects that control the
   *     the layout of different elements in the purchase tab. These definitions are
   *     brought out here to separate contents from layout, and keep the methods
   *     that actually create the components shorter and cleaner.
   */

  private GridBagConstraints getConstraintsForPurchaseMenu() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 0d;

    return gc;
  }


  private GridBagConstraints getConstraintsForPurchasePanel() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.fill = GridBagConstraints.BOTH;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 1.0;

    return gc;
  }


  // The constraints that control the layout of the buttons in the purchase menu
  private GridBagConstraints getConstraintsForMenuButtons() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.weightx = 0;
    gc.anchor = GridBagConstraints.CENTER;
    gc.gridwidth = GridBagConstraints.RELATIVE;

    return gc;
  }
  
  // creates and returns payment window
  
  private JFrame createPaymentFrame(){
	  JFrame frame = new JFrame("Payment");
      sumField = new JLabel();
      paymentAmountField = new JTextField("");
      changeAmountField = new JLabel();
      double sum=0.0;
      List<SoldItem> list = model.getCurrentPurchaseTableModel().getTableRows();
      for(SoldItem i:list){
    	  sum+=i.getSum();
      }
      sumField.setText(sum+"");
      final double finalSum = sum;
      Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
      frame.setLocation((screen.width - 200) / 2, (screen.height - 200) / 2);
      JPanel paymentJPanel = new JPanel();
      GridLayout paymentGridLayout = new GridLayout(4,2);
      frame.add(paymentJPanel);
      paymentJPanel.setLayout(paymentGridLayout);
      paymentJPanel.setBorder(new EmptyBorder(10,10,10,10));
      paymentJPanel.add(new JLabel("Sum:"));
      paymentJPanel.add(sumField);
      paymentJPanel.add(new JLabel("Payed:"));
      paymentJPanel.add(paymentAmountField);
      paymentJPanel.add(new JLabel("Change:"));
      paymentJPanel.add(changeAmountField);

      JButton paymentCancelButton = createCancelPaymentButton();
      final JButton paymentAcceptButton = createPaymentAcceptButton();
      paymentJPanel.add(paymentAcceptButton);
      paymentJPanel.add(paymentCancelButton);
      paymentCancelButton.setEnabled(true);
      paymentAmountField.setText("");
      frame.pack();
      frame.setVisible(true);
      
      // fills ChangeAmountField after ENTER is pressed
      paymentAmountField.addKeyListener(new KeyAdapter()
      {
          public void keyPressed(KeyEvent ke)
          {
        	  if(ke.getKeyCode()==KeyEvent.VK_ENTER){
        		  double payed=0;
            	  try
            	  {
            	    payed = Double.parseDouble(paymentAmountField.getText());
            	    if(payed>=finalSum){
            	    	changeAmountField.setText((double)(Math.round((payed-finalSum)*100))/100+"");
            	    	paymentAcceptButton.setEnabled(true);
            	    }
            	    else{changeAmountField.setText("");}
            	  }
            	  catch(NumberFormatException ex){
            		  changeAmountField.setText("");
            		  
            	  }
        	  }
          }
      });
      
      return frame;

  }


}
