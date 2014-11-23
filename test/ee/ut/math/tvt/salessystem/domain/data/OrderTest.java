package ee.ut.math.tvt.salessystem.domain.data;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {

	StockItem item1;
	StockItem item2;
	
	@Before
	public void setUp() throws Exception {
		item1 = new StockItem(1l, "TestItem1", "for testing", 10.90, 2);
		item2 = new StockItem(2l, "TestItem2", "for testing", 1.20, 5);
		
	}

	@Test
	public void testAddSoldItem() {
		SoldItem item = new SoldItem(item1, 1);
		List<SoldItem> soldItems = new ArrayList<SoldItem>();
		soldItems.add(item);
		SoldItem sItem = new SoldItem(item2, 1);
		Order order = new Order(soldItems, -1l);
		Assert.assertEquals(10.90, order.getSum());
		order.addSoldItem(sItem);
		Assert.assertEquals(12.10, order.getSum());
	}
	
	@Test
	public void testGetSumWithNoItems() {
		List<SoldItem> soldItems = new ArrayList<SoldItem>();
		Order order = new Order(soldItems, -1l);
		Assert.assertEquals(0, order.getSum(), 0.0001);
			
	}
	
	@Test
	public void testGetSumWithOneItem() {
		List<SoldItem> soldItems = new ArrayList<SoldItem>();
		SoldItem item = new SoldItem(item1, 1);
		soldItems.add(item);
		Order order = new Order(soldItems, -1l);
		Assert.assertEquals(10.90, order.getSum(), 0.0001);
		
	}
	
	@Test
	public void testGetSumWithMultipleItems() {
		List<SoldItem> soldItems = new ArrayList<SoldItem>();
		SoldItem item = new SoldItem(item1, 2);
		SoldItem sItem = new SoldItem(item2, 1);
		soldItems.add(item);
		soldItems.add(sItem);
		Order order = new Order(soldItems, -1l);
		Assert.assertEquals(23, order.getSum(), 0.0001);
		
	}
}
