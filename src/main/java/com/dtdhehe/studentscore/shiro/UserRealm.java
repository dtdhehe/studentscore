package com.dtdhehe.studentscore.shiro;

import com.dtdhehe.studentscore.entity.User;
import com.dtdhehe.studentscore.service.UserService;
import com.dtdhehe.studentscore.util.ConstantUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 陈珊珊
 * @version 1.0
 * @date 2019/11/05 20:49
 * @description
 **/
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进行授权");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        //授权

        return authorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进行认证");
        String userName = (String) authenticationToken.getPrincipal();
        User dbUser = userService.findByUserName(userName);
        if (dbUser == null){
            //用户不存在
            throw new UnknownAccountException();
        }
        if (ConstantUtils.NOTACTIVE.equals(dbUser.getValidFlag())){
            throw new DisabledAccountException();
        }
        if (ConstantUtils.LOCKED.equals(dbUser.getValidFlag())){
            throw new LockedAccountException();
        }
        return new SimpleAuthenticationInfo(dbUser,dbUser.getPassword(), ByteSource.Util.bytes(dbUser.getUserName()),getName());
    }
}
