package com.example.jwtExam.jwt.filter;

import com.example.jwtExam.jwt.exception.JwtExceptionCode;
import com.example.jwtExam.jwt.token.JwtAuthenticationToken;
import com.example.jwtExam.jwt.util.JwtTokenizer;
import com.example.jwtExam.security.dto.CustomUserDetails;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenizer jwtTokenizer;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        if(StringUtils.hasLength(token)){
            try{
                Authentication authentication = getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch (ExpiredJwtException e){
                request.setAttribute("exception", JwtExceptionCode.EXPIRED_TOKEN.getCode());
                log.info("Expired token : {}", token, e);
                SecurityContextHolder.clearContext();
                throw new BadCredentialsException("Expired token exception");
            }catch (UnsupportedJwtException e){
                request.setAttribute("exception", JwtExceptionCode.UNSUPPORTED_TOKEN.getCode());
                log.info("Unsupported token : {}", token, e);
                SecurityContextHolder.clearContext();
                throw new BadCredentialsException("Unsupported token exception");
            }catch (MalformedJwtException e){
                request.setAttribute("exception", JwtExceptionCode.INVALID_TOKEN.getCode());
                log.info("Invalid token : {}", token, e);
                SecurityContextHolder.clearContext();
                throw new BadCredentialsException("Invalid token exception");
            } catch (IllegalArgumentException e){
                request.setAttribute("exception", JwtExceptionCode.NOT_FOUND_TOKEN.getCode());
                log.info("Not found token : {}", token, e);
                SecurityContextHolder.clearContext();
                throw new BadCredentialsException("Not found token exception");
            } catch (Exception e){
                log.info("JWT Filter - Internal Eroor : {}",token, e);
                SecurityContextHolder.clearContext();
                throw new BadCredentialsException("JWT Filter - Internal Eroor exception");
            }

        }
        filterChain.doFilter(request, response);
    }
    private Authentication getAuthentication(String token) {
        Claims claims = jwtTokenizer.parseAccessToken(token);
        String email = claims.getSubject();
        Long userId = claims.get("userId", Long.class);
        String name = claims.get("name", String.class);
        String username = claims.get("username", String.class);

        List<GrantedAuthority> grantedAuthorities = getAuthorities(claims);

        CustomUserDetails customUserDetails = new CustomUserDetails(username,"",name, grantedAuthorities);
        return new JwtAuthenticationToken(grantedAuthorities, customUserDetails, null);
    }

    private List<GrantedAuthority> getAuthorities(Claims claims) {
        List<String> roles = (List<String>)claims.get("roles");
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    private String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        //쿠키에 accessToken 이 있는지 찾아서 리턴
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if("accessToken".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}
