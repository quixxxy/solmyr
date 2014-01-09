package com.quixxxy.solmyr.jmx;

import net.sf.ehcache.config.CacheConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Scope;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
@ManagedResource(objectName = "somyr:name=EhCache", description = "EhCache mbean")
public class EhCacheMBean {
		
	private static final String TEST_CACHE_NAME = "test";
	
	@Autowired
	private CacheManager cacheManager; 

	@ManagedAttribute(description = "Get cache's name")
    public String getName() {
    	return getCacheConfiguration().getName();
    }

	@ManagedAttribute(description = "Maximum elements that could be stored")
    public int getMaxElementsInMemory(){
    	return getCacheConfiguration().getMaxElementsInMemory();
    }
    
	@ManagedAttribute(description = "Sets the time to idle for an element before it expires")
    public void setTimeToLiveSeconds(int ttl){
    	getCacheConfiguration().setTimeToLiveSeconds(ttl);
    }
	
	@ManagedAttribute(description = "lolololol")
    public int getClear() {
		getCache().clear();
		return 1;
    }
	
	@ManagedAttribute(description = "The average get time in ms")
    public float getAverageGetTime() {
		return getNativeCache().getAverageGetTime();
    }
	
	@ManagedAttribute(description = "Gets the size of the cache")
    public int getSize() {
		return getNativeCache().getKeysWithExpiryCheck().size();
    }
	
	@ManagedAttribute(description = "Whether this cache is disabled")
    public boolean isDisabled() {
		return getNativeCache().isDisabled();
    }
    
	@ManagedAttribute(description = "Disables or enables this cache")
    public void setDisabled(boolean isDisabled) {
		getNativeCache().setDisabled(isDisabled);
    }

	
    private CacheConfiguration getCacheConfiguration() {
		return getNativeCache().getCacheConfiguration();
    }

	private net.sf.ehcache.Cache getNativeCache() {
		return (net.sf.ehcache.Cache) getCache().getNativeCache();
	}

	private Cache getCache() {
		return cacheManager.getCache(TEST_CACHE_NAME);
	}
}
