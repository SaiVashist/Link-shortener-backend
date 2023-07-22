package com.shortlink.assignment.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shortlink.assignment.model.ShortUrl;
import com.shortlink.assignment.repository.ShortUrlRepository;

@Service
public class ShortUrlService {
	
	@Autowired
	private ShortUrlRepository shortUrlRepository;
	
	public String getShortUrl(String url) {
		
		String newUrl = generateNewUrl(url);
		String commonString = "http://localhost:8080/";
		
		LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(5);
		
		ShortUrl shortUrl = new ShortUrl();
		shortUrl.setExpirationTime(expirationTime);
		shortUrl.setOriginalURL(url);
		shortUrl.setShortUrl(newUrl);
		shortUrlRepository.save(shortUrl);
		
		
		return commonString+newUrl;
	}

	private String generateNewUrl(String url) {
		String newUrl = UUID.randomUUID().toString().replace("-", "").substring(0,8);
		return newUrl;
	}

}
