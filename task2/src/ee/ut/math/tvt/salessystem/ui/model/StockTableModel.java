package ee.ut.math.tvt.salessystem.ui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

/**
 * Stock item table model.
 */
public class StockTableModel extends SalesSystemTableModel<StockItem> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(StockTableModel.class);

	protected List<StockItem> rows;
	
	public StockTableModel() {
		super(new String[] {"Id", "Name", "Price", "Quantity"});
		rows = new ArrayList<StockItem>();
	}

	@Override
	protected Object getColumnValue(StockItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			return item.getPrice();
		case 3:
			return item.getQuantity();
		}
		throw new IllegalArgumentException("Column index out of range");
	}
	
	public boolean hasEnoughInStock(StockItem item, int quantity) {
	    for(StockItem i : this.rows) {
	        if (i.getId().equals(item.getId())) {
	            return (i.getQuantity() >= quantity);
	        }
	    }
	    return false;
	}
	
	public boolean validateNameUniqueness(String newName) {
	    for (StockItem item : rows) {
	        log.debug(" === Comparing: " + newName + " vs. " + item.getName());
	        
	        if (newName.equals(item.getName())) {
	            return false;
	        }
	    }
	    return true;
	}

	public List<StockItem> getTableRows() {
		return rows;
	}

}
