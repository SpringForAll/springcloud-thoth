package com.prometheus.thoth.common.util;

import java.util.UUID;

/**
 * generate short id for distributed system
 * must configure NTP server
 * Created by zhuhuaiqi on 2017/3/22.
 */
public class IdGenerator {
    private IdGenerator() {
    }

    /**
     * short uuid
     *
     * @return
     */
    public static String shortNext() {
        UUID u = UUID.randomUUID();
        return toIDString(u.getMostSignificantBits()) + toIDString(u.getLeastSignificantBits());
    }

    /**
     * uuid
     *
     * @return
     */
    public static String next() {
        String id = UUID.randomUUID().toString();
        return id;
    }

    private static String toIDString(long i) {
        char[] buf = new char[32];
        int z = 64; // 1 << 6;
        int cp = 32;
        long b = z - 1;
        do {
            buf[--cp] = DIGITS66[(int) (i & b)];
            i >>>= 6;
        } while (i != 0);
        return new String(buf, cp, (32 - cp));
    }

    // array de 64+2 digitos
    private final static char[] DIGITS66 = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '-', '.', '_', '~'
    };
}
