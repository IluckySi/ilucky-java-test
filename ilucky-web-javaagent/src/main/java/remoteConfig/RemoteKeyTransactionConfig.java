package remoteConfig;

public class RemoteKeyTransactionConfig {

    private String url;
    private String[] urls;
    private String[] header;
    private String body;
    private String[] cookie;
    private String[] session;
    
    public String getUrl() {
        return url;
    }
    public String[] getUrls() {
        return urls;
    }
    public String[] getHeader() {
        return header;
    }
    public String getBody() {
        return body;
    }
    public String[] getCookie() {
        return cookie;
    }
    public String[] getSession() {
        return session;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setUrls(String[] urls) {
        this.urls = urls;
    }
    public void setHeader(String[] header) {
        this.header = header;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public void setCookie(String[] cookie) {
        this.cookie = cookie;
    }
    public void setSession(String[] session) {
        this.session = session;
    }
}
