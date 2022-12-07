package refactor330;

public class Movie {

    public static final int CHILDREN = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String title;
    Price price;

    public Movie(String title, int priceCode) {
        this.title = title;
        setPriceCode(priceCode);
    }

    public String getTitle() {
        return title;
    }

    public int getPriceCode() {
        return price.getPriceCode();
    }

    public void setPriceCode(int priceCode) {
    	 switch (priceCode) {
	         case CHILDREN:
	             price = new Children();
	             break;
	         case NEW_RELEASE:
	             price = new NewRelease();
	             break;
	         case REGULAR:
	             price = new Regular();
	             break;
	         default:
	             throw new IllegalArgumentException("invalid price code");
    	 }
    }
    
    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        if (getPriceCode() == Movie.NEW_RELEASE && daysRented > 1)
            return 2;
        return 1;
    }
}
