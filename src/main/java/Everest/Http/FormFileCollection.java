package Everest.Http;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the collection of files sent with the HttpRequest.
 */
public class FormFileCollection extends ArrayList<IFormFile> {
    /**
     * Gets the first file with the specified name.
     * @param name The name of the file to get.
     * @return The requested file, or null if it is not present.
     */
    IFormFile getFile(String name){
        return stream().filter(file -> file.getFileName().equals(name)).findFirst().orElse(null);
    }

    /**
     * Gets an {@link List<IFormFile>} containing the files of the
     * {@link FormFileCollection} with the specified name.
     * @param name The name of the files to get.
     * @return An {@link List<IFormFile>} containing the files of the object
     *  that implements {@link IFormFile}.
     */
    List<IFormFile> getFiles(String name){
        return stream().filter(file -> file.getFileName().equals(name)).collect(Collectors.toList());
    }
}
