package in.koala.interceptor;

import in.koala.enums.ErrorMessage;
import in.koala.enums.TokenType;
import in.koala.exception.NonCriticalException;
import in.koala.util.JwtUtil;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ChatInterceptor implements ChannelInterceptor {
    @Resource(name="redisTemplate")
    private SetOperations<String, String> setOps;

    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    @Resource(name="jwtUtil")
    private JwtUtil jwtUtil;

    @Override
    public void postSend(Message message, MessageChannel channel, boolean sent){
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if(accessor.getCommand().equals(StompCommand.CONNECT)){
            String id = this.getId(accessor);
            Long memCnt = listOps.rightPush(id, accessor.getSessionId());
            if(memCnt == 1){ setOps.add("member", id); }
            return;
        }

        if(accessor.getCommand().equals(StompCommand.DISCONNECT)){
            String id = this.getId(accessor);
            listOps.remove(id, 0, accessor.getSessionId());
            if(listOps.size(id) <= 0) setOps.remove("member", id);
            return;
        }
    }

    private String getId(StompHeaderAccessor accessor){
        if(accessor.getFirstNativeHeader("Authorization") == null)
            throw new NonCriticalException(ErrorMessage.SOCKETTOKEN_NOT_FOUNDED);
        return String.valueOf(jwtUtil.validateToken(accessor.getFirstNativeHeader("Authorization"), TokenType.ACCESS).get("id"));
    }
}
