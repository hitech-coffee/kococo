package coffee.hitech.kococo.service;

import coffee.hitech.kococo.model.ServerInfo;
import coffee.hitech.kococo.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerInfoService {
    private static final String API = "http://221.141.3.28/SvrInfo.php?uid={uid}";

    public ServerInfo get(User user) throws IOException {
        URL url = new URL(API.replace("{uid}", user.getId()));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try (InputStreamReader is = new InputStreamReader(connection.getInputStream());
             BufferedReader br = new BufferedReader(is)) {
            String inputLine;
            StringBuilder sb = new StringBuilder();

            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }

            return ServerInfo.parse(sb.toString());
        }
    }
}
