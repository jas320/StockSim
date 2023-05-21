//import yahoofinance.Stock;
//import yahoofinance.YahooFinance;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

/** Free to use basic stock info API, not officially supported anymore, best performance with US stocks (lowest latency)
 * Also has option to use FX.
 * Adapter pattern to allow API to be changed with minimal refactor.
 */
public class YahooFinanceAPI implements StockViewInterface{
    /* view price of stock at current time */
    @Override
    public StockRep price(String symbol){
        try {
            Stock stock = YahooFinance.get(symbol);
            return handleStock(symbol, stock);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return StockRep.InvalidRep();
        }
    }

    @Override
    public List<StockRep> prices(String... symbols) {
        // symbols implicitly treated as array of type: String. String[]

        Map<String, Stock> stocks = null; // single request
        try {
            stocks = YahooFinance.get(symbols);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stocks.entrySet().stream().map(e -> handleStock(e.getKey(), e.getValue())).toList();
    }

    public StockRep handleStock(String symbol, Stock stock) {
        if (stock == null || stock.getName() == null) {
            System.out.printf("Symbol %s, not recognised by yahoo finance", symbol);
            return StockRep.InvalidRep();
        }
        BigDecimal price = stock.getQuote().getPrice();
//            BigDecimal change = stock.getQuote().getChangeInPercent();
//            BigDecimal peg = stock.getStats().getPeg();
//            BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
        System.out.println(price);
        stock.print();
        return new StockRep(symbol, price.doubleValue());
    }

}
/** catches incorrect validation of api fields etc. */
class QueryException extends Exception {

    public QueryException(String message) {
        super(message);
    }
}
