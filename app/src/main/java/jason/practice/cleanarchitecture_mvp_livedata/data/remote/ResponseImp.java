package jason.practice.cleanarchitecture_mvp_livedata.data.remote;

import java.util.HashMap;
import java.util.Map;

public class ResponseImp implements Response {

    int statusCode;
    String content;
    String errorMessage;
    Map<String, String> headers;

    public ResponseImp(int statusCode, String content, String errorMessage) {
        this.statusCode = statusCode;
        this.content = content;
        this.errorMessage = errorMessage;
        this.headers = new HashMap<>();
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getHeader(String key) {
        return headers.containsKey(key) ? headers.get(key) : "";
    }
}
