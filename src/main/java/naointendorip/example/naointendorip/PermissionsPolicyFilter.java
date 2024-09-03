package naointendorip.example.naointendorip;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class PermissionsPolicyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //httpServletResponse.setHeader("Permissions-Policy", "geolocation=(self), microphone=()");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // init config if needed
    }

    @Override
    public void destroy() {
        // clean up if needed
    }
}
