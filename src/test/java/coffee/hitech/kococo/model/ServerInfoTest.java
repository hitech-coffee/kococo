package coffee.hitech.kococo.model;

import coffee.hitech.kococo.exception.ServerInfoNotFoundException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ServerInfoTest {

    @Test
    public void parse() {
        String response = "<br>0 => [OK]<br>1 => hh_korea<br>2 => 한국 한화꿈에그린<br>3 => 192.168.0.1";
        ServerInfo info = ServerInfo.parse(response);
        assertThat(info.getCode(), is("hh_korea"));
        assertThat(info.getCodeDescription(), is("한국 한화꿈에그린"));
        assertThat(info.getHost(), is("192.168.0.1"));
    }

    @Test(expected = ServerInfoNotFoundException.class)
    public void parse_null() {
        ServerInfo.parse(null);
    }

    @Test(expected = ServerInfoNotFoundException.class)
    public void parse_error_code() {
        String response = "<br>0 => [ERROR]<br>1 => hh_korea<br>2 => 한국 한화꿈에그린<br>3 => 192.168.0.1";
        ServerInfo.parse(response);
    }

    @Test(expected = ServerInfoNotFoundException.class)
    public void parse_not_enough_line() {
        String response = "<br>0 => [ERROR]<br>1 => hh_korea<br>2 => 한국 한화꿈에그린";
        ServerInfo.parse(response);
    }

    @Test(expected = ServerInfoNotFoundException.class)
    public void parse_not_enough_field() {
        String response = "<br>0 => [ERROR]<br>1 => hh_korea<br>2 => 한국 한화꿈에그린<br>3";
        ServerInfo.parse(response);
    }
}
