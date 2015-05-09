/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.cas.client.proxy;

import com.liferay.portal.kernel.cache.CacheListener;
import com.liferay.portal.kernel.cache.CacheListenerScope;
import com.liferay.portal.kernel.cache.PortalCache;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.2.0
 */
public class LiferayBackedProxyGrantingTicketStorageImplTests extends TestCase {

    public void testEncryptionMechanisms() throws Exception {
        final PortalCache<String, String> portalCache = new LocalCache();
        final LiferayBackedProxyGrantingTicketStorageImpl cache = new LiferayBackedProxyGrantingTicketStorageImpl(portalCache);
        assertNull(cache.retrieve(null));
        assertNull(cache.retrieve("foobar"));
        cache.save("proxyGrantingTicketIou", "proxyGrantingTicket");
        assertEquals("proxyGrantingTicket", cache.retrieve("proxyGrantingTicketIou"));
        assertTrue("proxyGrantingTicket".equals(portalCache.get("proxyGrantingTicketIou")));
    }

    private static class LocalCache implements PortalCache<String, String> {
        private static final Logger LOG = LoggerFactory.getLogger(LocalCache.class);
        private Map<String, String> cache = new HashMap<String, String>();

        public void destroy() {
            LOG.debug("Destroy");
        }

        public Collection<String> get(Collection<String> collection) {
            return null;
        }

        public String get(String s) {
            return cache.get(s);
        }

        public String getName() {
            return "Local Test Cache";
        }

        public void put(String s, String s2) {
            cache.put(s, s2);
        }

        public void put(String s, String s2, int i) {
            throw new UnsupportedOperationException();
        }

        public void registerCacheListener(CacheListener<String, String> cacheListener) {
            throw new UnsupportedOperationException();
        }

        public void registerCacheListener(CacheListener<String, String> cacheListener, CacheListenerScope cacheListenerScope) {
            throw new UnsupportedOperationException();
        }

        public void remove(String s) {
            cache.remove(s);
        }

        public void removeAll() {
            cache.clear();
        }

        public void unregisterCacheListener(CacheListener<String, String> cacheListener) {
            throw new UnsupportedOperationException();
        }

        public void unregisterCacheListeners() {
            throw new UnsupportedOperationException();
        }
    }
}
