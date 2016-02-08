package pl.jangrot.lnksmgmt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

import static pl.jangrot.lnksmgmt.Utils.randomStringUUID;

public final class TestUtils {

    private TestUtils() {
    }

    public static byte[] json(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(o);
    }

    public static Link createLink(String url, boolean watched) {
        Link link = new Link();
        link.setId(randomStringUUID());
        link.setUrl(url);
        link.setWatched(watched);
        return link;
    }
}
