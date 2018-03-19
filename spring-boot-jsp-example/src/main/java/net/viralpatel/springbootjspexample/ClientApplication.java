package net.viralpatel.springbootjspexample;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.netflix.discovery.EurekaClient;

import static org.springframework.web.util.UriComponentsBuilder.fromUri;

import java.util.ArrayList;
import java.util.List;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class ClientApplication extends SpringBootServletInitializer {

	@Autowired
	private EurekaClient discoveryClient;
	private static Log logger = LogFactory.getLog(ClientApplication.class);
	@Autowired
	private DiscoveryClient clients;
	@Autowired
	private LoadBalancerClient client;
	@Autowired
	private RestTemplate restTemplate;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ClientApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ClientApplication.class);
		application.setAdditionalProfiles("client");
		application.run(args);
	}

	@RequestMapping(value = "/Home", method = RequestMethod.GET)
	public String welcomePage() {

		String s = "server";
		List<String> myList = new ArrayList();
		List<String> myList2 = clients.getServices();
		for (String ob : myList2) {
			if (ob.substring(ob.length() - 6, ob.length()).equals(s)) {
				myList.add("" + ob + "");
			}
		}
		ArrayList<String> sa = new ArrayList<String>();
		sa.add("some string1");
		sa.add("some string2");
		sa.add("some string3");

		String html = "<html><head><title>Insert title here</title></head><body>" + "List of avaible services: "
				+ myList.toString() + "<br><br><br><br>For access to services type " + "/fetch/{service-name}"
				+ "from Url</body></html>";

		return html;
	}

}
