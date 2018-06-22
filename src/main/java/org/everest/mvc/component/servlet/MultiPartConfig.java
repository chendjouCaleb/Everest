package org.everest.mvc.component.servlet;

public class MultiPartConfig {
    private String location;
    private long maxFileSize = 10485760;
    private long maxRequestSize = 52428800;
    private int fileSizeThreshold = 1048576;

    public String getLocation() {
        return location;
    }

    public MultiPartConfig location(String location) {
        this.location = location;
        return this;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public MultiPartConfig maxFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
        return this;
    }

    public long getMaxRequestSize() {
        return maxRequestSize;
    }

    public MultiPartConfig maxRequestSize(long maxRequestSize) {
        this.maxRequestSize = maxRequestSize;
        return this;
    }

    public int getFileSizeThreshold() {
        return fileSizeThreshold;
    }

    public MultiPartConfig fileSizeThreshold(int fileSizeThreshold) {
        this.fileSizeThreshold = fileSizeThreshold;
        return this;
    }
}
