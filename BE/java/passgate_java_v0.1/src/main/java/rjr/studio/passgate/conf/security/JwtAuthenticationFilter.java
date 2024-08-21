package rjr.studio.passgate.conf.security;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;

	@Autowired
	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");
		
		if (header == null || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = header.substring(7);

		try {
			jwtTokenProvider.validateToken(token);

			String username = jwtTokenProvider.getUsernameFromToken(token);
			List<String> roles = jwtTokenProvider.getRolesFromToken(token);
			Collection<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());

			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
					authorities);
			auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(auth);

		} catch (Exception ex) {
			// TODO: eccezione da gestire
			// Gestione dell'eccezione
			// logger.error("Error occurred during JWT processing: {}", ex.getMessage());
			// response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			System.out.println("***************** ERRORE DA MODIFICARE *****************");
			System.out.println(ex.getMessage());
		}
		
		filterChain.doFilter(request, response);
	}
}
