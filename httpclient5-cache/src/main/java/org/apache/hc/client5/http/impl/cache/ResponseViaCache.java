/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.hc.client5.http.impl.cache;

import java.io.IOException;

import org.apache.hc.core5.annotation.Contract;
import org.apache.hc.core5.annotation.ThreadingBehavior;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.HttpResponseInterceptor;
import org.apache.hc.core5.http.ProtocolVersion;
import org.apache.hc.core5.http.protocol.HttpContext;

/**
 * This response interceptor adds {@literal VIA} header to all incoming
 * response messages dispatched through the caching layer.
 */
@Contract(threading = ThreadingBehavior.IMMUTABLE)
class ResponseViaCache implements HttpResponseInterceptor {

    /**
     * Default instance of {@link ResponseViaCache}.
     */
    public static final ResponseViaCache INSTANCE = new ResponseViaCache();

    @Override
    public void process(final HttpResponse response,
                        final EntityDetails entity,
                        final HttpContext context) throws HttpException, IOException {
        final ProtocolVersion protocolVersion = context.getProtocolVersion();
        response.addHeader(HttpHeaders.VIA, ViaCacheGenerator.INSTANCE.lookup(protocolVersion));
    }

}
