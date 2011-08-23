package org.zkoss.fiddle.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.zkoss.fiddle.FiddleConstant;
import org.zkoss.fiddle.model.Case;
import org.zkoss.fiddle.visualmodel.UserVO;
import org.zkoss.service.login.IUser;
import org.zkoss.zk.ui.Session;

public class UserUtil {

	public static String getUserView(Case thecase){
		return getUserView(thecase.getAuthorName(), thecase.isGuest());
	}

	public static String getUserView(UserVO uservo){
		return getUserView(uservo.getUserName(), uservo.isGuest());
	}

	public static String getUserView(String userName,Boolean isGuest){
		if(isGuest == null) isGuest = false;

		try{
			return "/user/"+ ( isGuest ? "guest/" :"") + URLEncoder.encode(userName,  FiddleConstant.CHARSET_UTF_8);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("wrong user name " + userName, e);
		}
	}

	public static Boolean isAdmin(HttpSession sess) {
		return isLogin(sess) && (getLoginUser(sess).getRole() == FiddleConstant.ROLE_ADMIN);
	}

	public static Boolean isAdmin(Session sess) {
		return isAdmin((HttpSession) sess.getNativeSession());
	}

	public static void login(HttpSession sess, IUser user) {
		sess.setAttribute(FiddleConstant.SESSION_ATTR_USER, user);
	}

	public static void login(Session sess, IUser user) {
		login((HttpSession) sess.getNativeSession(), user);
	}

	public static IUser getLoginUser(HttpSession sess) {
		return (IUser) sess.getAttribute(FiddleConstant.SESSION_ATTR_USER);
	}

	public static void logout(HttpSession sess) {
		sess.removeAttribute(FiddleConstant.SESSION_ATTR_USER);
	}

	public static void logout(Session sess) {
		logout(((HttpSession) sess.getNativeSession()));
	}


	public static IUser getLoginUser(Session sess) {
		return getLoginUser((HttpSession) sess.getNativeSession());
	}

	public static Boolean isLogin(HttpSession sess) {
		return sess.getAttribute(FiddleConstant.SESSION_ATTR_USER) != null;
	}

	public static Boolean isLogin(Session sess) {
		return isLogin((HttpSession) sess.getNativeSession());
	}
}
