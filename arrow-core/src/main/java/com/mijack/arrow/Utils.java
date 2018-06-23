/*
 *   Copyright (C) 2018 Mi&Jack.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.mijack.arrow;

import java.util.List;

/**
 * @author Mi&Jack
 * @since 2018/6/23
 */
public class Utils {

    public static void checkNotNull(Object o) {
        checkNotNull(o, "object is null！");
    }

    public static void checkNotNull(Object o, String msg) {
        if (o == null) throw new IllegalStateException(msg);
    }

    public static void checkNull(Object o) {
        checkNull(o, "object is not null！");
    }

    public static void checkNull(Object o, String msg) {
        if (o != null) throw new IllegalStateException(msg);
    }

    public static void checkTrue(boolean value, String msg) {
        if (!value) throw new IllegalStateException(msg);
    }

    public static void checkFalse(boolean value, String msg) {
        if (value) throw new IllegalStateException(msg);
    }

    public static int findCharsInString(String source, String target) {
        return findCharsInString(source, target, 0);
    }

    public static int findCharsInString(String source, String target, int offset) {
        for (int i = offset; i < source.length(); i++) {
            if (target.indexOf(source.charAt(i)) > 0) {
                return i;
            }
        }
        return -1;
    }

    public static int startWith(String source, List<String> target) {
        return startWith(source, target, 0);
    }

    public static int startWith(String source, List<String> target, int offset) {
        for (int i = 0; i < target.size(); i++) {
            if (startWith(source, offset, target.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public static boolean startWith(String source, int offset, String target) {
        checkNotNull(source);
        checkNotNull(target);
        for (int i = 0; i < target.length(); i++) {
            if (offset + i > source.length() || source.charAt(offset + i) != target.charAt(i)) {
                return false;
            }
        }
        return true;
    }


    public static int indexCharOf(String source, String chars, int offsite) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < chars.length(); i++) {
            int indexOf = source.indexOf(chars.charAt(i), offsite);
            if (indexOf >= 0) {
                min = Math.min(min, indexOf);
            }
        }
        return Integer.MAX_VALUE != min ? min : -1;
    }

    public static int indexCharOf(String source, String chars) {
        return indexCharOf(source, chars, 0);
    }
}
