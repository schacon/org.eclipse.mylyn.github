package org.eclipse.mylyn.github.internal;

// hello

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

public class GitHub {
	public static final String BUNDLE_ID = "org.eclipse.mylyn.github.core";
	public static final String CONNECTOR_KIND = "github";

	public static final String HTTP_WWW_GITHUB_ORG = "http://www.github.org";
	public static final String HTTP_GITHUB_COM = "http://github.com";

	public static final Pattern URL_PATTERN = Pattern.compile("(?:"+Pattern.quote(HTTP_WWW_GITHUB_ORG)+"|"+Pattern.quote(HTTP_GITHUB_COM)+")/([^/]+)/([^/]+)");

	public static IStatus createStatus(int severity, String message) {
		return new Status(severity, BUNDLE_ID, message);
	}

	public static IStatus createStatus(int severity, String message, Throwable e) {
		return new Status(severity, BUNDLE_ID, message, e);
	}

	public static IStatus createErrorStatus(String message) {
		return createStatus(IStatus.ERROR, message);
	}

	public static IStatus createErrorStatus(String message, Throwable t) {
		return createStatus(IStatus.ERROR, message, t);
	}

	public static IStatus createErrorStatus(Throwable e) {
		return createStatus(IStatus.ERROR, "Unexpected error: "
				+ e.getMessage(), e);
	}

	public static ILog getLog() {
		return Platform.getLog(Platform.getBundle(BUNDLE_ID));
	}
	
	public static void logError(String message,Throwable t) {
		getLog().log(createErrorStatus(message, t));
	}
	
	public static void logError(Throwable t) {
		getLog().log(createErrorStatus(t.getMessage(), t));
	}

	public static String computeTaskRepositoryUser(String repositoryUrl) {
		Matcher matcher = URL_PATTERN.matcher(repositoryUrl);
		if (matcher.matches()) {
			return matcher.group(1);
		}
		return null;
	}

	public static String computeTaskRepositoryProject(String repositoryUrl) {
		Matcher matcher = URL_PATTERN.matcher(repositoryUrl);
		if (matcher.matches()) {
			return matcher.group(2);
		}
		return null;
	}
	
	/**
	 * uses github.com
	 * @see #createGitHubUrlAlternate(String, String)
	 */
	public static String createGitHubUrl(String user,String project) {
		return HTTP_GITHUB_COM+'/'+user+'/'+project;
	}

	/**
	 * Uses www.github.org
	 * @see #createGitHubUrl(String, String)
	 */
	public static String createGitHubUrlAlternate(String user,String project) {
		return HTTP_WWW_GITHUB_ORG+'/'+user+'/'+project;
	}
}
