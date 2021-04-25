package com.jsondiff.loader.file;

import com.fasterxml.jackson.databind.JsonNode;
import com.jsondiff.loader.AbstractDataLoader;
import com.jsondiff.loader.DataLoader;
import com.jsondiff.loader.DataRequest;
import com.jsondiff.loader.DataResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileJsonDataLoader extends AbstractDataLoader {
    @Override
    public DataResponse load(DataRequest request) {
        FileDataRequest fileDataRequest = getDataRequestAs(request, FileDataRequest.class);
        JsonNode jsonResponse = getFileContent(fileDataRequest.getFilePath());
        return DataResponse.Builder.aDataResponse().withData(jsonResponse).build();
    }

    private JsonNode getFileContent(String filePath) {
        try {
            final String rawData = Files.readString(Paths.get(filePath));
            return readAsJson(rawData);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read file contents at path " + filePath);
        }
    }
}
