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

import static com.mijack.arrow.Utils.checkNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import com.mijack.arrow.http.HttpUrl;
import com.mijack.arrow.model.Call;

/**
 * @author Mi&Jack
 * @since 2018/06/23
 */
public class Arrow {
//    public static final class Builder {
//        private Call.Factory callFactory;
//        private HttpUrl baseUrl;
//        private final List<Converter.Factory> converterFactories = new ArrayList<>();
//        private final List<CallAdapter.Factory> callAdapterFactories = new ArrayList<>();
//        private Executor callbackExecutor;
//        private boolean validateEagerly;
//
//
//        public Builder() {
//        }
//
//        Builder(Arrow arrow) {
//            callFactory = arrow.callFactory;
//            baseUrl = arrow.baseUrl;
//
//            converterFactories.addAll(arrow.converterFactories);
//            // Remove the default BuiltInConverters instance added by build().
//            converterFactories.remove(0);
//
//            callAdapterFactories.addAll(arrow.callAdapterFactories);
//            // Remove the default, platform-aware call adapter added by build().
//            callAdapterFactories.remove(callAdapterFactories.size() - 1);
//
//            callbackExecutor = arrow.callbackExecutor;
//            validateEagerly = arrow.validateEagerly;
//        }
//
//        public Builder callFactory(okhttp3.Call.Factory factory) {
//            this.callFactory = checkNotNull(factory, "factory == null");
//            return this;
//        }
//
//        public Builder baseUrl(String baseUrl) {
//            checkNotNull(baseUrl, "baseUrl == null");
//            HttpUrl httpUrl = HttpUrl.parse(baseUrl);
//            if (httpUrl == null) {
//                throw new IllegalArgumentException("Illegal URL: " + baseUrl);
//            }
//            return baseUrl(httpUrl);
//        }
//
//
//
//        public Builder baseUrl(HttpUrl baseUrl) {
//            checkNotNull(baseUrl, "baseUrl == null");
//            List<String> pathSegments = baseUrl.pathSegments();
//            if (!"".equals(pathSegments.get(pathSegments.size() - 1))) {
//                throw new IllegalArgumentException("baseUrl must end in /: " + baseUrl);
//            }
//            this.baseUrl = baseUrl;
//            return this;
//        }
//
//        /** Add converter factory for serialization and deserialization of objects. */
//        public Builder addConverterFactory(Converter.Factory factory) {
//            converterFactories.add(checkNotNull(factory, "factory == null"));
//            return this;
//        }
//
//        /**
//         * Add a call adapter factory for supporting service method return types other than {@link
//         * Call}.
//         */
//        public Builder addCallAdapterFactory(CallAdapter.Factory factory) {
//            callAdapterFactories.add(checkNotNull(factory, "factory == null"));
//            return this;
//        }
//
//        public Builder callbackExecutor(Executor executor) {
//            this.callbackExecutor = checkNotNull(executor, "executor == null");
//            return this;
//        }
//
//        public List<CallAdapter.Factory> callAdapterFactories() {
//            return this.callAdapterFactories;
//        }
//
//        public List<Converter.Factory> converterFactories() {
//            return this.converterFactories;
//        }
//
//        public Builder validateEagerly(boolean validateEagerly) {
//            this.validateEagerly = validateEagerly;
//            return this;
//        }
//
//        public Arrow build() {
//            if (baseUrl == null) {
//                throw new IllegalStateException("Base URL required.");
//            }
//
//            okhttp3.Call.Factory callFactory = this.callFactory;
//            if (callFactory == null) {
//                callFactory = new OkHttpClient();
//            }
//
//            Executor callbackExecutor = this.callbackExecutor;
//            if (callbackExecutor == null) {
//                callbackExecutor = platform.defaultCallbackExecutor();
//            }
//
//            // Make a defensive copy of the adapters and add the default Call adapter.
//            List<CallAdapter.Factory> callAdapterFactories = new ArrayList<>(this.callAdapterFactories);
//            callAdapterFactories.add(platform.defaultCallAdapterFactory(callbackExecutor));
//
//            // Make a defensive copy of the converters.
//            List<Converter.Factory> converterFactories =
//                    new ArrayList<>(1 + this.converterFactories.size());
//
//            // Add the built-in converter factory first. This prevents overriding its behavior but also
//            // ensures correct behavior when using converters that consume all types.
//            converterFactories.add(new BuiltInConverters());
//            converterFactories.addAll(this.converterFactories);
//
//            return new Retrofit(callFactory, baseUrl, unmodifiableList(converterFactories),
//                    unmodifiableList(callAdapterFactories), callbackExecutor, validateEagerly);
//        }
//    }
}
