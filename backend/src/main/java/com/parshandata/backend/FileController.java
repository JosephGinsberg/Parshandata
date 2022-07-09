package com.parshandata.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FileController {
	// @GetMapping("/{page:^(?!.*[.].*$).*$}")
	@GetMapping(value={"/", "/about"})
	public String loadFile(@PathVariable("page") String page) throws FileNotFoundException {
		if(page == "/") page = "index";
		page += ".html";
		System.out.print(page);

		// URL url = getClass().getResource(page);
		// File file = new File(url.getPath());
		// if(!file.exists()) page = "404";

		return page;
	}
}
