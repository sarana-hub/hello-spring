package hello.hellospring.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/** 시간 측정 AOP 등록 */

@Component //스프링빈으로 등록
@Aspect
public class TimeTraceAop {

    //원하는 적용 대상을 선택할 수 있다. @Around("execution(*패키지명..*(..))")
    @Around("execution(* hello.hellospring..*(..))")    //hello.hellospring 패키지 하위에 모두 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());   //어떤 메소드 호출되는지 출력
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
