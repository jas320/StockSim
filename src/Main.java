import org.apache.commons.cli.*;


public class Main {
    public static void main(String[] args) {
        /* parse all commands and options here */
        Options options = new Options();
        Option price = new Option("p", "price", false, "price command");
        price.setRequired(false);
        options.addOption(price);
        Option symbol = new Option("s", "symbol", true, "ticker symbol");
        symbol.setRequired(true);
        options.addOption(symbol);
        HelpFormatter formatter = new HelpFormatter();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("StockSim", options);
            System.exit(1);
            return;
        }
        if (cmd.hasOption("p")) {
            System.out.println("User chose command: price");
        }
        System.out.println("User ticker symbol is:" + cmd.getOptionValue("symbol"));
    }
}