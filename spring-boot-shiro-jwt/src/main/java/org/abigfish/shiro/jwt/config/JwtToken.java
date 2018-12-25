package org.abigfish.shiro.jwt.config;
 
import org.apache.shiro.authc.AuthenticationToken;
 
/**
 * 
 * @author abigfish
 *
 * @date 2018年12月25日
 */
public class JwtToken implements AuthenticationToken {
 
    private String token;
 
    public JwtToken(String token) {
        this.token = token;
    }
 
    @Override
    public Object getPrincipal() {
        return token;
    }
 
    @Override
    public Object getCredentials() {
        return token;
    }
}
