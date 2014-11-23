package ee.ut.math.tvt.salessystem.ui.model;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.Order;

public class HistoryTableModelTest {

	private HistoryTableModel model;
	private Order order;
	
	@Before
	public void setUp() throws Exception {
		model = new HistoryTableModel();
		order = new Order(20.0, "products info for testing", model.getLastId());
		
	}

	@Test
	public void testAddItem() {
		model.addItem(new Order(20.0, "info", model.getLastId()));
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddNullItem() {
		model.addItem(null);
	}
	
	@Test
	public void testGetColumnValue(){
		model.addItem(new Order(20.0, "info", model.getLastId()));
		Assert.assertEquals(20.0, model.getColumnValue(model.getOrder(0), 2));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonExistingColumnValue(){
		model.addItem(new Order(20.0, "info", model.getLastId()));
		model.getColumnValue(model.getOrder(0), 3);
	}
	
	@Test
	public void testGetOrder(){
		Order order = new Order(20.0, "info", model.getLastId());
		model.addItem(order);
		Assert.assertEquals(order, model.getOrder(0));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetNonExistingOrder(){
		model.getOrder(5);
	}
	
	@Test
	public void testGetLastId(){
		Assert.assertEquals((Long)0l, (Long)model.getLastId());
		model.addItem(new Order(20.0, "info", model.getLastId()));
		Assert.assertEquals((Long)1l, (Long)model.getLastId());
	}

}
