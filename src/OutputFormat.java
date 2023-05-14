import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;

public class OutputFormat {

    /** writes output to standard console using String rep of parameter */
    public static void toConsole(StockRep stockRep) {
        System.out.println(stockRep);
    }

    /** writes output to new file */
    static void toFileAuto(StockRep stockRep) {
        String path = "/homes/jas320/IdeaProjects/StockSim/src/OUTPUT.txt";
        toFile(path, List.of(stockRep));
    }

    /** writes output to new file */
    static void toFileAuto(List<StockRep> stockReps) {
        String path = "/homes/jas320/IdeaProjects/StockSim/src/OUTPUT.txt";
        toFile(path, stockReps);
    }


    /** writes output to new file */
    static void toFile(String path, List<StockRep> stockReps) {
        try (FileWriter fr = new FileWriter(path);
             BufferedWriter br = new BufferedWriter(fr)) {
            for (StockRep stockRep : stockReps) {
                br.write(stockRep.toString());
            }
        } catch (IOException e) {
            System.out.println("Error writing file" + e.getMessage());
        }
    }


}
