package no.avexis.allup.model;

/**
 * Created by Sindre Bøyum on 22.01.2017.
 */

public class WebPage {
    private long id;
    private int status;
    private String statusText;
    private String title;
    private String url;

    public WebPage() {
        id = -1L;
        status = -1;
        statusText = "";
        title = "";
        url = "";
    }

    public WebPage(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public WebPage(int status, String statusText, String title, String url) {
        this.status = status;
        this.statusText = statusText;
        this.title = title;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}
