public class StockViewer implements StockViewInterface {
    @Override
    public StockRep price(String symbol) {
        return new StockRep(symbol, 1.0);
    }
}
