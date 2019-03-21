package Everest.Mvc.Result;

import java.io.File;

/**
 * The {@link IActionResult} to return the local file in the http response.
 */
public class LocalFileResult {

    /**
     * The physical location of the file.
     */
    private String filePath;

    /**
     * The file to download.
     */
    private File file;

    public LocalFileResult(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
    }

    public LocalFileResult(File file) {
        this.file = file;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
