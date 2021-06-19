package jason.practice.cleanarchitecture_mvp_livedata.data.remote;

public interface Response {
    int getStatusCode();
    String getContent();
    String getErrorMessage();
    String getHeader(String key);
}
