package jason.practice.cleanarchitecture_mvp_livedata.model;

public class WifiModel {
    private String ssid;
    private String password;
    private int encryption;
    private int _interface;

    public WifiModel(String ssid, String password, int encryption, int _interface) {
        this.ssid = ssid;
        this.password = password;
        this.encryption = encryption;
        this._interface = _interface;
    }

    public String getSsid() {
        return ssid;
    }

    public String getPassword() {
        return password;
    }

    public int getEncryption() {
        return encryption;
    }

    public int getInterface() {
        return _interface;
    }
}
