package xyz.sadiulhakim;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter server address (default: localhost): ");
            String address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                address = "localhost";
            }

            System.out.print("Enter server port (default: 8000): ");
            String portStr = scanner.nextLine().trim();
            int port = portStr.isEmpty() ? 8000 : Integer.parseInt(portStr);

            try (FastDBClient client = new FastDBClient(address, port)) {

                System.out.println("Connected to the server. Enter your commands:");
                System.out.println("Type `help` to see help text.");
                while (true) {
                    System.out.print("> ");
                    String command = scanner.nextLine().trim();
                    if (command.equalsIgnoreCase("exit")) {
                        System.out.println("Good Bye");
                        break;
                    } else if (command.startsWith("help")) {
                        processHelpScript();
                        continue;
                    }
                    String response = client.sendCommand(command);
                    System.out.println(response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processHelpScript() {
        String helpText = """
                 - help \t To see help text
                 - exit \t-> To exit the console
                 - insert {key} {value} \t-> To insert a data
                 - update {key} {newValue} \t-> To update existing data
                 - get {key} \t-> To get a value
                 - remove {ket} \t-> To remove a data       \s
                \s""";
        System.out.println(helpText);
    }
}