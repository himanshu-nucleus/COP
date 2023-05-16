//package com.ecommerce.product.filter;
//
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.filter.GenericFilterBean;
//
//import com.ecommerce.product.repository.UserClient;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Configuration
//public class CustomFilter extends GenericFilterBean {
//
//	@Autowired
//	private UserClient userClient;
//
//	@Override
//	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
//			final FilterChain filterChain) throws IOException, ServletException {
//
//		HttpServletRequest request = (HttpServletRequest) servletRequest;
//		HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//		if (isActuatorEndPoint(request) || request.getMethod().equalsIgnoreCase("OPTIONS")) {
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }
//		
//		filterChain.doFilter(servletRequest, servletResponse);
//	}
//	
//	 /**
//     * This method checks for actutors end point.
//     * @param request
//     * @return boolean
//     * */
//    private boolean isActuatorEndPoint(final HttpServletRequest request) {
//
//        String[] actuatorEndpoints = {"/product/create", "/actuator/log", "/actuator/info"};
//        for (String endpoint : actuatorEndpoints) {
//            if (request.getRequestURI().indexOf(endpoint) != -1) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//}