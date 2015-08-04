package pl.jangrot.lnksmgmt;

import org.springframework.data.annotation.Id;

public final class Link {

    @Id
    private String id;

    private String url;

    private Boolean watched;

    public Link() {
    }

    public Link(String id, String url, Boolean watched) {
        this.id = id;
        this.url = url;
        this.watched = watched;
    }

    public String getUrl() {
        return url;
    }

    public Boolean getWatched() {
        return watched;
    }
}
