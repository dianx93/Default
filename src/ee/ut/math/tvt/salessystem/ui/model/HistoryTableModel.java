package ee.ut.math.tvt.salessystem.ui.model;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.Order;

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
	
	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final Order order : rows) {
			buffer.append(order.getDate() + "\t");
			buffer.append(order.getTime() + "\t");
			buffer.append(order.getSum() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}
	
	public Order getOrder(int rownr){
		return rows.get(rownr);
	}

	/**
	 * Returns Id of last Order for makeshift autoincrement purposes
	 * @author Ott
	 * @return
	 */
	public Long getLastId() {
		if(rows.size()<1){return 0l;} //?
		else return rows.get(rows.size()-1).getId();
	}

}
