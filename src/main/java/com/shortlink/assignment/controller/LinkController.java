package com.shortlink.assignment.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shortlink.assignment.config.Constants;
import com.shortlink.assignment.model.ShortUrl;
import com.shortlink.assignment.service.RedirectUrlService;
import com.shortlink.assignment.service.ShortUrlService;

import jakarta.servlet.http.HttpServletResponse;
@RestController
public class LinkController {
	
	@Autowired
	private ShortUrlService shortUrlService;
	
	@Autowired
	private RedirectUrlService redirectUrlService;
	
	String expiredRedirectUrl = Constants.EXPIRED_REDIRECT_URL;
	String invalidLinkRedirectUrl = Constants.INVALID_LINK_REDIRECT_URL;
	String issueRedirectUrl = Constants.ISSUE_REDIRECT_URL;
	
	@GetMapping("/getGreetings")
	public String helloWorld() {
		
		return "my name is vashist";
		
	}
	@GetMapping("/getNewUrl")
	public ResponseEntity<String> getNewUrl(@RequestParam(name = "url")  @NotBlank String url){
		 if (url == null || url.trim().isEmpty()) {
	            throw new IllegalArgumentException("Please Provide Valid URL");
	        }
		
		String shortUl = shortUrlService.getShortUrl(url);
		return ResponseEntity.ok(shortUl);
	}
	
	
	
	@GetMapping("/{shortString}")
	
	public void routeToOriginalUrl(@PathVariable("shortString") String shortUrl,HttpServletResponse response)throws IOException{
		try {
			
			Optional<ShortUrl> redirectUrl = redirectUrlService.getRedirectUrl(shortUrl);
			
			if(redirectUrl.isPresent()) {
				ShortUrl shortUrlEntity = redirectUrl.get();
				LocalDateTime expirationDate = shortUrlEntity.getExpirationTime();
				LocalDateTime curentDateandTime = LocalDateTime.now();
				 String originalURL = shortUrlEntity.getOriginalURL();
	             if (!originalURL.startsWith("http://") && !originalURL.startsWith("https://") && !originalURL.isEmpty()) {
	                 originalURL = "http://" + originalURL;
	             }
				if(curentDateandTime.isBefore(expirationDate)) {
					
					response.sendRedirect(originalURL);
					return;
				} else {
					response.sendRedirect( Constants.EXPIRED_REDIRECT_URL);
					return;
				}
				
			}else {
				response.sendRedirect(Constants.INVALID_LINK_REDIRECT_URL);
				return;
			}
		
			
		} catch (Exception e) {
			response.sendRedirect(Constants.ISSUE_REDIRECT_URL);
			return;
		}
		
	}

}
