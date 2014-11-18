package ee.ut.math.tvt.salessystem.domain.data;

import static org.junit.Assert.*;
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

	// TODO: check if correct
	@Test
	public void testClone() {
		StockItem item = new StockItem(1l, "TestItem", "for testing", 10.90, 2);
		Object newItem = item.clone();
		Assert.assertEquals(item, newItem);
		
	}
	
	// TODO: check if correct
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

}
