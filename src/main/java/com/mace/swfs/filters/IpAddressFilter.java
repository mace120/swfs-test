package com.mace.swfs.filters;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IpAddressFilter extends OncePerRequestFilter {

    private final List<String> allowedIpAddresses;

    public IpAddressFilter() {
        // Define your list of allowed IP addresses
        allowedIpAddresses = new ArrayList<>();
        allowedIpAddresses.add("127.0.0.1");
        allowedIpAddresses.add("0:0:0:0:0:0:0:1");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String clientIpAddress = request.getRemoteAddr();
        filterChain.doFilter(request, response);
//        if (allowedIpAddresses.contains(clientIpAddress)) {
//            filterChain.doFilter(request, response);
//        } else {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            response.getWriter().write("Access Denied: Your IP address is not allowed.");
//        }
    }
}
