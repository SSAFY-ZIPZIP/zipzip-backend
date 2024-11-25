package org.ssafy.zipzipapiapp.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {

    @Pointcut("@annotation(org.ssafy.zipzipapiapp.common.aop.Timer))")//Timer 어노테이션이 붙은 메서드에만 적용
    private void enableTimer() {
    }

    @Around("enableTimer()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 메서드 실행 및 결과 저장
        Object result = joinPoint.proceed();

        stopWatch.stop();
        System.out.println("total time : " + stopWatch.getTotalTimeSeconds() + " seconds");

        // 원래 메서드의 결과 반환
        return result;
    }

}