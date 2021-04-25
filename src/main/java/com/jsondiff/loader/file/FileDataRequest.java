package com.jsondiff.loader.file;

import com.jsondiff.loader.DataRequest;

public class FileDataRequest extends DataRequest {
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
