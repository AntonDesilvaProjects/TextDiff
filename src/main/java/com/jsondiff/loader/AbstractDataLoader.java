package com.jsondiff.loader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public abstract class AbstractDataLoader implements DataLoader{
    protected final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public JsonNode readAsJson(String rawStr) {
        try {
            return JSON_MAPPER.readTree(rawStr);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while attempting parse string as JSON", e);
        }
    }

    protected  <T> T getDataRequestAs(DataRequest request, Class<T> type) {
        return Optional.ofNullable(request)
                .filter(reqObj -> reqObj.getClass().isInstance(type))
                .map(type::cast)
                .orElseThrow(() -> new IllegalArgumentException("Unable to cast DataRequest!"));
    }

}
