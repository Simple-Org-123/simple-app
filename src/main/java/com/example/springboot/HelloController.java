package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot + Tanzu!";
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