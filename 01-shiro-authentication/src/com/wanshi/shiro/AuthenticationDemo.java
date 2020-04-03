package com.wanshi.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * 完成用户认证功能
 */
public class AuthenticationDemo {
	public static void main(String[] args) {
		// 创建SecurityManager工厂 读取相应的配置文件
		Factory<SecurityManager> securityManager = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 通过SecurityManager工厂获取SecurityManager实例
		SecurityManager manager = securityManager.getInstance();
		// 将SecurityManager对象放入运行环境中
		SecurityUtils.setSecurityManager(manager);
		// 通过SecurityUtils获取主体subject
		Subject subject = SecurityUtils.getSubject();
		// 登录 这个地方的zhangsan 1111是登录的信息
		// 而shiro.ini中的数据是数据库中存放的信息
		UsernamePasswordToken uToken = new UsernamePasswordToken("zhangsan", "1111");
		try {
			// 进行用户身份认证
			subject.login(uToken);
			// 通过subject来判断用户是否通过验证
			if (subject.isAuthenticated()) {
				System.out.println("登录成功");
			}
		} catch (AuthenticationException e) {
			e.printStackTrace();
			System.out.println("登录失败");
		}
	}
}
