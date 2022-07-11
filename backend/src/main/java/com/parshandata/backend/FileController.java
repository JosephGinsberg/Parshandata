package com.parshandata.backend;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileController {
	// @RequestMapping(value={"{URI:/search|/}"})
	@GetMapping(value={"", "/", "/search"})
	public String serveFileAsModel(@PathVariable("URI") String URIendpoint, HttpServletRequest httpRequest) {

		System.out.println(URIendpoint);
		// System.out.println(httpRequest.getRequestURI());
		// System.out.println(httpRequest.getRequestURI());
		// System.out.println(httpRequest.getRequestURI());
		// System.out.println(httpRequest.getRequestURI());
		// System.out.println(httpRequest.getRequestURI());

		String URImodel = httpRequest.getRequestURI();
		// String URImodel = httpRequest.getRequestURL().toString();
		if(URImodel.length() <= 1){
			URImodel = "/index";
		}
		URImodel = URImodel.replace("/", "");
		System.out.println("! " + URImodel + " !");
		return URImodel;
	}
	// System.getenv(name)
}