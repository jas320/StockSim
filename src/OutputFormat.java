import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;

public class OutputFormat {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /** writes output to standard console using String rep of parameter */
    public static void toConsole(StockRep stockRep) {
        System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);
//        System.out.println(stockRep);
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
