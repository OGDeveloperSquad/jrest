package com.og.jrest.test.http;

class RequestTest {

//	@Test
//	void postHTMLBody() {
//		String testRequest = "POST /cgi-bin/process.cgi HTTP/1.1\n";
//        testRequest = testRequest + "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\n";
//        testRequest = testRequest + "Accept-Encoding: gzip, deflate\n";
//        testRequest = testRequest + "Connection: Keep-Alive\n\n";
//        testRequest = testRequest + "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + 
//        		"<string xmlns=\"http://clearforest.com/\">string</string>";
//
//        Request test = new Request(testRequest);
//        
//        //test request line
//        assertEquals( "POST", test.verb);
//        assertEquals("/cgi-bin/process.cgi", test.uri );
//        assertEquals( "HTTP/1.1", test.httpVersion);
//        //test headers
//        assertEquals( true, test.headers.containsKey("User-Agent"));
//        assertEquals( true, test.headers.containsKey("Accept-Encoding"));
//        assertEquals( true, test.headers.containsKey("Connection"));
//        assertEquals("Mozilla/4.0 (compatible; MSIE5.01; Windows NT)", test.headers.get("User-Agent")[0]);
//        assertEquals("gzip", test.headers.get("Accept-Encoding")[0]);
//        assertEquals("deflate", test.headers.get("Accept-Encoding")[1]);
//        assertEquals("Keep-Alive", test.headers.get("Connection")[0]);
//        //test body
//        assertEquals("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + 
//        		"<string xmlns=\"http://clearforest.com/\">string</string>", test.body );
//	}
//	
//	@Test
//	void postEmptyBody() {
//		String testRequest = "POST /cgi-bin/process.cgi HTTP/1.1\n";
//        testRequest = testRequest + "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\n";
//        testRequest = testRequest + "Accept-Encoding: gzip, deflate\n";
//        testRequest = testRequest + "Connection: Keep-Alive\n\n";
//        
//
//        Request test = new Request(testRequest);
//        
//        //test request line
//        assertEquals( "POST", test.verb);
//        assertEquals("/cgi-bin/process.cgi", test.uri );
//        assertEquals( "HTTP/1.1", test.httpVersion);
//        //test headers
//        assertEquals( true, test.headers.containsKey("User-Agent"));
//        assertEquals( true, test.headers.containsKey("Accept-Encoding"));
//        assertEquals( true, test.headers.containsKey("Connection"));
//        assertEquals("Mozilla/4.0 (compatible; MSIE5.01; Windows NT)", test.headers.get("User-Agent")[0]);
//        assertEquals("gzip", test.headers.get("Accept-Encoding")[0]);
//        assertEquals("deflate", test.headers.get("Accept-Encoding")[1]);
//        assertEquals("Keep-Alive", test.headers.get("Connection")[0]);
//        //test body
//        assertEquals("", test.body );
//	}

}
