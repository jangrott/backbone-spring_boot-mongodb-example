package pl.jangrot.lnksmgmt;

import java.util.UUID;

public final class TestUtils {

    private TestUtils() {
    }

    public static String randomStringUUID() {
        return UUID.randomUUID().toString();
    }
}
