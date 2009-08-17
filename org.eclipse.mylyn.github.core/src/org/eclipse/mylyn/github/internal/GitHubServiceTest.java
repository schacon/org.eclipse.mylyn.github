package org.eclipse.mylyn.github.internal;

import static org.junit.Assert.assertEquals;

import org.eclipse.mylyn.github.GitHubIssue;
import org.eclipse.mylyn.github.GitHubService;
import org.eclipse.mylyn.github.GitHubIssues;
import org.junit.Test;

public class GitHubServiceTest {
	
	// GitHub API key for user "eclipse-github-plugin" 
	String API_KEY = "8b35af675fcdca9d254ae7a6ad4d0be8";
	
	String TEST_USER = "eclipse-github-plugin";
	
	String TEST_PASS = "plugin";
	
	String TEST_PROJECT = "org.eclipse.mylyn.github.issues";
	
	String REPO_USER = "smilebase";

	@Test
	public void searchIssues() throws Exception {
		GitHubService service = new GitHubService();
		GitHubIssues issues = service.searchIssues( REPO_USER, TEST_PROJECT, "open", "test" );
		assertEquals( 3, issues.getIssues().length );
	}
	
	@Test
	public void openIssue() throws Exception {
		GitHubService service = new GitHubService();
		GitHubIssue issue = new GitHubIssue();
		issue.setUser( TEST_USER );
		issue.setBody("This is a test body");
		issue.setTitle("Issue Title");
		service.openIssue( REPO_USER, TEST_PROJECT, issue);
	}

	
	@Test
	public void addLabel() throws Exception {
		GitHubService service = new GitHubService();
		service.addLabel( REPO_USER, TEST_PROJECT, "lame", 1, API_KEY );
	}
	
	@Test
	public void removeLable() throws Exception {
		GitHubService service = new GitHubService();
		service.removeLabel( REPO_USER, TEST_PROJECT, "lame", 1, API_KEY);
	}

}
