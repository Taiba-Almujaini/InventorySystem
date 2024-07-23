package com.TRA.tra24Springboot.logging;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ControllerLoggingAdvice {
    public static Logger logger = LoggerFactory.getLogger(ControllerLoggingAdvice.class);


    @Pointcut(value = "execution(* com.TRA.tra24Springboot.Controllers.*.*(..))")
    public void pointCutDefinitionInventory() {
    }

    @Before(value = "pointCutDefinitionInventory()")
    public void logBefore(JoinPoint pjp) {
        System.out.println("Before method: " + pjp.getSignature().getName());
    }

    @AfterReturning(value = "pointCutDefinitionInventory()", returning = "result")
    public void logAfterReturning(JoinPoint pjp, Object result) {
        System.out.println("After method: " + pjp.getSignature().getName() + ", Result: " + result);
    }

    @Around(value = "pointCutDefinitionInventory()")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();

        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] array = pjp.getArgs();

        logger.info("\n***** Before function execution ****"+"\nThis class is running: " + className + "\nThis function is running " + methodName + "() " + "\nFunction arguments : "
                + mapper.writeValueAsString(array));

        Object object = pjp.proceed();

        logger.info("\n**** After function execution ****"+ "\nThis class is running: " + className + "\nThis function is running " + methodName + "()" + "\nResponse : "
                + mapper.writeValueAsString(object));
        return object;
    }
}
