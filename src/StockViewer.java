public class StockViewer implements StockViewInterface {
    /** does something */
    @Override
    public StockRep price(String symbol) {
        return new StockRep(symbol, 1.0);
    }
}
