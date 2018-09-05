package com.stackroute.foodieapp.helper;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class HeaderRequestInterceptor implements ClientHttpRequestInterceptor{

	private Map<String, String> headers;
	
	public HeaderRequestInterceptor(Map<String, String> headers)
	{
		this.headers = headers;
	}
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] bytes, ClientHttpRequestExecution execution)
			throws IOException {
		if(this.headers != null)
		{
			for(Map.Entry<String, String> entry : this.headers.entrySet())
			{
				request.getHeaders().add(entry.getKey(), entry.getValue());
			}
		}
		
		return execution.execute(request, bytes);
	}

}
