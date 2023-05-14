import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLOutput;

/** Free to use basic stock info API, not officially supported anymore, best performance with US stocks (lowest latency)
 * Also has option to use FX.
 * Adapter pattern to allow API to be changed with minimal refactor.
 */
public class YahooFinanceAPI implements StockViewInterface{
    @Override
    public StockRep price(String symbol) {
        try {
            Stock stock = YahooFinance.get("TSLA");

            BigDecimal price = stock.getQuote().getPrice();
            BigDecimal change = stock.getQuote().getChangeInPercent();
            BigDecimal peg = stock.getStats().getPeg();
            BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

            stock.print();
//            if (!stock.isValid()) {
//                System.out.println("Symbol not recognised by Yahoo Finance");
//            } else {
//                System.out.println(stock);
//            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new StockRep(symbol, 1.0);
    }
}
