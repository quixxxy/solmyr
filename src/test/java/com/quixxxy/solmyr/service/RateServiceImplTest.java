package com.quixxxy.solmyr.service;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.quixxxy.solmyr.dao.RateDao;
import com.quixxxy.solmyr.domain.Rate;
import com.quixxxy.solmyr.jmx.IQuotesSettings;
import com.quixxxy.solmyr.service.impl.RateServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:cache-test-context.xml")
public class RateServiceImplTest extends Assert {

	private RateDao mockDao;
	private RateServiceImpl rateService;

	@Before
	public void setUp() throws Exception {
		mockDao = EasyMock.createMock(RateDao.class);
		rateService = new RateServiceImpl();
		rateService.setRateDao(mockDao);
	}

	@Test
	public void testGetRates() {
		assertNull(rateService.getRates());
	}

	@Test
	public void testAddRate() {
		rateService.addRate(new Rate());
	}

	@Test
	public void testDeleteRate() {
		rateService.deleteRate(0L);
	}

	@Test
	public void textJmx() {
		JMXServiceURL url;
		JMXConnector jmxc = null;
		try {
			url = new JMXServiceURL(
					"service:jmx:rmi://localhost/jndi/rmi://localhost:50447/jmxrmi");

			jmxc = JMXConnectorFactory.connect(url, null);
			MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

			ObjectName mxbeanName = new ObjectName("somyr:name=QuotesSettings");
			IQuotesSettings mxbeanProxy = JMX.newMXBeanProxy(mbsc, mxbeanName,
					IQuotesSettings.class);

			
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		} catch (MalformedObjectNameException e) {
		} finally {
			try {
				jmxc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
