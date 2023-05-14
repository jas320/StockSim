import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/* Internal representation of a stock, to be used when passing around from APIs */
class StockRepTest {


    @Test
    void rep_with_different_symbols_are_not_equal() {
        StockRep expected = new StockRep("APPL", 1.0);
        StockRep actual = new StockRep("APP", 1.0);
        assertNotEquals(expected, actual);
    }

    @Test
    void rep_with_different_prices_are_not_equal() {
        StockRep expected = new StockRep("APPL", 1.0);
        StockRep actual = new StockRep("APPL", 1.1);
        assertNotEquals(expected, actual);
    }

}