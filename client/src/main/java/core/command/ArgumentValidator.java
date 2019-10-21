package core.command;

import command.Command;

import java.io.IOException;

/**
 * Validates arguments for a command.
 */
public class ArgumentValidator {
    /**
     * Validate arguments for a given command.
     * @param commandName The name of the command.
     * @param arguments The arguments to validate for the command.
     * @return True if the arguments are valid.
     * @throws IOException If the arguments are invalid.
     */
    public static boolean validateArguments(Command.CommandName commandName, String[] arguments) throws IOException
    {
        // Select which command to validate.
        switch (commandName) {
            case CD:
                return validateCD(arguments);
            case LS:
                return validateLS(arguments);
            case MKDIR:
                return validateMKDIR(arguments);
            case UPLOAD:
                return validateUPLOAD(arguments);
            case DOWNLOAD:
                return validateDOWNLOAD(arguments);
            case EXIT:
                return validateEXIT(arguments);
            default:
                return false;
        }
    }

    /**
     * Validates arguments for the cd command.
     * @param arguments The arguments to validate.
     * @return True if the arguments are valid.
     * @throws IOException If the arguments are invalid.
     */
    private static boolean validateCD(String[] arguments) throws IOException
    {
        if (arguments.length < 1) {
            throw new IOException("Pas assez d'arguments pour CD!");
        }

        if (arguments.length > 1) {
            throw new IOException("Trop d'arguments pour CD!");
        }

        return true;
    }

    /**
     * Validates arguments for the ls command.
     * @param arguments The arguments to validate.
     * @return True if the arguments are valid.
     * @throws IOException If the arguments are invalid.
     */
    private static boolean validateLS(String[] arguments) throws IOException
    {
        if (arguments.length > 0) {
            throw new IOException("Trop d'arguments pour LS!");
        }

        return true;
    }

    /**
     * Validates arguments for the mkdir command.
     * @param arguments The arguments to validate.
     * @return True if the arguments are valid.
     * @throws IOException If the arguments are invalid.
     */
    private static boolean validateMKDIR(String[] arguments) throws IOException
    {
        if (arguments.length < 1) {
            throw new IOException("Pas assez d'arguments pour MKDIR!");
        }

        if (arguments.length > 1) {
            throw new IOException("Trop d'arguments pour MKDIR!");
        }

        return true;
    }

    /**
     * Validates arguments for the upload command.
     * @param arguments The arguments to validate.
     * @return True if the arguments are valid.
     * @throws IOException If the arguments are invalid.
     */
    private static boolean validateUPLOAD(String[] arguments) throws IOException
    {
        if (arguments.length < 1) {
            throw new IOException("Pas assez d'arguments pour UPLOAD!");
        }

        if (arguments.length > 1) {
            throw new IOException("Trop d'arguments pour UPLOAD!");
        }

        return true;
    }

    /**
     * Validates arguments for the download command.
     * @param arguments The arguments to validate.
     * @return True if the arguments are valid.
     * @throws IOException If the arguments are invalid.
     */
    private static boolean validateDOWNLOAD(String[] arguments) throws IOException
    {
        if (arguments.length < 1) {
            throw new IOException("Pas assez d'arguments pour DOWNLOAD!");
        }

        if (arguments.length > 1) {
            throw new IOException("Trop d'arguments pour DOWNLOAD!");
        }

        return true;
    }

    /**
     * Validates arguments for the exit command.
     * @param arguments The arguments to validate.
     * @return True if the arguments are valid.
     * @throws IOException If the arguments are invalid.
     */
    private static boolean validateEXIT(String[] arguments) throws IOException
    {
        if (arguments.length > 0) {
            throw new IOException("Trop d'arguments pour EXIT!");
        }

        return true;
    }
}
