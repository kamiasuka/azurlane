package ask.azurlane.commom.Interceptor;

import ask.azurlane.commom.jwt.JwtUtils;
import ask.azurlane.commom.response.JsonResult;
import ask.azurlane.commom.response.StatusCode;
import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component //当前拦截器对象由Spring创建和管理
@Slf4j
public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (false) {
            //获取请求url
            String url = request.getRequestURL().toString();
            log.info("请求的url: {}", url);
            //请求url包含login或者下载，放行
            if (url.contains("login") || url.contains("download") || url.contains("doc")) {
                log.info("登录(下载)操作, 放行...");
                return true;
            }
            //如果请求为 OPTIONS 请求，则返回 true,否则需要通过jwt验证
            if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
                System.out.println("OPTIONS请求，放行");
                return true;
            }

            //获取请求头中的令牌（token）
            String jwt = request.getHeader("Authorization");
            System.out.println("获取请求头中的令牌" + jwt);
            //判断令牌是否存在
            if (!StringUtils.hasLength(jwt)) {
                log.info("请求头token为空,返回未登录的信息");
                String message = "操作失败，您的登录信息已经过期，请重新登录！";
                JsonResult jsonResult = JsonResult.fail(StatusCode.NOT_LOGIN, message);
                String result = JSON.toJSONString(jsonResult);
                response.getWriter().write(result);
                return false;
            }

            //解析token，如果解析失败，返回错误结果
            try {
                System.out.println("解析前端发送的token令牌:" + jwt);
                JwtUtils.parseJWT(jwt);
            } catch (RuntimeException e) {//jwt解析失败
                e.printStackTrace();
                log.info("解析令牌失败, 返回未登录错误信息");
                String message = "解析令牌失败, 返回未登录错误信息!";
                JsonResult jsonResult = JsonResult.fail(StatusCode.LOGIN_OUT_TIME, message);
                String result = JSON.toJSONString(jsonResult);
                response.getWriter().write(result);
                return false;
            }

            //放行
            log.info("令牌合法, 放行");
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
