import java.util.Calendar;
import java.util.List;

public class StockViewer implements StockViewInterface {
    /** returns current price of given ticker symbol */
    @Override
    public StockRep price(String symbol) {
        return new StockRep(symbol, 1.0);
    }

    @Override
    public List<StockRep> prices(String... symbols) {
        return null;
    }

    @Override
    public List<StockRep> histPrices(String symbol, Calendar start, Calendar end, String interval) {
        return null;
    }
}
