package Everest.Mvc.Result;

/**
 * Represents an {@link IActionResult} that when executed will
 * write a file as the response.
 */
public abstract class FileResult implements IActionResult {
    protected String fileName;

    /**
     * Gets the Content-Type header for the response.
     */
    private String contentType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
