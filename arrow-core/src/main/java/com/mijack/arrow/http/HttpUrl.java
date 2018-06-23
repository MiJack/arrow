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

package com.mijack.arrow.http;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.mijack.arrow.Utils;

import com.google.common.collect.Lists;

/**
 * @author Mi&Jack
 * @since 2018/6/23
 */
public class HttpUrl {

    private static final List<String> SUPPORT_SCHEMAS =
            Lists.newArrayList("http://", "https://", "ftp://");
    final String scheme;

    private final String username;

    private final String password;

    final String host;

    final int port;

    private final List<String> pathSegments;

    private final List<String> queryNamesAndValues;

    private final String fragment;
    private final String url;

    HttpUrl(Builder builder) {
        this.scheme = builder.scheme;
        this.username = builder.username;
        this.password = builder.password;
        this.host = builder.host;
        this.port = builder.port;
        this.pathSegments = builder.pathSegments;
        this.queryNamesAndValues = builder.queryNamesAndValues;
        this.fragment = builder.fragment;
        this.url = builder.toString();
    }

    public static HttpUrl parse(String url) {
        HttpUrl.Builder builder = new Builder();
        int i = Utils.startWith(url, SUPPORT_SCHEMAS);
        Utils.checkTrue(i >= 0, "未找到支持的协议");
        int offsite = 0;
        String scheme = SUPPORT_SCHEMAS.get(i);
        builder.scheme(scheme.substring(0, scheme.length() - 3));
        offsite += scheme.length();
        int hostEnd = Utils.indexCharOf(url, "/?#", offsite);
        String optionPart = hostEnd > 0 ? url.substring(hostEnd) : null;
        String mainPart = hostEnd > 0 ? url.substring(offsite, hostEnd) : url.substring(offsite);
        int indexCharOf = Utils.indexCharOf(mainPart, "@");
        if (indexCharOf > 0) {
            String usernameAndPassword = mainPart.substring(0, indexCharOf);
            int indexOf1 = usernameAndPassword.indexOf(":");
            if (indexOf1 > 0) {
                builder.username(usernameAndPassword.substring(0, indexOf1))
                        .password(usernameAndPassword.substring(indexOf1 + 1));
            } else {
                builder.username(usernameAndPassword);
            }
        }
        String mainHost = indexCharOf > 0 ? mainPart.substring(indexCharOf + 1) : mainPart;
        int hostIndex = mainHost.indexOf(":");
        if (hostIndex > 0) {
            builder.host(mainHost.substring(0, hostIndex))
                    .port(Integer.parseInt(mainHost.substring(hostIndex + 1)));
        } else {
            builder.host(mainHost);
        }
        if (optionPart != null) {
            offsite = Utils.indexCharOf(optionPart, "?#");
            if (offsite > 0) {
                builder.addPathSegments(optionPart.substring(0, offsite));
            } else if (offsite < 0) {
                builder.addPathSegments(optionPart);
            }
            int offsiteEnd = Utils.indexCharOf(optionPart, "#");
            // query
            if (offsite != offsiteEnd) {
                offsiteEnd = offsiteEnd == -1 ? optionPart.length() : offsiteEnd;
                String query = optionPart.substring(offsite + 1, offsiteEnd);
                builder.query(query);
            }
            if (offsiteEnd >= 0 && offsiteEnd < optionPart.length()) {
                builder.fragment(optionPart.substring(offsiteEnd + 1));
            }
        }
        return builder.build();
    }


    /**
     * Returns this URL as a {@link URL java.net.URL}.
     */
    public URL url() {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e); // Unexpected!
        }
    }

    @Override
    public String toString() {
        return url;
    }

    public Builder builder() {
        return new Builder().scheme(scheme)
                .username(username).password(password)
                .host(host).port(port)
                ;
    }

    public static class Builder {
        String scheme;
        String username = "";
        String password = "";
        String host;
        int port = -1;
        final List<String> pathSegments = new ArrayList<>();
        List<String> queryNamesAndValues;
        String fragment;

        public Builder scheme(String scheme) {
            if (scheme == null) {
                throw new NullPointerException("scheme == null");
            } else if (scheme.equalsIgnoreCase("http")) {
                this.scheme = "http";
            } else if (scheme.equalsIgnoreCase("https")) {
                this.scheme = "https";
            } else {
                throw new IllegalArgumentException("unexpected scheme: " + scheme);
            }
            return this;
        }

        public Builder username(String username) {
            if (username == null) throw new NullPointerException("username == null");
            this.username = username;
            return this;
        }


        public Builder password(String password) {
            if (password == null) throw new NullPointerException("password == null");
            this.password = password;
            return this;
        }

        public Builder host(String host) {
            if (host == null) throw new NullPointerException("host == null");
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            if (port <= 0 || port > 65535) throw new IllegalArgumentException("unexpected port: " + port);
            this.port = port;
            return this;
        }


        public Builder addPathSegment(String pathSegment) {
            if (pathSegment == null) throw new NullPointerException("pathSegment == null");
            pathSegments.add(pathSegment);
            return this;
        }

        public Builder addPathSegments(String pathSegments) {
            if (pathSegments == null) throw new NullPointerException("pathSegments == null");
            int offset = 0;
            do {
                int segmentEnd = pathSegments.indexOf('/', offset + 1);
                if (segmentEnd < 0) {
                    segmentEnd = pathSegments.indexOf('?', offset + 1);
                }
                if (segmentEnd < 0) {
                    segmentEnd = pathSegments.indexOf('#', offset + 1);
                }
                if (segmentEnd < 0) {
                    segmentEnd = pathSegments.length();
                }
                addPathSegment(pathSegments.substring(offset + 1, segmentEnd));
                offset = segmentEnd;
            } while (offset < pathSegments.length());
            return this;
        }

        public Builder setPathSegment(int index, String pathSegment) {
            if (pathSegment == null) throw new NullPointerException("pathSegment == null");
            pathSegments.set(index, pathSegment);
            return this;
        }

        public Builder removePathSegment(int index) {
            pathSegments.remove(index);
            if (pathSegments.isEmpty()) {
                pathSegments.add(""); // Always leave at least one '/'.
            }
            return this;
        }

        public Builder query(String query) {
            this.queryNamesAndValues = query != null
                    ? queryStringToNamesAndValues(query)
                    : null;
            return this;
        }

        private List<String> queryStringToNamesAndValues(String query) {
            String[] split = query.split("&");
            List<String> list = new ArrayList<>();
            for (String s: split) {
                String[] queryData = s.split("=");
                list.add(queryData[0]);
                list.add(queryData[1]);
            }
            return list;
        }


        public Builder addQueryParameters(String querys) {
            if (querys == null) throw new NullPointerException("querys == null");
            if (queryNamesAndValues == null) queryNamesAndValues = new ArrayList<>();
            String[] split = querys.split("&");
            for (String s: split) {
                String[] strings = s.split("=");
                addQueryParameter(strings[0], strings[1]);
            }
            return this;
        }

        public Builder addQueryParameter(String name, String value) {
            if (name == null) throw new NullPointerException("name == null");
            if (queryNamesAndValues == null) queryNamesAndValues = new ArrayList<>();
            queryNamesAndValues.add(name);
            queryNamesAndValues.add(value);
            return this;
        }

        public Builder setQueryParameter(String name, String value) {
            removeAllQueryParameters(name);
            addQueryParameter(name, value);
            return this;
        }

        public Builder removeAllQueryParameters(String name) {
            if (name == null) throw new NullPointerException("name == null");
            if (queryNamesAndValues == null) return this;
            String nameToRemove = name;
            removeAllCanonicalQueryParameters(nameToRemove);
            return this;
        }

        private void removeAllCanonicalQueryParameters(String canonicalName) {
            for (int i = queryNamesAndValues.size() - 2; i >= 0; i -= 2) {
                if (canonicalName.equals(queryNamesAndValues.get(i))) {
                    queryNamesAndValues.remove(i + 1);
                    queryNamesAndValues.remove(i);
                    if (queryNamesAndValues.isEmpty()) {
                        queryNamesAndValues = null;
                        return;
                    }
                }
            }
        }

        public Builder fragment(String fragment) {
            this.fragment = fragment;
            return this;
        }

        public HttpUrl build() {
            if (scheme == null) throw new IllegalStateException("scheme == null");
            if (host == null) throw new IllegalStateException("host == null");
            return new HttpUrl(this);
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append(scheme);
            result.append("://");

            if (!username.isEmpty() || !password.isEmpty()) {
                result.append(username);
                if (!password.isEmpty()) {
                    result.append(':');
                    result.append(password);
                }
                result.append('@');
            }

            if (host.indexOf(':') != -1) {
                // Host is an IPv6 address.
                result.append('[');
                result.append(host);
                result.append(']');
            } else {
                result.append(host);
            }

            if (port != -1) {
                result.append(':');
                result.append(port);
            }
            pathSegmentsToString(result, pathSegments);

            if (queryNamesAndValues != null) {
                result.append('?');
                namesAndValuesToQueryString(result, queryNamesAndValues);
            }

            if (fragment != null) {
                result.append('#');
                result.append(fragment);
            }

            return result.toString();
        }

        private void namesAndValuesToQueryString(StringBuilder result, List<String> queryNamesAndValues) {
            for (int i = 0; i < queryNamesAndValues.size() / 2; i++) {
                if (i != 0) {
                    result.append("&");
                }
                result.append(queryNamesAndValues.get(2 * i)).append("=").append(queryNamesAndValues.get(2 * i + 1));
            }
        }

        private void pathSegmentsToString(StringBuilder result, List<String> pathSegments) {
            for (String path: pathSegments) {
                result.append("/").append(path);
            }
        }
    }
}
