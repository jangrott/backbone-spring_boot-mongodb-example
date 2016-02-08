package pl.jangrot.lnksmgmt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Objects;

public final class Link {

    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    private boolean watched;

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
