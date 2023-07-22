package com.shortlink.assignment.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.shortlink.assignment.model.ShortUrl;

public interface ShortUrlRepository extends MongoRepository<ShortUrl, String> {

	    Optional<ShortUrl> findByShortUrl(String shortURL);
}
