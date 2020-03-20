package ru.innopolis.stc21.med.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class GeoIPService {

    public GeoIPService() {
    }

    public GeoIP getLocation(String ip) throws IOException {

        URL url = new URL("http://ipinfo.io/" + ip + "/geo");
        ObjectMapper objectMapper = new ObjectMapper();
        GeoIPtoJSON geoIPtoJSON = objectMapper.readValue(url, GeoIPtoJSON.class);

        String cityName = geoIPtoJSON.getCity();
        String[] loc = geoIPtoJSON.getLoc().split(",");
        String latitude = loc[0];
        String longitude = loc[1];

        System.out.println(cityName + " " + latitude + " " + longitude);
        return new GeoIP(ip, cityName, latitude, longitude);
    }
}