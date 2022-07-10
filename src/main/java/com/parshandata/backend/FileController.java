package com.parshandata.backend;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileController {
	@GetMapping(value={"", "/", "/search"})
	public String serveFileAsModel(HttpServletRequest httpRequest) {
		String URImodel = httpRequest.getRequestURI();
		if(URImodel.length()<=1) URImodel = "/index";
		return URImodel;
	}
	// System.getenv(name)
}