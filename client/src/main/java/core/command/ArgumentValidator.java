package core.command;

import command.Command;

import java.io.IOException;

public class ArgumentValidator {
    public static boolean validateArguments(Command.CommandName commandName, String[] arguments) throws IOException
    {
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

    private static boolean validateCD(String[] arguments) throws IOException {
        if (arguments.length > 0) {
            throw new IOException("Trop d'arguments pour CD!");
        }

        return true;
    }

    private static boolean validateLS(String[] arguments) throws IOException {
        if (arguments.length > 0) {
            throw new IOException("Trop d'arguments pour LS!");
        }

        return true;
    }

    private static boolean validateMKDIR(String[] arguments) throws IOException {
        if (arguments.length < 1) {
            throw new IOException("Pas assez d'arguments pour MKDIR!");
        }

        if (arguments.length > 1) {
            throw new IOException("Trop d'arguments pour MKDIR!");
        }

        return true;
    }

    private static boolean validateUPLOAD(String[] arguments) throws IOException {
        if (arguments.length < 1) {
            throw new IOException("Pas assez d'arguments pour UPLOAD!");
        }

        if (arguments.length > 1) {
            throw new IOException("Trop d'arguments pour UPLOAD!");
        }

        return true;
    }
    private static boolean validateDOWNLOAD(String[] arguments) throws IOException {
        if (arguments.length < 1) {
            throw new IOException("Pas assez d'arguments pour DOWNLOAD!");
        }

        if (arguments.length > 1) {
            throw new IOException("Trop d'arguments pour DOWNLOAD!");
        }

        return true;
    }

    private static boolean validateEXIT(String[] arguments) throws IOException {
        if (arguments.length > 0) {
            throw new IOException("Trop d'arguments pour EXIT!");
        }

        return true;
    }
}
