package pl.jangrot.lnksmgmt;

import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.Objects;

public final class Link {

    @Id
    @Getter
    private String id;

    @Getter
    private String url;

    @Getter
    private boolean watched;

    public Link() {
    }

    public Link(String id, String url, boolean watched) {
        this.id = id;
        this.url = url;
        this.watched = watched;
    }

    public Link(String id, String url) {
        this(id, url, false);
    }

    @Override
    public String toString() {
        return String.format("Link(id=\"%s\", url:\"%s\" watched:\"%b\")", id, url, watched);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return watched == link.watched &&
                Objects.equals(id, link.id) &&
                Objects.equals(url, link.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, watched);
    }
}
