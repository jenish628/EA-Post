package edu.miu.post.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.post.clients.UserVerificationRequest;
import edu.miu.post.dto.ErrorResponse;
import edu.miu.post.dto.Verification;
import edu.miu.post.dto.VerifyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {


    @Autowired  private UserVerificationRequest userVerification;

    @Autowired private JWTUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authorizationHeader.substring(7);
            String username = jwtUtils.parseToken(token);
            Verification verification = userVerification.verifyUserDetail(
                    VerifyRequest.builder()
                            .email(username)
                            .build()
            );

            VerifiedUserDetail verifiedUserDetail = new VerifiedUserDetail(verification);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    new ArrayList<>()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception exception) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, exception.getMessage(), request.getServletPath());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            ObjectMapper mapper = new ObjectMapper();
            PrintWriter out = response.getWriter();
            out.print(mapper.writeValueAsString(errorResponse));
            out.flush();
        }
    }
}
