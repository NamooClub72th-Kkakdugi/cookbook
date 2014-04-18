package com.namoo.cookbook.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import net.wimpi.telnetd.io.terminal.ansi;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.crsh.shell.impl.command.system.repl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.namoo.cookbook.domain.Cookbook;
import com.namoo.cookbook.domain.ImageFile;
import com.namoo.cookbook.domain.Recipe;
import com.namoo.cookbook.service.facade.CookbookService;

/**
 * 요리책 컨트롤러
 * @author kosta-19
 *
 */
@Controller
public class CookbookController {
	
	@Value("#{cookbook['imageRootPath']}")
	private String imageRoot;
	
	@Autowired
	private CookbookService cookbookService;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView cookbook() {
		//
		Cookbook cookbook = cookbookService.getCookbook();
		return new ModelAndView("cookbook", "cookbook", cookbook);
	}
	
	@RequestMapping(value = "/recipe/{recipeName}", method = RequestMethod.GET)
	public ModelAndView recipe(@PathVariable("recipeName") String recipeName) {
		//
		Recipe recipe = cookbookService.getRecipe(recipeName);
		return new ModelAndView("recipe", "recipe", recipe);
	}
	
	@RequestMapping(value = "/recipe/add", method = RequestMethod.GET)
	public String addRecipe() {
		return "register";
	}
	
	@RequestMapping(value = "/recipe/add", method = RequestMethod.POST)
	public View addRecipe(
			Recipe recipe,
			@RequestParam("imageFile") MultipartFile file) throws IOException {
		//
		setupImageToRecipe(recipe, file);
		
		cookbookService.registerRecipe(recipe);
		return new RedirectView("/main", true);
	}
	
	@RequestMapping(value = "/recipe/{recipeName}/image", method = RequestMethod.GET)
	public void getRecipeImage(
			@PathVariable("recipeName") String recipeName, HttpServletResponse resp) throws IOException {
		//
		Recipe recipe = cookbookService.getRecipe(recipeName);
		ImageFile imageFile = recipe.getRecipeImage();
		String contentType = null;
		InputStream in = null;
		if (imageFile != null) {
			contentType = imageFile.getContentType();
			in = new FileInputStream(new File(imageRoot+ imageFile.getFileName()));
		} else {
			contentType = "image/jpeg";
			in = this.getClass().getResourceAsStream("/default.jpg");
		} 
		try {
			resp.setContentType(contentType);
			IOUtils.copy(in, resp.getOutputStream());
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
	
	@RequestMapping(value="/recipe/{recipeName}/delete", method = RequestMethod.GET)
	public void deleteRecipe(@PathVariable("recipeName") String recipeName, HttpServletResponse resp) throws IOException {
		//
		cookbookService.removeRecipe(recipeName);
		resp.sendRedirect("/cookbook/main");
	}
	
	
	
	
	//--------------------------------------------------------------------------
	// private method
	
	private void setupImageToRecipe(Recipe recipe, MultipartFile file) throws IOException {
	    //
		if (file.isEmpty()) return;
		
		StringBuffer sb = new StringBuffer();
		sb.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		sb.append(".");
		sb.append(FilenameUtils.getExtension(file.getOriginalFilename()));
		String saveFileName = sb.toString();
		File saveFile = new File(imageRoot + saveFileName);

		FileCopyUtils.copy(file.getBytes(), saveFile);
		System.out.println("image saved in " + saveFile.getCanonicalPath());

		String contentType = file.getContentType();
		recipe.setRecipeImage(new ImageFile(contentType, saveFileName));
	   }
}
