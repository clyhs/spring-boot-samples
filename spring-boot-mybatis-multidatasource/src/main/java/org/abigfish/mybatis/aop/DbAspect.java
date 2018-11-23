package org.abigfish.mybatis.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;




@Aspect
@Component
public class DbAspect extends DataSourceAspect {
 
    @Pointcut("execution(* org.abigfish.mybatis.dao.*.*(..))")
    @Override
    protected void datasourceAspect() {
        super.datasourceAspect();
    }
}
