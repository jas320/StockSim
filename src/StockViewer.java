public class StockViewer implements StockViewInterface {
    /** returns current price of given ticker symbol */
    @Override
    public StockRep price(String symbol) {
        return new StockRep(symbol, 1.0);
    }
}
