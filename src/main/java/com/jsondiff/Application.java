package com.jsondiff;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;

/**
 * Data Loader: Load data from source => store it
 * Comparator: Load data from a source(file, rest, etc) => preprocess => store for comparison
 * Report
 * */
public class Application {
    public static void main(String... args) throws JSONException {
        JSONCompareResult result = JSONCompare.compareJSON("{\n" +
                "   \"name\":{\n" +
                "      \"firstName\":\"Anton\"\n" +
                "   }\n" +
                "}", "{\n" +
                "   \"name\":{\n" +
                "      \"firstName\":\"Reeki\"\n" +
                "   }\n" +
                "}", JSONCompareMode.NON_EXTENSIBLE);

        System.out.print(result);
    }
}
