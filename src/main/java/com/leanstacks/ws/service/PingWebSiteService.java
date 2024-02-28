package com.leanstacks.ws.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class PingWebSiteService {

    private static final int RESPONSE_LIMIT = 100;
    private static final Logger logger = LoggerFactory.getLogger(PingWebSiteService.class);

    public static String getStatus(String url) throws IOException {
        String result;
        try {
            URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            con.setRequestMethod("GET");
            // Set connection timeout
            con.setConnectTimeout(3000);
            con.connect();

            result = getHeadersInformation(con.getHeaderFields());
        } catch (Exception e) {
            logger.error("Error ping website: " + e.getMessage());
            result = "failed";
        }
        return result;
    }

    private static String getHeadersInformation(Map<String, List<String>> map) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getKey() == null)
                continue;
            builder.append(entry.getKey())
                    .append(": ");

            List<String> headerValues = entry.getValue();
            headerValues.stream().forEach(
                    str -> builder.append(str).append(", ")
            );

            builder.append("\n");
        }
        return builder.substring(0, RESPONSE_LIMIT);
    }
}
