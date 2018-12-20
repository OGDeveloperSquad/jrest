package com.og.jrest.test.http;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.og.jrest.exceptions.HttpClientErrorException;
import com.og.jrest.http.Request;

class RequestTest {

	@Test
	void postHTMLBody() throws HttpClientErrorException {
		String testRequest = "POST /cgi-bin/process.cgi HTTP/1.1\n";
        testRequest = testRequest + "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\n";
        testRequest = testRequest + "Accept-Encoding: gzip, deflate\n";
        testRequest = testRequest + "Connection: Keep-Alive\n\n";
        testRequest = testRequest + "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + 
        		"<string xmlns=\"http://clearforest.com/\">string</string>";

        Request test = new Request(testRequest);
        
        //test request line
        assertEquals( "POST", test.getVerb());
        assertEquals("/cgi-bin/process.cgi", test.getURI() );
        assertEquals( "HTTP/1.1", test.getHttpVersion());
        //test headers
        assertEquals( true, test.getHeaders().containsKey("User-Agent"));
        assertEquals( true, test.getHeaders().containsKey("Accept-Encoding"));
        assertEquals( true, test.getHeaders().containsKey("Connection"));
        assertEquals("Mozilla/4.0 (compatible; MSIE5.01; Windows NT)", test.getHeaders().get("User-Agent")[0]);
        assertEquals("gzip", test.getHeaders().get("Accept-Encoding")[0]);
        assertEquals("deflate", test.getHeaders().get("Accept-Encoding")[1]);
        assertEquals("Keep-Alive", test.getHeaders().get("Connection")[0]);
        //test body
        assertEquals("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + 
        		"<string xmlns=\"http://clearforest.com/\">string</string>", test.getBody() );
	}
	
	@Test
	void postEmptyBody() throws HttpClientErrorException {
		String testRequest = "POST /cgi-bin/process.cgi HTTP/1.1\n";
        testRequest = testRequest + "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\n";
        testRequest = testRequest + "Accept-Encoding: gzip, deflate\n";
        testRequest = testRequest + "Connection: Keep-Alive\n\n";
        

        Request test = new Request(testRequest);
        
        //test request line
        assertEquals( "POST", test.getVerb());
        assertEquals("/cgi-bin/process.cgi", test.getURI() );
        assertEquals( "HTTP/1.1", test.getHttpVersion());
        //test headers
        assertEquals( true, test.getHeaders().containsKey("User-Agent"));
        assertEquals( true, test.getHeaders().containsKey("Accept-Encoding"));
        assertEquals( true, test.getHeaders().containsKey("Connection"));
        assertEquals("Mozilla/4.0 (compatible; MSIE5.01; Windows NT)", test.getHeaders().get("User-Agent")[0]);
        assertEquals("gzip", test.getHeaders().get("Accept-Encoding")[0]);
        assertEquals("deflate", test.getHeaders().get("Accept-Encoding")[1]);
        assertEquals("Keep-Alive", test.getHeaders().get("Connection")[0]);
        //test body
        assertEquals("", test.getBody() );
	}
	
	@Test
	void emptyStringTest()

}
