package com.jqmkj.java.common.security;

import com.jqmkj.java.common.config.JqmkjProperties;
import com.jqmkj.java.util.PublicUtil;
import com.jqmkj.java.util.domain.CustomMessage;
import com.jqmkj.java.web.rest.base.BaseResource;
import com.jqmkj.java.web.rest.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import sun.misc.Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Returns a 401 error code (Unauthorized) to the client.
 */
@Component
public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    private final Logger log = LoggerFactory.getLogger(Http401UnauthorizedEntryPoint.class);
    @Autowired
    private JqmkjProperties jqmkjProperties;

    /**
     * Always returns a 401 error code to the client.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
            throws IOException,
            ServletException {

        log.debug("Pre-authenticated entry point called. Rejecting access");
        if (jqmkjProperties.getHttp().isRestful()
                || jqmkjProperties.getGatewayModel()
                || RequestUtil.isRestfulRequest(request)) {
            BaseResource.writeJsonHttpResponse(CustomMessage.createDataError("error_token",
                "权限不足或登录超时"), response);

        } else {
            response.sendRedirect(PublicUtil.toAppendStr(request.getContextPath(), jqmkjProperties.getAdminPath(), "/login"));
        }

    }
}
