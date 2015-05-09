# BUILDING THE CAS CLIENT FOR JAVA

Please note that to be deployed in Maven Central, we mark a number of JARs as provided (related to JBoss and Memcache
Clients).  In order to build the clients, you must enable the commented out repositories in the appropriate pom.xml
files in the modules (cas-client-integration-jboss and cas-client-support-distributed-memcached) or follow the
instructions on how to install the file manually.


## UMS Updates.

* Configure the cache within the ```bash webapps/ROOT/WEB-INF/web.xml```

```xml
<init-param>
  <param-name>proxyGrantingTicketStorageClass</param-name>
  <param-value>org.jasig.cas.client.proxy.LiferayBackedProxyGrantingTicketStorageImpl</param-value>
</init-param>
```

* Additional CAS Caching setups need to be done for the Liferay VM Multipool. This is a definition of an integrated
Liferay Ehcache cache.


```xml
<cache
   name="edu.maine.cas.client.LiferayBackedProxyGrantingTicketStorageImpl.cache"
   maxElementsInMemory="100"
   eternal="false"
   timeToIdleSeconds="100"
   timeToLiveSeconds="100"
   overflowToDisk="false">
   <cacheEventListenerFactory class="com.liferay.portal.cache.ehcache.LiferayCacheEventListenerFactory"/>
</cache>
```