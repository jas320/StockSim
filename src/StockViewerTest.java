import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockViewerTest {

    private StockViewer stockViewer = new StockViewer();

    @Test
    void price_returns_stock_rep_with_one_and_string_value() {
        StockRep expected = new StockRep("APPL", 1.0);
        StockRep actual = stockViewer.price("APPL");
        assertEquals(expected, actual);
    }
}