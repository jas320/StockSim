import org.junit.jupiter.api.Test;
import yahoofinance.quotes.fx.FxSymbols;

import java.util.Calendar;
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
    // TODO split stockRep and FXRep when requiring more unique fields.
    // FX will default getHIstory() when printing stock so will be slower, consider running this less often
    @Test
    void get_price_data_of_EURUSD_should_return_positive_double_value() throws QueryException {
        StockRep stockRep = api.price(FxSymbols.EURUSD);
        assertTrue(stockRep.getPrice() > 0);
        assertEquals(stockRep.getSymbol(), FxSymbols.EURUSD);
        assertEquals(stockRep.getDate(), new Calendar.Builder().setDate(2023,5,21).build());
    }
    @Test
    void get_prices_of_multiple_currency_pairs_returns_list_of_stock_reps_with_valid_prices() {
        List<StockRep> stockReps = api.prices(FxSymbols.AUDCHF, FxSymbols.USDEUR, FxSymbols.USDGBP);
        stockReps.stream().forEach(i -> assertTrue(i.getPrice() > 0));
    }

    @Test
    void invalid_fx_call_retuns_invalid_stock_rep(){
        StockRep stockRep = api.price("ZZZZZ");
        assertEquals(stockRep.getSymbol(), "ERROR");
        assertEquals(stockRep.getPrice(), -1.0);
    }


    // TODO need to isolate the API call so can verify contents are parsed/mapped corretly.
    @Test
    void historical_prices_returns_price_at_each_interval() {
        Calendar c1 = new Calendar.Builder().setDate(2020,1,1).build();
        Calendar c2 = new Calendar.Builder().setDate(2020,3,1).build();
        List<StockRep> stockReps = api.histPrices("AAPL", c1
                , c2,
                "m"
                );
        c1.add(Calendar.MONTH, 1);
        assertEquals(stockReps.get(0).getDate(), c1);
         assertEquals(stockReps.get(1).getDate(), c2);
//        assertEquals(stockReps.get(1).getDate(), c2);

    }



    // fx and stock combined at the moment, can be seperated in the future.
//    @Test
//    void get_price_handles_fx_prices_should_return_positive_value() {
//        StockRep stockRep = api.price("USDGBP=X");
//        assertTrue(stockRep.getPrice() > 0);
//    }







}