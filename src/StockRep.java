import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class StockRep {

    private final String symbol;
    private final double price;
    private Calendar date;

    public StockRep(String symbol, double price, Calendar date) {
        this.symbol = symbol;
        this.price = price;
        this.date = date;
    }

    public StockRep(String symbol, Double price) {
        LocalDate localDate = LocalDate.now();
        this.date = new Calendar.Builder().setDate(
                localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth()).build();
        this.symbol = symbol;
        this.price = price;
    }

    public static StockRep InvalidRep() {
        return new StockRep("ERROR", -1.0);
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "StockRep{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockRep stockRep = (StockRep) o;
        return this.symbol.equals(stockRep.symbol) && price == stockRep.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, price);
    }

    public Calendar getDate() {
        return date;
    }
}
