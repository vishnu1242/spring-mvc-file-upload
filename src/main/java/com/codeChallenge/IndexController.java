package com.codeChallenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.io.Files;

@Controller
public class IndexController {

	String message = "Hello ";

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		resolver.setMaxUploadSize(20000000);
		resolver.setResolveLazily(false);
		return resolver;
	}

	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {

		ModelAndView mv = new ModelAndView("index");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public ModelAndView uploadfile(@RequestParam String userName, String password, HttpServletRequest request,
			HttpSession session) throws IOException, ServletException {

		HashMap<String, String> loginInfo = new HashMap<String, String>();
		loginInfo.put("test", "123456");
		loginInfo.put("test2", "1234567");
		loginInfo.put("test3", "1234568");

		int status = 0;
		String key = "";
		for (Entry<String, String> entryset : loginInfo.entrySet()) {
			if (entryset.getKey().equals(userName) && entryset.getValue().equals(password)) {
				status = 1;
				key = entryset.getKey();
				System.out.println("Login Sucessfull");
			}
		}

		if (status == 1) {
			ArrayList<String> filename = new ArrayList<String>();
			message = "Choose Files";
			ModelAndView mv = new ModelAndView("index");
			mv.addObject("message", message);
			mv.addObject("file", filename);
			mv.addObject("key", key);
			return mv;

		} else {
			String error = "LogIn Failed";
			ModelAndView mv = new ModelAndView("login");
			mv.addObject("error", error);
			return mv;
		}
	}

	@RequestMapping("/logout")
	public ModelAndView logout() {

		message = "Logout success";
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("message", message);
		return mv;
	}

	@RequestMapping(value = "/savefile", method = RequestMethod.POST)
	public ModelAndView upload(@RequestParam List<CommonsMultipartFile> uploadFiles, HttpServletRequest request,
			HttpSession session) throws IOException, ServletException {

		ArrayList<FileProcessed> filesUploaded = new ArrayList<FileProcessed>();

		File tempDir = com.google.common.io.Files.createTempDir();

		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		MultiValueMap<String, MultipartFile> multipartReqMap = multiRequest.getMultiFileMap();
		multipartReqMap.forEach((reqParam, multipartFile) -> {
			multipartFile.forEach(mfile -> {
				File newFile = new File(tempDir, mfile.getOriginalFilename());
				System.out.println("filePath is ：" + newFile.toString());
				try {
					mfile.transferTo(newFile);

					// Read file and append its content
					try (Stream<String> stream = java.nio.file.Files.lines(Paths.get(newFile.getAbsolutePath()))) {

						Optional<String> first = stream.findFirst();
						FileProcessed fileResult = new FileProcessed();
						fileResult.setFileName(mfile.getOriginalFilename());
						fileResult.setFirstLine(first.get());
						filesUploaded.add(fileResult);

					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}

			});
		});

		message = "Upload success";

		ModelAndView mv = new ModelAndView("index");
		mv.addObject("message", message);
		mv.addObject("files", filesUploaded);
		return mv;
	}
}
