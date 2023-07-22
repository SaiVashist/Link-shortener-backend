package com.shortlink.assignment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shortlink.assignment.model.ShortUrl;
import com.shortlink.assignment.repository.ShortUrlRepository;

@Service
public class RedirectUrlService {
	
	@Autowired
	private ShortUrlRepository shortUrlRepository;
	
	public Optional<ShortUrl> getRedirectUrl(String shortUrl) {
		
		Optional<ShortUrl> redirectUrl = shortUrlRepository.findByShortUrl(shortUrl);
		
		
		return redirectUrl;
	}

}
