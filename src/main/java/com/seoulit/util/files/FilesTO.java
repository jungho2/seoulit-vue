package com.seoulit.util.files;


public class FilesTO {
    
    private int idx;
    private String originalFileName;
    private String storedFile;
    private String fileUrl;
    private long fileSize;



    /**
     * @return int return the idx
     */
    public int getIdx() {
        return idx;
    }

    /**
     * @param idx the idx to set
     */
    public void setIdx(int idx) {
        this.idx = idx;
    }

    /**
     * @return String return the originalFileName
     */
    public String getOriginalFileName() {
        return originalFileName;
    }

    /**
     * @param originalFileName the originalFileName to set
     */
    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    /**
     * @return String return the storedFile
     */
    public String getStoredFile() {
        return storedFile;
    }

    /**
     * @param storedFile the storedFile to set
     */
    public void setStoredFile(String storedFile) {
        this.storedFile = storedFile;
    }

    /**
     * @return String return the fileUrl
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * @param fileUrl the fileUrl to set
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * @return long return the fileSize
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize the fileSize to set
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

}