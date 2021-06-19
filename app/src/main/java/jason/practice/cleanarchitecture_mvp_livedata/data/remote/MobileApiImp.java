package jason.practice.cleanarchitecture_mvp_livedata.data.remote;

import java.net.UnknownHostException;
import java.util.Random;

public class MobileApiImp implements MobileApi {

    public Response getWifiData(int _interface) throws Throwable {
        Random random = new Random(System.currentTimeMillis());

        // simulate when unknown host name happen
        if(random.nextInt(10) == 9) {
            throw new UnknownHostException();
        }
        if (_interface == 0) {
            return new ResponseImp(200, "Jason_wifi_5G", null);
        } else {
            return new ResponseImp(200, "Jason_wifi_2G", null);
        }
    }
}
