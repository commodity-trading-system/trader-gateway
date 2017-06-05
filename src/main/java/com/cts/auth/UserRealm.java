package com.cts.auth;
import com.cts.dao.TraderDao;
import com.cts.entity.Trader;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fy on 2017/6/3.
 */
public class UserRealm extends AuthorizingRealm {
    @Resource
    private TraderDao traderDao;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        String role = "user";
        Set<String> permissions = new HashSet<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(role);
        info.addStringPermissions(permissions);
        return info;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken)token;
        String username = upToken.getUsername();
        Trader user = traderDao.getTraderByName(username);
        if(user == null)
            return null;
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getName(),user.getPassword(), getName());
        return info;
    }
}
