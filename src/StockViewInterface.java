import java.util.List;

public interface StockViewInterface {

    /** view price of stock at current time */
    StockRep price(String symbol) throws QueryException;

    /** view prices of multiple stocks max 64 */
    List<StockRep> prices(String ... symbols);
}
