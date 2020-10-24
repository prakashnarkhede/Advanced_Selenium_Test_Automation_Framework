package reusableComponents;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author: Prakash Narkhede
 * @Youtube: https://www.youtube.com/automationtalks
 * @LinkedIn: https://www.linkedin.com/in/panarkhede89/
 */
public class JiraOperations {
	String jiraURL = PropertiesOperations.getPropertyValueByKey("jiraURL");
	String jiraUserName = PropertiesOperations.getPropertyValueByKey("jiraUserName");
	String jiraAccessKey = PropertiesOperations.getPropertyValueByKey("jiraSecretKey");


	//create Jira Issue as bug
	public String createJiraIssue(String ProjectName, String issueSummary, String issueDescription, String component, String priority, String label, String env, String assignee) throws ClientProtocolException, IOException, ParseException {
		
		String issueId = null; //to store issue / bug id.
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		String url = jiraURL+"/rest/api/3/issue";
		HttpPost postRequest = new HttpPost(url);
		postRequest.addHeader("content-type", "application/json");

	//	BASE64Encoder base=new BASE64Encoder();
		String encoding = Base64.getEncoder().encodeToString((jiraUserName+":"+jiraAccessKey).getBytes());
		//String encoding = base.encode((jiraUserName+":"+jiraAccessKey).getBytes());
		postRequest.setHeader("Authorization", "Basic " + encoding);

		StringEntity params = new StringEntity(createPayloadForCreateJiraIssue(ProjectName, issueSummary, issueDescription, component, priority, label, env, assignee));
		postRequest.setEntity(params);
		HttpResponse response = httpClient.execute(postRequest);

		//convert httpresponse to string
		String jsonString = EntityUtils.toString(response.getEntity());

		//convert sring to Json
		JSONParser parser = new JSONParser();  
		JSONObject json = (JSONObject) parser.parse(jsonString);

		//extract issuekey from Json
		issueId = (String) json.get("key");

		return issueId;

	}

	//Add attachment to already created bug / issue in JIRA
		public void addAttachmentToJiraIssue(String issueId, String filePath) throws ClientProtocolException, IOException 
		{
			String pathname= filePath; 
			File fileUpload = new File(pathname);

			HttpClient httpClient = HttpClientBuilder.create().build();
			String url = jiraURL+"/rest/api/3/issue/"+issueId+"/attachments";
			HttpPost postRequest = new HttpPost(url);

			//BASE64Encoder base=new BASE64Encoder();
			//String encoding = base.encode((jiraUserName+":"+jiraAccessKey).getBytes());
			String encoding = Base64.getEncoder().encodeToString((jiraUserName+":"+jiraAccessKey).getBytes());

			postRequest.setHeader("Authorization", "Basic " + encoding);
			postRequest.setHeader("X-Atlassian-Token","nocheck");

			MultipartEntityBuilder entity=MultipartEntityBuilder.create();
			entity.addPart("file", new FileBody(fileUpload));
			postRequest.setEntity( entity.build());
			HttpResponse response = httpClient.execute(postRequest);
			System.out.println(response.getStatusLine());

			if(response.getStatusLine().toString().contains("200 OK")){
				System.out.println("Attachment uploaded");
			} else{
				System.out.println("Attachment not uploaded");
			}
		}
	
	//creates payload for create issue post request
	private static String createPayloadForCreateJiraIssue(String ProjectName, String issueSummary, String issueDescription, String componentId, String priority, String label, String env, String assigneeId) {
		return "{\r\n" + 
				"    \"fields\": {\r\n" + 
				"       \"project\":\r\n" + 
				"       {\r\n" + 
				"          \"key\": \""+ProjectName+"\"\r\n" + 
				"       },\r\n" + 
				"       \"summary\": \""+issueSummary+"\",\r\n" + 
				"	   \"description\": {\r\n" + 
				"          \"type\": \"doc\",\r\n" + 
				"          \"version\": 1,\r\n" + 
				"          \"content\": [\r\n" + 
				"				{\r\n" + 
				"                    \"type\": \"paragraph\",\r\n" + 
				"                    \"content\": [\r\n" + 
				"								{\r\n" + 
				"                                    \"text\": \""+issueDescription+"\",\r\n" + 
				"                                    \"type\": \"text\"\r\n" + 
				"								}\r\n" + 
				"							   ]\r\n" + 
				"				}\r\n" + 
				"					]\r\n" + 
				"						}, \r\n" + 
				"		\"issuetype\": {\r\n" + 
				"          \"name\": \"Bug\"\r\n" + 
				"       },\r\n" + 
				"      \"components\": [\r\n" + 
				"      {\r\n" + 
				"        \"id\": \""+componentId+"\"\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"priority\": {\r\n" + 
				"      \"id\": \""+priority+"\"\r\n" + 
				"    },\r\n" + 
				"        \"labels\": [\r\n" + 
				"      \""+label+"\"\r\n" + 
				"    ],\r\n" + 
				"    	\"environment\": {\r\n" + 
				"      \"type\": \"doc\",\r\n" + 
				"      \"version\": 1,\r\n" + 
				"      \"content\": [\r\n" + 
				"        {\r\n" + 
				"          \"type\": \"paragraph\",\r\n" + 
				"          \"content\": [\r\n" + 
				"            {\r\n" + 
				"              \"text\": \""+env+"\",\r\n" + 
				"              \"type\": \"text\"\r\n" + 
				"            }\r\n" + 
				"          ]\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    },\r\n" + 
				"    	\"assignee\": {\r\n" + 
				"      \"id\": \""+assigneeId+"\"\r\n" + 
				"    }\r\n" + 
				"}\r\n" + 
				"}";
	}
	
	
}

