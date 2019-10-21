package utils;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Util class for various File and Path methods.
 */
public class FileUtils {
    /**
     * Verifies weather a new path appended to the current directory is valid.
     * @param newPath The new path to append to the current working directory.
     * @param currentWorkingDirectory The current working directory.
     * @return True if the appended path is valid. False otherwise.
     */
    public static boolean checkPathExists(String newPath, Path currentWorkingDirectory)
    {
        // Append the two paths and verify if it exists.
        Path path = currentWorkingDirectory.resolve(newPath).normalize();

        return Files.exists(path);
    }

    /**
     * Combine two paths and return the combination.
     * @param newPath The new path to append to the current working directory.
     * @param currentWorkingDirectory The current working directory.
     * @return The combined paths.
     */
    public static Path getCombinedPath(String newPath, Path currentWorkingDirectory)
    {
        return currentWorkingDirectory.resolve(newPath).normalize();
    }

    /**
     * Verifies if a file exists. Combines the current working directory with a new path.
     * @param newPath The new path to append to the current working directory.
     * @param currentWorkingDirectory The current working directory.
     * @return True if said file exists. False otherwise.
     */
    public static boolean fileExists(String newPath, Path currentWorkingDirectory)
    {
        // Combine the two paths.
        Path path = getCombinedPath(newPath, currentWorkingDirectory);

        // Verify if it's a file.
        return Files.isRegularFile(path);
    }
}
