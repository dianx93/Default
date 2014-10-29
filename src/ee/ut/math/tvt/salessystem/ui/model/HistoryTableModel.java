package ee.ut.math.tvt.salessystem.ui.model;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.DisplayableItem;
import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class HistoryTableModel extends SalesSystemTableModel<Order> {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(HistoryTableModel.class);
	
	public HistoryTableModel() {
		super(new String[] {"Date", "Time", "Total price"});
	}

	@Override
	protected Object getColumnValue(Order item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getDate();
		case 1:
			return item.getTime();
		case 2:
			return item.getSum();
		}
		throw new IllegalArgumentException("Column index out of range");
	}
	
	public void addItem(final Order order) {
		rows.add(order);
		log.debug("Added " + order.getDate() + " " + order.getTime()
				+ " order with total sum of " + order.getSum());
		fireTableDataChanged();
	}

}
