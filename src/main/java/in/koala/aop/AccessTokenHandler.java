package in.koala.aop;

import in.koala.controller.dto.KeywordSettingRequest;
import in.koala.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AccessTokenHandler {

    private final UserService userService;

    @Around("execution(* in.koala.controller.KeywordController.getUserKeywordSetting(..)) || " +
            "execution(* in.koala.controller.KeywordController.modifyKeywordSetting(..))")
    public Object getUserIdFromAccessToken(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] Args = joinPoint.getArgs();

        Long userId = userService.getLoginUserInfo().getId();
        Args[0] = userId;

        Object resultObj = joinPoint.proceed(Args);
        return resultObj;
    }

}
