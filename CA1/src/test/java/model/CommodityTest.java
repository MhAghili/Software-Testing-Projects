package model;

import exceptions.NotInStock;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CommodityTest {
    private Commodity commodity;

    @Before
    public void setUp() {
        commodity = new Commodity();
        commodity.setInStock(10);
        commodity.setInitRate(3.0f);
    }

    @Test
    public void testUpdateInStock_StockIncrease() throws NotInStock {
        commodity.updateInStock(5);
        assertEquals(15, commodity.getInStock());
    }

    @Test
    public void testUpdateInStock_StockDecrease() throws NotInStock {
        commodity.updateInStock(-5);
        assertEquals(5, commodity.getInStock());
    }

    @Test(expected = NotInStock.class)
    public void testUpdateInStock_StockDecreaseWithException() throws NotInStock {
        commodity.updateInStock(-15);
    }

    @Test
    public void testAddRate() {
        commodity.addRate("user1", 4);
        assertEquals(4, commodity.getUserRate().get("user1").intValue());
        assertEquals(3.5f, commodity.getRating(), 0.01);
    }

    @Test
    public void testCalcRatingIndirectly() {
        commodity.addRate("user1", 4);
        commodity.addRate("user2", 5);
        assertEquals((9 + commodity.getInitRate()) / 3, commodity.getRating(), 0.01);
    }
}
