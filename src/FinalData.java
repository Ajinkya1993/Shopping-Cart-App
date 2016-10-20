/**
 * Class that holds info about products.
 * @author Ajinkya Nimbalkar AndrewID: animbalk
 *
 */
public class FinalData {
    
    private final String item;
    private final double price;
    
    //constructor
    public FinalData(String itm, double prce) {
        item = itm;
        price = prce;
    }
    
    public double getprice() {
        return price;
    }

}
