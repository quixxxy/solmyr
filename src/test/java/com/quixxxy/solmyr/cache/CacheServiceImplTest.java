package com.quixxxy.solmyr.cache;

import net.sf.ehcache.config.CacheConfiguration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:cache-test-context.xml")
public class CacheServiceImplTest {

	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private CacheManager cacheManager; 
	
	@Test
	public void testSomeMethod() throws InterruptedException {
//		for (int i = 1; i < 1000; i++) {
//			cacheService.someMethod(i);			
//		}
		cacheService.someMethod(1);
		
		Cache cache = cacheManager.getCache("test");
		net.sf.ehcache.Cache ehcache = (net.sf.ehcache.Cache) cache.getNativeCache();
		CacheConfiguration config = ehcache.getCacheConfiguration();
		config.setTimeToLiveSeconds(1);
		
		cacheService.someMethod(1);
	}

}
