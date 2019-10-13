package utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    public static boolean checkPathExists(String newPath, Path currentWorkingDirectory)
    {
        Path path = currentWorkingDirectory.resolve(newPath).normalize();

        return Files.exists(path);
    }

    public static Path getCombinedPath(String newPath, Path currentWorkingDirectory)
    {
        return currentWorkingDirectory.resolve(newPath).normalize();
    }
}
