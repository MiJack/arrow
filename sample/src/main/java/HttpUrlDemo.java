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

import com.mijack.arrow.http.HttpUrl;

/**
 * @author Mi&Jack
 * @since 2018/6/23
 */
public class HttpUrlDemo {
    public static void main(String[] args) {
        test("http://mijack:paw@mijack.com:8080/sss/xx?xx=cc&xxxx=cc#fff");
        test("http://mijack:paw@mijack.com:8080/sss/xx?xx=cc&xxxx=cc");
        test("http://mijack:paw@mijack.com:8080?xx=cc&xxxx=cc");
        test("http://mijack:paw@mijack.com:8080/sss/xx#fff");
        test("http://mijack:paw@mijack.com:8080");
        test("http://mijack:paw@mijack.com");
        test("http://mijack@mijack.com:8080/sss/xx#fff");
        test("http://mijack.com:8080/sss/xx?xx=cc&xxxx=cc#fff");
        test("http://mijack.com:8080/sss/xx?xx=cc&xxxx=cc");
        test("http://mijack.com:8080/sss/xx#fff");
        test("http://mijack.com/sss/xx#fff");
        test("http://mijack.com#fff");
        test("http://mijack.com/sss/xx?xx=cc&xxxx=cc");
        test("http://mijack.com/sss/xx");
        test("http://mijack.com");

    }

    private static void test(String url) {
        try {
            HttpUrl httpUrl = HttpUrl.parse(url);
            if (!url.equals(httpUrl.toString())) {
                throw new Exception("\n" + url + "\n" + httpUrl);
            }
            System.out.println("✅:\n" + url + "\n" + httpUrl);
        } catch (Exception e) {
//            e.printStackTrace(System.out);
            System.out.println("❌:" + e.getMessage());
        }
    }
}
