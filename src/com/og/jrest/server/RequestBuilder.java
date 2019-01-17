package com.og.jrest.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.og.jrest.http.Header;
import com.og.jrest.http.Request;

public class RequestBuilder {

	private RequestBuilder() {
		// Static class
	}

	public static Request buildFromSocket(Socket socket) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		Request request = buildRequest(in);

		return request;
	}

	private static Request buildRequest(BufferedReader in) throws IOException {
		Request request = processRequestLine(in);
		if (request != null) {
			request = processHeaders(request, in);
			request = processBody(request, in);
		}

		return request;
	}

	private static Request processRequestLine(BufferedReader in) throws IOException {
		String line = in.readLine();
		Request request = new Request();
		// Some phantom requests just have "null"? idk why, just nullify the request if
		// so
		if (line == "null" || line == null)
			request = null;
		else
			request.parseRequestLine(line);

		return request;
	}

	private static Request processHeaders(Request request, BufferedReader in) throws IOException {
		String line = in.readLine();
		while (line != null && line.length() > 0) {
			request.addHeader(line);
			line = in.readLine();
		}

		return request;
	}

	private static Request processBody(Request request, BufferedReader in) throws IOException {
		Header contentLengthHeader = request.getHeader("Content-Length");
		if (contentLengthHeader != null) {
			int bodyLength = Integer.parseInt(contentLengthHeader.getValuesJoined());
			if (bodyLength > 0) {
				String body = readBody(in, bodyLength);
				request.setBody(body);
			}
		}

		return request;
	}

	private static String readBody(BufferedReader in, int bodyLength) throws IOException {
		char[] charArray = new char[bodyLength];
		in.read(charArray, 0, bodyLength);
		String body = new String(charArray);

		return body;
	}
}
