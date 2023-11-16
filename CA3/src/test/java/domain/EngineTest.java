package domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EngineTest {

    private Engine engine;

    @Before
    public void setUp() {
        engine = new Engine();
    }
    @Test
    public void testGetAverageOrderQuantityByCustomer() {
        Order order1 = new Order(1, 1, 10, 5);
        Order order2 = new Order(2, 1, 15, 7);
        engine.addOrderAndGetFraudulentQuantity(order1);
        engine.addOrderAndGetFraudulentQuantity(order2);

        int average = engine.getAverageOrderQuantityByCustomer(1);
        assertEquals(6, average);
    }
    @Test
    public void testGetAverageOrderQuantityByCustomerEmptyOrder() {
        int average = engine.getAverageOrderQuantityByCustomer(1);
        assertEquals(0, average);
    }

    @Test
    public void testGetQuantityPatternByPriceEmptyOrderHistory() {
        // Scenario: Empty order history
        int result = engine.getQuantityPatternByPrice(10);
        assertEquals(0, result);
    }

    @Test
    public void testGetQuantityPatternByPriceNoPatternDetected() {
        // Scenario: No consistent pattern detected
        List<Order> orderHistory = new ArrayList<>();
        orderHistory.add(new Order(1, 1, 10, 5));
        orderHistory.add(new Order(2, 2, 12, 8));
        orderHistory.add(new Order(3, 3, 14, 6));

        engine.orderHistory = (ArrayList<Order>) orderHistory;

        int result = engine.getQuantityPatternByPrice(10);
        assertEquals(0, result);
    }
    @Test
    public void testGetQuantityPatternByPriceConsistentPatternDetected() {
        // Scenario: Consistent pattern detected
        List<Order> orderHistory = new ArrayList<>();
        orderHistory.add(new Order(3, 3, 10, 6));
        orderHistory.add(new Order(4, 4, 10, 9));
        orderHistory.add(new Order(5, 4, 10, 12));

        engine.orderHistory = (ArrayList<Order>) orderHistory;

        int result = engine.getQuantityPatternByPrice(10);
        assertEquals(3, result); // Expected pattern: 9 - 6 = 3
    }
    @Test
    public void testGetQuantityPatternByPriceConsistentPattern2Detected() {
        // Scenario: inConsistent pattern detected
        List<Order> orderHistory = new ArrayList<>();
        orderHistory.add(new Order(3, 3, 10, 6));
        orderHistory.add(new Order(4, 4, 10, 9));
        orderHistory.add(new Order(5, 4, 10, 9));
        engine.orderHistory = (ArrayList<Order>) orderHistory;

        int result = engine.getQuantityPatternByPrice(10);
        assertEquals(0, result); // Expected pattern: 9 - 6 = 3
    }

    @Test
    public void testGetCustomerFraudulentQuantity() {;
        engine.orderHistory.add(new Order(1, 1, 100, 20));

        // Scenario where the order quantity is greater than the average
        Order order = new Order(2, 1, 100, 25);
        int result = engine.getCustomerFraudulentQuantity(order);
        assertEquals(5, result);
    }

    @Test
    public void testGetCustomerFraudulentQuantityNotFraudulent() {
        engine.orderHistory.add(new Order(1, 1, 100, 20));

        // Scenario where the order quantity is not greater than the average
        Order order = new Order(2, 1, 100, 15);
        int result = engine.getCustomerFraudulentQuantity(order);
        assertEquals(0, result);
    }
    @Test
    public void testAddOrderAndGetFraudulentQuantity_OrderAlreadyExists() {
        Order order = new Order(1, 1, 100, 10);


        engine.orderHistory.add(order);

        int result = engine.addOrderAndGetFraudulentQuantity(order);
        assertEquals(0, result);

    }

    @Test
    public void testAddOrderAndGetFraudulentQuantity_CustomerFraudulent() {
        engine.orderHistory.add(new Order(1, 1, 100, 20));


        Order order = new Order(2, 1, 100, 25);
        int result = engine.addOrderAndGetFraudulentQuantity(order);
        assertEquals(5, result);
    }

    @Test
    public void testAddOrderAndGetFraudulentQuantity_QuantityPatternByPrice() {
        Engine engine = new Engine();
        engine.orderHistory.add(new Order(1, 1, 100, 10));

        Order order = new Order(2, 2, 100, 15);
        int result = engine.addOrderAndGetFraudulentQuantity(order);
        assertEquals(15, result);
    }

    @Test
    public void testAddOrderAndGetFraudulentQuantity_NoFraudulentQuantity() {
        Engine engine = new Engine();
        engine.orderHistory.add(new Order(1, 1, 100, 20));


        Order order = new Order(2, 1, 100, 15);
        int result = engine.addOrderAndGetFraudulentQuantity(order);
        assertEquals(0, result);
    }
}
