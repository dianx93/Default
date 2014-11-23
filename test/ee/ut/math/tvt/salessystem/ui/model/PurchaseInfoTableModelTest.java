package ee.ut.math.tvt.salessystem.ui.model;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class PurchaseInfoTableModelTest {
	
	private PurchaseInfoTableModel model;
	private StockItem stockItem;

	@Before
	public void setUp() throws Exception {
		model = new PurchaseInfoTableModel();
		stockItem = new StockItem(50l, "TestItem", "for testing", 10.90, 10);
	}

	@Test
	public void testAddNewItem() {
		model.addItem(new SoldItem(stockItem, 1));
	}
	
	@Test
	public void testAddExistingItem() {
		model.addItem(new SoldItem(stockItem, 1));
		model.addItem(new SoldItem(stockItem, 3));
		Assert.assertEquals(1, model.getRowCount());
	}

	@Test
	public void testAddMultipleItems() {
		StockItem item2 = new StockItem(51l, "TestItem2", "for testing", 1.2, 5);
		model.addItem(new SoldItem(item2, 1));
		model.addItem(new SoldItem(stockItem, 2));
		Assert.assertEquals(2, model.getRowCount());
	}
	
	@Test
	public void testGetColumnValue() {
		SoldItem item = new SoldItem(stockItem, 4); 
		model.addItem(item);
		Assert.assertEquals(item.getId(), model.getColumnValue(item, 0));
		Assert.assertEquals("TestItem", model.getColumnValue(item, 1));
		Assert.assertEquals(10.90, model.getColumnValue(item, 2));
		Assert.assertEquals(4, model.getColumnValue(item, 3));
		Assert.assertEquals(4*10.90, model.getColumnValue(item, 4));
		
	}
	
	@Test
	public void testGetSoldItems(){
		List<SoldItem> list = new ArrayList<SoldItem>();
		Assert.assertEquals(list, model.getSoldItems());
		SoldItem item = new SoldItem(stockItem, 4);
		model.addItem(item);
		list.add(item);
		Assert.assertEquals(list, model.getSoldItems());
	}
	
}
