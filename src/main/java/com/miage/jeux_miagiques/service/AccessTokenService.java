package com.miage.jeux_miagiques.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class AccessTokenService {
	
	 private final Map<String, Instant> tokenStore = new HashMap<>();
	 private final Map<String, String> emailToToken = new HashMap<>();

	    public String generateToken(String email, String role) {
	        String token = UUID.randomUUID().toString() + ";" + email + ";" + role;
	        storeToken(token, email);
	        return token;
	    }

	    public String getTokenByEmail(String email) {
	        return emailToToken.get(email);
	    }

	    private void storeToken(String token, String email) {
	        Instant expiryTime = calculateExpiryTime();
	        tokenStore.put(token, expiryTime);
	        emailToToken.put(email, token);
	    }

	    public boolean isTokenExpired(String token) {
	        Instant expiryTime = tokenStore.get(token);
	        return expiryTime == null || expiryTime.isBefore(Instant.now());
	    }

	    private Instant calculateExpiryTime() {
	        return Instant.now().plus(1, ChronoUnit.HOURS);
	    }
	    
	    public void invalidateToken(String email) {
	        if (emailToToken.containsKey(email)) {
	            emailToToken.remove(email);
	        }
	    }
}
