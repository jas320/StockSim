import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OutputFormatTest {

    @Test
    void writing_stockRep_to_console_writes_to_system_out_println() throws IOException {
        StockRep stockRep = new StockRep("APPL", 1.0);
        String expected = stockRep + "\n";

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        // Redirect System.out to buffer
        PrintStream orginal = System.out;
        System.setOut(new PrintStream(bo));

        OutputFormat.toConsole(stockRep);

        bo.flush();
        String allWrittenLines = bo.toString();
        System.setOut(orginal);
        assertEquals(expected, allWrittenLines);
    }

    @Test
    void writing_single_stockRep_does_not_throw_error_when_read() {
        StockRep stockRep = new StockRep("APPL", 1.0);
        String path = "/homes/jas320/IdeaProjects/StockSim/src/OUTPUT.txt";
        OutputFormat.toFileAuto(stockRep);
        assertDoesNotThrow(() -> new FileReader(path));
    }

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