package cn.itcast.ssm.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author Xuejun Yang
 * @version V1.0
 * @description: TODO
 * @date 2019/5/30 16:42
 */

@Component
@Aspect
public class LogAdvice {

    //After
    //AfterThrowing
    //AfterReturning
    @Pointcut("execution(* cn.itcast.ssm.ctl.*.*(..))")


    //匹配所有ServiceImpl包下面的所有类的所有方法
    public void addLog(){}

    @Before("addLog()")
    public void before(JoinPoint jPoint){
        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ra.getRequest();
        String userId = request.getParameter("userId");
    }

    @Around("addLog()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        //获得request对象
        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ra.getRequest();

        Object[] args = pjp.getArgs();//获取方法参数

        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("method:"+method.getName());
        Object proceed = pjp.proceed();

        //   String userId = request.getParameter("userId");

      return proceed;

    }

}
