package pl.jangrot.lnksmgmt;

import lombok.Getter;
import org.springframework.data.annotation.Id;

public final class Link {

    @Id
    private String id;

    @Getter
    private String url;

    @Getter
    private boolean isWatched;

    public Link() {
    }

    public Link(String id, String url, boolean isWatched) {
        this.id = id;
        this.url = url;
        this.isWatched = isWatched;
    }

    public Link(String id, String url) {
        this(id, url, false);
    }

    @Override
    public String toString() {
        return String.format("Link(id=\"%s\", url:\"%s\" isWatched:\"%b\")", id, url, isWatched);
    }
}
