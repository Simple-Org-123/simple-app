package com.example.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
		String directory = "<table>";
		
		directory += "<tr>";
		directory += "  <th>Thing</th>";
		directory += "  <th>Route</th>";
		directory += "</tr>";
		
		directory += "<tr>";
		directory += "  <td>[internal] How are you?</td>";
		directory += "  <td><a href=\"/how\">/how</a></td>";
		directory += "</tr>";
		
		directory += "<tr>";
		directory += "  <td>[internal] How now?</td>";
		directory += "  <td><a href=\"/now\">/now</a></td>";
		directory += "</tr>";

		directory += "<tr>";
		directory += "  <td>[external] Random Neuron Firings</td>";
		directory += "  <td><a href=\"/yow\">/yow</a></td>";
		directory += "</tr>";
		
		directory += "<tr>";
		directory += "  <td>[internal] Runtime Environment</td>";		
		directory += "  <td><a href=\"/envs\">/envs</a></td>";
		directory += "</tr>";
		
		directory += "</table>";
		
		return "Greetings from Spring Boot + Tanzu!<br><br>\n" + directory;
	}

	@RequestMapping("/how")
	public String how() {
		return "How are you today?";
	}
	
	@RequestMapping("/now")
	public String now() {
		return "Brown Cow?";
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
		String uri = System.getenv("SIMPLE_API_URL");
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		return result;
	}
}
