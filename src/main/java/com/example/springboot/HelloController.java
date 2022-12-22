package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {

		Map<RequestMappingInfo, HandlerMethod> handlerMethods = re.getHandlerMethods();
		List<String> urls = new ArrayList<>();
		for (Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
				urls.addAll((entry.getKey().getPatternsCondition().getPatterns()));
		}

		return "Greetings from Spring Boot + Tanzu!<br><br>\n" + urls;
	}

	@RequestMapping("/how")
	public String how() {
		return "How are you today?";
	}

	@RequestMapping("/env")
	public String env(String name) {
		String val = System.getenv(name);
		if (val != null) {
			return "Environment variable: " + name + " = " + val + "\n";
		} else {
			return "Environment variable: " + name + " is not assigned\n";
		}
	}

	@RequestMapping("/envs")
	public String envs() {
		Map<String, String> envMap = System.getenv();
		String buffer = "";
		for (String envName : envMap.keySet()) {
			buffer +=  "Environment variable: " + envName + " = " + envMap.get(envName) + "<br>\n";
		}
		return(buffer);
	}

	@RequestMapping("/yow")
	public String yow() {
		String uri = "https://simple-api.dev-tap.tap.bmath.nyc";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		return result;
	}

}