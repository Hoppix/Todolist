package de.uhh.hop.JavaCI.integration.tools;

import de.uhh.hop.JavaCI.model.TodoEntry;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RestClient
{
	RestTemplate template;
	String host;

	public RestClient(String host)
	{
		template = new RestTemplate();
		this.host = host;
	}

	public <T> List<T> restGetList(String path, TodoEntry e)
	{
		List result = template.getForObject(getUrl(host, path, false), List.class);
		return (List<T>) result.stream().collect(Collectors.toList());
	}

	public TodoEntry restGetTodoEntry(String path, String... params)
	{

		return template.getForObject(getUrl(host, path, false) + "(" + params[0] + ")", TodoEntry.class);
	}

	public void restPostTodoEntry(String path, String... params)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("title", params[0]);
		map.add("text", params[1]);


		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);
		try
		{
			template.postForObject(getUrl(host, path, false), requestEntity, Boolean.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void restPostTodoEntrySetDone(String path, String... params)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("done", params[0]);


		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);
		try
		{
			template.postForObject(getUrl(host, path, false), requestEntity, Boolean.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void restDeleteTodoEntry(String path, String... params)
	{
		template.delete(getUrl(host, path, false) + "(" + params[0] + ")");
	}

	public void restDeleteTodoEntry(String path)
	{
		template.delete(path);
	}

	private String getUrl(String host, String path, boolean ssl)
	{
		String url = "";

		if (ssl)
		{
			url += "https://";
		}
		else
		{
			url += "http://";
		}

		url += host + path;
		return url;
	}
}
