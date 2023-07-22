package com.shortlink.assignment.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection  = "short_urls")
@AllArgsConstructor
@NoArgsConstructor
public class ShortUrl {
	
	@Id
    private String id;
    private String originalURL;
    private String shortUrl;
    private LocalDateTime expirationTime;


}
