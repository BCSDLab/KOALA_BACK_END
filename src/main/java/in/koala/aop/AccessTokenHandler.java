package in.koala.aop;

import in.koala.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AccessTokenHandler {

    private final UserService userService;

    @Around("execution(* in.koala.controller.KeywordController.getUserKeywordSetting(Long))")
    public Object getUserIdFromAccessToken(ProceedingJoinPoint joinPoint) throws Throwable {

        Long userId = userService.getLoginUserInfo().getId();
        Object resultObj = joinPoint.proceed(new Object[] { userId });
        return resultObj;
    }

}
