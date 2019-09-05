import connection.ConnectionValidator;

public class PortInputHandler {
    public static int askForInput()
    {
        System.out.println("Please enter the listening port (between 5000 and 5050)");
        do {
            try {
                String input = System.console().readLine();
                int port = Integer.parseInt(input);

                if (ConnectionValidator.validatePort(port))
                    return port;
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry. Please try again:");
            }
        } while (true);
    }
}
