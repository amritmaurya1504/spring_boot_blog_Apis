package com.rajamrit.SpringBoot_Blog_app.Security;

import com.rajamrit.SpringBoot_Blog_app.config.AppConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTTokenHelper {

    private final static String secret = "heythisismyfirstspringbootapplication";

    /*
    In the context of JSON Web Tokens (JWTs) and authentication, a "claim" is a piece of information that
    is encoded within the JWT itself. A JWT consists of three parts: a header, a payload (which contains claims),
    and a signature. Claims are the actual data that the JWT holds and represents various assertions about the
    user or the token itself.
    1. In your provided code, the JWTTokenHelper class is responsible for working with JWTs, and it has methods
    to manipulate and extract information from JWT claims. Here are some key methods related to claims in your code:
    2. getClaimFromToken(String token, Function<Claims, T> claimsResolver): This method allows you to retrieve a
    specific claim from a given JWT token. It takes a Function as an argument, which defines how to extract the
    desired claim from the token's payload.
    3. getAllClaimsFromToken(String token): This method decodes the JWT token, parses its payload, and returns all
    the claims present in the token.
    4. generateToken(UserDetails userDetails): This method is used to generate a new JWT token for a user. It prepares
    a set of claims that you want to include in the token, such as the user's username, roles, and other relevant information.
    5. doGenerateToken(Map<String, Object> claims, String subject): This private method is responsible for actually creating
     the JWT token by specifying the claims you want to include. It sets the subject (usually the username) of the token,
     the token's issuance date, and its expiration date.
    6. In the context of your code, claims are pieces of information about the user that are stored within the JWT token.
    These claims can be used to represent user roles, permissions, and other relevant attributes needed for authentication
     and authorization purposes.
     */


    // retrieve username from jwt token
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    // retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // check if the token has expired
    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // generate token for user
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + AppConstants.JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
