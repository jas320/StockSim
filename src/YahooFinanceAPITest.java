import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class YahooFinanceAPITest {
    private YahooFinanceAPI api = new YahooFinanceAPI();
    // TODO: do we need to veryify that we are actually calling the API here?

    /* actual API call tests */
    @Test
    void get_price_data_of_tesla_should_return_positive_double_value() throws QueryException {
        StockRep stockRep = api.price("TSLA");
        assertTrue(stockRep.getPrice() > 0);
    }

//    @Test
//    void invalid_symbol_name_raises_exception() {
//        QueryException thrown = assertThrows(QueryException.class, () -> {
//            StockRep stockRep = api.price("ZZZZZZ");
//        });
//        assertEquals("Symbol ZZZZZZ, not recognised by yahoo finance", thrown.getMessage());
//    }
    @Test
    void invalid_api_call_returns_invalid_stock_rep(){
        StockRep stockRep = api.price("ZZZZZ");
        assertEquals(stockRep.getSymbol(), "ERROR");
        assertEquals(stockRep.getPrice(), -1.0);
    }

    @Test
    void get_prices_of_multiple_stocks_returns_list_of_stock_reps_with_valid_prices() {
        List<StockRep> stockReps = api.prices("AAPL", "TSLA", "MSFT");
        stockReps.stream().forEach(i -> assertTrue(i.getPrice() > 0));
    }



}