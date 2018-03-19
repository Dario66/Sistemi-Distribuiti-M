package net.viralpatel.springbootjspexample;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
	private static Log logger = LogFactory.getLog(WelcomeController.class);
	@Autowired
	private DiscoveryClient clients;

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RequestMapping(value = "/Homes", method = RequestMethod.GET)
	public String viewRegister3(Map<String, Object> model, ModelMap mod) {
		User userForm = new User();
		model.put("userForm2", userForm);

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
		mod.addAttribute("modules", myList);
		String html;
		html = "<html><head><title>Simple Page</title></head>";
		return "welcome";
	}

	@RequestMapping(value = "/fetch/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getInfo(@PathVariable("id") String id) {
		System.out.println("id:" + id);

		String name = restTemplate.getForObject("http://" + id + "/name", String.class);
		logger.info(name.toString());

		return name;
	}

}
