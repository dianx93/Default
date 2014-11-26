package ee.ut.math.tvt.salessystem.domain.data;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StockItemTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUp() {
	}

	@Test
	public void testClone() {
		StockItem item = new StockItem(1l, "TestItem", "for testing", 10.90, 2);
		StockItem newItem = (StockItem) item.clone();
		Assert.assertEquals(item.getName(), newItem.getName());
		Assert.assertEquals(item.getDescription(), newItem.getDescription());
		Assert.assertEquals(item.getPrice(), newItem.getPrice());
		Assert.assertEquals(item.getQuantity(), newItem.getQuantity());
		Assert.assertEquals(item.getClass(), newItem.getClass());
		Assert.assertEquals(item.getId(), newItem.getId());
		
	}
	
	@Test
	public void testGetColumn() {
		StockItem item = new StockItem(1l, "TestItem", "for testing", 10.90, 2);
		Assert.assertEquals(item.getColumn(0), 1l);
		Assert.assertEquals(item.getColumn(1), "TestItem");
		Assert.assertEquals(item.getColumn(2), 10.90);
		Assert.assertEquals(item.getColumn(3), 2);
		exception.expect(RuntimeException.class);
		item.getColumn(4);
		}
	
	@Test
	public void testIsInStock(){
		StockItem item = new StockItem(1l, "TestItem", "for testing", 1.90, 0);
		Assert.assertEquals(false, item.isInStock());
		StockItem item2 = new StockItem(2l, "TestItem2", "for testing", 2.10, 2);
		Assert.assertEquals(true, item2.isInStock());
		
	}
}
