package itcastzull.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义的登录验证过滤器
 *
 * @author zc
 */
@Component
public class LogFilter extends ZuulFilter {

    /**
     * 过滤器类型：pre route post error
     * pre：在zuul服务路由之前执行
     * route:在zuul服务路由过程中执行
     * post:在route和error后执行（执行请求返回结果后执行）
     * error: 处理请求出现异常后执行
     * 正常流程：
     * 请求到达首先会经过pre类型过滤器，而后到达routing类型，进行路由，请求就到达真正的服务提供者，
     * 执行请求，返回结果后，会到达post过滤器。而后返回响应。
     * 异常流程：
     * 整个过程中，pre或者routing过滤器出现异常，都会直接进入error过滤器，再error处理完毕后，会将请求交给POST过滤器，最后返回给用户。
     * 如果是error过滤器自己出现异常，最终也会进入POST过滤器，而后返回。
     * 如果是POST过滤器出现异常，会跳转到error过滤器，但是与pre和routing不同的时，请求不会再到达POST过滤器了。
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤顺序：同一个类型的过滤器，filterOrder返回值越小优先级越高
     * 通常返回的数字都应该是可扩展的（前面可以加数字），例如10前面可以加1-9，而如果写0的话，
     * 如果还有一个过滤器并且优先级应当高于当前过滤器那么就会无法扩展。
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 10;
    }

    /**
     * 返回true时执行run()方法
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 编写过滤的代码逻辑。
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        // 获取zuul提供的请求上下文对象，注意是zuul包下
        RequestContext context = RequestContext.getCurrentContext();
        // 获取tomcat的HttpServletRequest对象
        HttpServletRequest request = context.getRequest();
        // 获取请求参数token,本案例模拟用户登录，如果token请求参数不为null，认定用户登录。
        String token = request.getParameter("token");
        // 判断用户是否已经登录
        if (StringUtils.isBlank(token)) {
            // 设置网关响应为false，zuul不再进行服务路由
            context.setSendZuulResponse(false);
            // 设置响应状态码 401-未授权
            context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            // 设置响应提示（注意响应中文会乱码）
            context.setResponseBody("no authentication , please log in first");
        }
        // 校验通过，可以考虑把用户信息放入上下文，继续向后执行
        return null;
    }
}
