package com.quixxxy.solmyr.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CacheServiceImpl implements CacheService {

	@Cacheable("test")
	public String someMethod(int a) {
		System.out.println("wow wow polehshe");
		return String.valueOf(a + 5);
	}

}
