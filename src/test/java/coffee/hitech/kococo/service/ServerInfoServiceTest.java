package coffee.hitech.kococo.service;

import coffee.hitech.kococo.model.ServerInfo;
import coffee.hitech.kococo.model.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;

public class ServerInfoServiceTest {
    private static final String ID = "";
    private ServerInfoService service;

    @Before
    public void setup() {
        service = new ServerInfoService();
    }

    @Ignore("테스트를 원하면 'String ID'에 본인 코콤 ID를 넣으세요.")
    @Test
    public void get() throws IOException {
        User user = new User(ID, "");
        ServerInfo info =  service.get(user);
        assertFalse(info.getHost().isEmpty());
    }
}
