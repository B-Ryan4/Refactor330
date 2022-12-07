package refactor330;

import static org.junit.Assert.assertEquals;

import org.junit.*;

public class CustomerTest {
	private static final Movie SPONGEBOB = new Movie("Spongebob: The Movie", Movie.CHILDREN);
    private static final Movie SPACEBALLS = new Movie("Spaceballs", Movie.NEW_RELEASE);
    private static final Movie TOY_STORY = new Movie("Toy Story", Movie.REGULAR);

    private final Customer customer = new Customer("John");

    @Test
    public void basicChildrenRental() {
        customer.addRental(new Rental(SPONGEBOB, 2));
        assertEquals(customer.statement(), expectedMessageFor("Spongebob: The Movie",1.5,1.5,1));
    }    
    @Test
    public void shouldDiscountChildrensRentals() {
        customer.addRental(new Rental(SPONGEBOB, 4));
        assertEquals(customer.statement(), expectedMessageFor("Spongebob: The Movie",3.0,3.0,1));
    }

    @Test
    public void basicNewReleaseRental() {
        customer.addRental(new Rental(SPACEBALLS, 1));
        assertEquals(customer.statement(), expectedMessageFor("Spaceballs", 3.0, 3.0, 1));
    }

    @Test
    public void shouldNotDiscountNewReleaseRentalsButBonusFrequentRenterPoints() {
        customer.addRental(new Rental(SPACEBALLS, 4));
        assertEquals(customer.statement(), expectedMessageFor("Spaceballs", 12.0, 12.0, 2));
    }

    @Test
    public void basicRegularRental() {
        customer.addRental(new Rental(TOY_STORY, 2));
        assertEquals(customer.statement(), expectedMessageFor("Toy Story", 2.0, 2.0, 1));
    }

    @Test
    public void shouldDiscountRegularRental() {
        customer.addRental(new Rental(TOY_STORY, 4));
        assertEquals(customer.statement(), expectedMessageFor("Toy Story", 5.0, 5.0, 1));
    }
    @Test
    public void shouldSumVariousRentals() {
        customer.addRental(new Rental(SPONGEBOB, 2));
        customer.addRental(new Rental(SPACEBALLS, 1));
        customer.addRental(new Rental(TOY_STORY, 3));
        assertEquals(customer.statement(), "Rental record for John\n"
        		+ "\tSpongebob: The Movie\t1.5\n"
        		+ "\tSpaceballs\t3.0\n"
        		+ "\tToy Story\t3.5\n"
        		+ "Amount owed is 8.0\n"
        		+ "You earned 3 frequent renter points");
    }

    private static String expectedMessageFor(String rental, double price, double total, int renterPointsEarned) {
        return "Rental record for John\n\t" + rental + "\t" + price + "\nAmount owed is " + total + "\nYou earned " + renterPointsEarned + " frequent renter points";
    }
}
