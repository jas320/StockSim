//import yahoofinance.Stock;
//import yahoofinance.YahooFinance;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.fx.FxQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.Calendar;
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

    @Override
    public List<StockRep> histPrices(String symbol, Calendar start, Calendar end, String interval) {
        // interval should be one of day - "d", week - "w", month - "m"
        try {
            Stock stock = YahooFinance.get(symbol, true);
            Calendar adjStart = Calendar.getInstance();
            adjStart = start;
//            adjStart.setTimeInMillis(start.getTimeInMillis() - 24 * 3600 * 1000);
            List<HistoricalQuote> historicalQuotes = stock.getHistory(adjStart, end, convert(interval));
            System.out.println(historicalQuotes);
            return historicalQuotes.stream().map(i -> new StockRep(symbol, i.getClose().doubleValue(), i.getDate())).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        List<HistoricalQuote> historicalQuotes = stock.getHistory();
    }

    public Interval convert(String input) {
        return switch (input) {
            case "w" -> Interval.WEEKLY;
            case "m" -> Interval.MONTHLY;
            case "d" -> Interval.DAILY;
            default -> {
                System.out.printf("%s cannot be converted to valid interval", input);
                yield null;
            }
        };
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
//        stock.print();
        return new StockRep(symbol, price.doubleValue());
    }

//    public List<StockRep> handleHisStock(String symbol, Calendar start, Calendar end, String interval, Stock stock)

//public void handleFx(String currPair, FxQuote fxQuote) {
//
//}

}
/** catches incorrect validation of api fields etc. */
class QueryException extends Exception {

    public QueryException(String message) {
        super(message);
    }
}
