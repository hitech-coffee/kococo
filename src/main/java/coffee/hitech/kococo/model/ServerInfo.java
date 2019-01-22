package coffee.hitech.kococo.model;

import coffee.hitech.kococo.exception.ServerInfoNotFoundException;
import lombok.Value;

@Value
public class ServerInfo {
    private static final String LINE_DELIMITER = "<br>";
    private static final String CONTENT_DELIMITER = " => ";
    private static final String STATUS_OK = "[OK]";

    private String code;
    private String codeDescription;
    private String host;

    public static ServerInfo parse(String response) {
        if (null == response) {
            throw new ServerInfoNotFoundException("Server info response is empty.");
        }

        String[] values = response.split(LINE_DELIMITER);

        if (values.length < 5) {
            throw new ServerInfoNotFoundException("Server info is not enough. : " + response);
        }

        String[] status = values[1].split(CONTENT_DELIMITER);

        if (status.length < 2 || !STATUS_OK.equals(status[1])) {
            throw new ServerInfoNotFoundException("Server info status is not ok. : " + response);
        }

        try {
            String[] code = values[2].split(CONTENT_DELIMITER);
            String[] codeDescription = values[3].split(CONTENT_DELIMITER);
            String[] host = values[4].split(CONTENT_DELIMITER);

            return new ServerInfo(code[1], codeDescription[1], host[1]);
        } catch (Exception e) {
            throw new ServerInfoNotFoundException("Server info parsing error. : " + response);
        }
    }
}
