package model;

import exceptions.CommodityIsNotInBuyList;
import exceptions.InsufficientCredit;
import exceptions.InvalidCreditRange;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("username", "password", "user@example.com", "01/01/1990", "123 Main St");
    }

    @Test
    public void testAddCredit() throws InvalidCreditRange {
        user.addCredit(100.0f);
        assertEquals(100.0f, user.getCredit(), 0.001f);

        assertThrows(InvalidCreditRange.class, () -> user.addCredit(-50.0f));
    }

    @Test
    public void testWithdrawCredit() throws InsufficientCredit {
        user.setCredit(100.0f);
        user.withdrawCredit(50.0f);
        assertEquals(50.0f, user.getCredit(), 0.001f);

        assertThrows(InsufficientCredit.class, () -> user.withdrawCredit(75.0f));
    }

    @Test
    public void testAddBuyItem() {
        Commodity commodity = new Commodity();
        commodity.setId("1");
        commodity.setName("Product");
        commodity.setPrice(10);

        user.addBuyItem(commodity);
        assertEquals(1, user.getBuyList().get("1").intValue());

        user.addBuyItem(commodity);
        assertEquals(2, user.getBuyList().get("1").intValue());
    }

    @Test
    public void testAddPurchasedItem() {
        user.addPurchasedItem("2", 3);
        assertEquals(3, user.getPurchasedList().get("2").intValue());

        user.addPurchasedItem("2", 5);
        assertEquals(8, user.getPurchasedList().get("2").intValue());
    }

    @Test
    public void testRemoveItemFromBuyList() throws CommodityIsNotInBuyList {
        Commodity commodity = new Commodity();
        commodity.setId("3");
        commodity.setName("Product");
        commodity.setPrice(10);

        user.addBuyItem(commodity);

        user.removeItemFromBuyList(commodity);
        assertFalse(user.getBuyList().containsKey("3"));

        assertThrows(CommodityIsNotInBuyList.class, () -> user.removeItemFromBuyList(commodity));
    }

    @ParameterizedTest
    @ValueSource(floats = { -10.0f, -1.0f, -0.01f })
    public void testAddCreditWithInvalidRange(float amount) {
        assertThrows(InvalidCreditRange.class, () -> user.addCredit(amount));
    }

    @ParameterizedTest
    @CsvSource({"50.0, 30.0, 20.0", "100.0, 75.0, 25.0"})
    public void testWithdrawCredit(float initialCredit, float withdrawAmount, float expectedCredit) throws InsufficientCredit {
        user.setCredit(initialCredit);
        user.withdrawCredit(withdrawAmount);
        assertEquals(expectedCredit, user.getCredit(), 0.001f);
    }

}
