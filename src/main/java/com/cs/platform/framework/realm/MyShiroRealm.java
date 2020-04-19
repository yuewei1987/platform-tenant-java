package com.cs.platform.framework.realm;

import com.cs.platform.framework.core.UserProfile;
import com.cs.platform.framework.entity.Menu;
import com.cs.platform.framework.entity.User;
import com.cs.platform.framework.service.UserService;
import com.cs.platform.framework.util.Encodes;
import com.cs.platform.framework.util.Passwrods;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    /**
     * hash密码加密校验
     */
    protected static HashedCredentialsMatcher hashedCredentialsMatcher;

    static {
        // 设定Password校验的Hash算法与迭代次数.
        hashedCredentialsMatcher = new HashedCredentialsMatcher(Passwrods.HASH_ALGORITHM);
        hashedCredentialsMatcher.setHashIterations(Passwrods.HASH_INTERATIONS);
    }
//    @Autowired
//    private MenuService menuService;

    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;

    /**
     * 验证通过后会调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserProfile userProfile = (UserProfile) principals.getPrimaryPrincipal();
        logger.info("doGetAuthorizationInfo = {},{}", userProfile.getUserId(), userProfile.getUserName(), userProfile.getLoginAccount());
        List<Menu> menus = userProfile.getMenus();
        if (menus != null) {
            for (Menu m : menus) {
                authorizationInfo.addStringPermission(m.getName());
            }
        }
        // rest上加上   @RequiresPermissions("userInfo:add")
//        if (StringUtils.equals(userProfile.getUserId(), InitDataService.DEFAULT_USER_ID)) {
//        authorizationInfo.addRole("admin");
//        }
        return authorizationInfo;
    }

    /* 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。 */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        setCredentialsMatcher(hashedCredentialsMatcher);
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        logger.info("username = {}, host = {}", token.getUsername(), token.getHost());
        User user = null;//userService.findUser(token.getUsername());
        if (user == null) {
            return null;
        }

        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(user.getUserId());
        userProfile.setUserName(user.getName());
        userProfile.setLoginAccount(user.getAccount());
        userProfile.setType(user.getType());
        userProfile.setAvatarUrl(user.getAvatarUrl());
//        userProfile.setMenus(menuService.getMenu(user.getUserId()));

        String password = user.getPassword();
        byte[] salt = Encodes.decodeHex(password.substring(0, 16));
        return new SimpleAuthenticationInfo(userProfile, password.substring(16), ByteSource.Util.bytes(salt), getName());
    }
}
