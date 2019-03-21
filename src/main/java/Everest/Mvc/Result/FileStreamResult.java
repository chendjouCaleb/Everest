package Everest.Mvc.Result;

import java.io.OutputStream;

/**
 * Represents an {@link IActionResult} that when executed will
 * write a file from a stream to the response.
 */
public class FileStreamResult extends FileResult {

    /**
     * Gets or sets the stream with the file that will be sent back as the response.
     */
    private OutputStream outputStream;


}
