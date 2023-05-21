import yahoofinance.histquotes.Interval;

import java.util.Calendar;
import java.util.List;

public interface StockViewInterface {

    /** view price of stock at current time */
    StockRep price(String symbol) throws QueryException;

    /** view prices of multiple stocks max 64 */
    List<StockRep> prices(String ... symbols);

    /** view prices of single stock at regular interval between two dates
     *  assumed to be the closing price on that day, or start of month/year,
     *  */
    List<StockRep> histPrices(String symbol, Calendar start, Calendar end, String interval);
}
