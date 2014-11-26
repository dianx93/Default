package ee.ut.math.tvt.salessystem.ui.model;

import java.util.NoSuchElementException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockTableModelTest {
	private StockTableModel model;
	StockItem item;

	@Before
	public void setUp() throws Exception {
		item = new StockItem(50l, "TestItem", "for testing", 10.90, 2);
		model = new StockTableModel();
		model.addItem(item);
	}

	@Test
	public void testValidateNameUniqueness() {
		Assert.assertEquals(true, model.isUnique(item, true));
		StockItem newItem = new StockItem(51l, "TestItem2", "for testing", 1.2, 4);
		Assert.assertEquals(true, model.isUnique(newItem, false));
		
	}
	
	@Test
	public void testHasEnoughInStock() {
		int quantity = (int)model.getColumnValue(item, 3);
		Assert.assertEquals(true, 2<=quantity);
		Assert.assertEquals(false, 3<=quantity);
		
	}
	
	@Test
	public void testGetItemByIdWhenItemExists() {
		Assert.assertEquals(item, model.getItemById(50l));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testGetItemByIdWhenThrowsException() {
		StockItem item = model.getItemById(20);
	}

}
