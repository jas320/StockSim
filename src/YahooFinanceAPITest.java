import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YahooFinanceAPITest {
    private YahooFinanceAPI api = new YahooFinanceAPI();

    @Test
    void get_price_data_of_microsoft_should_return() {
        api.price("TSLA");
        assertTrue(true);
    }

}