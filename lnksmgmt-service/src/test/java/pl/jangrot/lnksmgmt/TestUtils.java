package pl.jangrot.lnksmgmt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

public final class TestUtils {

    private TestUtils() {
    }

    public static String randomStringUUID() {
        return UUID.randomUUID().toString();
    }

    public static byte[] json(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(o);
    }
}
