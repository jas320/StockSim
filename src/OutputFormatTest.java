import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OutputFormatTest {

//    @Test
//    void writing_stockRep_to_console_calls_system_out_println() {
//        mock(System.out.getClass());
//
//    }

    @Test
    void writing_stockRep_to_file_creates_file_with_correct_contents() {
        StockRep stockRep = new StockRep("APPL", 1.0);
        StockRep stockRep2 = new StockRep("NVDA", 2.0);
        OutputFormat.toFileAuto(List.of(stockRep, stockRep2));


        String path = "/homes/jas320/IdeaProjects/StockSim/src/OUTPUT.txt";
        try (FileReader fr = new FileReader(path);
             BufferedReader br = new BufferedReader(fr)) {

            String expected = stockRep.toString() + stockRep2.toString();
            String actual = br.lines().toList().stream().reduce((a,b) -> a + b).orElse("ERROR");
            assertEquals(expected, actual);

        } catch (IOException e) {
            System.out.println("Error writing file" + e.getMessage());
        }
    }


}