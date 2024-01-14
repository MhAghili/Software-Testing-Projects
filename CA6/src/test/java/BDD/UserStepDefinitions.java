package BDD;

import exceptions.CommodityIsNotInBuyList;
import exceptions.InsufficientCredit;
import exceptions.InvalidCreditRange;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Commodity;
import model.User;

import static org.junit.Assert.assertEquals;

public class UserStepDefinitions {
    private User user;

    @Given("the user has a credit of {float}")
    public void givenTheUserHasACreditOf(float initialCredit) {
        user = new User();
        user.setCredit(initialCredit);
    }

    @When("the user adds credit of {float}")
    public void whenTheUserAddsCreditOf(float creditToAdd) throws InvalidCreditRange {
        user.addCredit(creditToAdd);
    }

    @When("the user withdraws credit of {float}")
    public void whenTheUserWithdrawsCreditOf(float creditToWithdraw) throws InsufficientCredit {
        user.withdrawCredit(creditToWithdraw);
    }

    @Then("the new credit should be {float}")
    public void thenTheNewCreditShouldBe(float expectedCredit) {
        assertEquals(expectedCredit, user.getCredit(), 0.01);
    }

    @Given("the user has a buy list with item ID {string} and quantity {int}")
    public void givenTheUserHasABuyListWithItemIDAndQuantity(String itemId,int quantity) {
        user = new User();
        Commodity com1 = new Commodity();
        com1.setId(itemId);
        user.addBuyItem(com1);
        user.addBuyItem(com1);
    }

    @When("the user removes {int} item with ID {string} from the buy list")
    public void whenTheUserRemovesItemFromTheBuyList(int itemsToRemove,String IdItemToRemove) throws CommodityIsNotInBuyList {
        Commodity commodity = new Commodity();
        commodity.setId(IdItemToRemove);
        for (int i = 0; i < itemsToRemove; i++) {
            try {
                user.removeItemFromBuyList(commodity);
            } catch (CommodityIsNotInBuyList ignored) {
            }
        }
    }

    @Then("the new quantity of item ID {string} in the buy list should be {int}")
    public void thenTheNewQuantityInBuyListShouldBe(String itemId, int expectedQuantity) {
        assertEquals(expectedQuantity, user.getBuyList().get(itemId).intValue());
    }


}
