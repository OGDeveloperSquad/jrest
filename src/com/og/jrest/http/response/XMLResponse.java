package com.og.jrest.http.response;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.og.jrest.http.Header;
import com.og.jrest.logging.Log;

public class XMLResponse extends Response {

	public XMLResponse(Document document) {
		super();
		String xml = xmlDocumentToString(document);
		this.initialize(xml);
	}

	public XMLResponse(String xml) {
		super();
		this.initialize(xml);
	}

	protected void initialize(Object body) {
		Header contentType = new Header(CONTENT_TYPE_KEY, "text/xml");
		this.addHeader(contentType);

		this.setBody(body);
	}

	/**
	 * Given a Document object containing XML, returns an unindented string of XML
	 * contained in the document.
	 * 
	 * @param document
	 *            Documnet object containing XML
	 * @return String representation of the XML contained in the document.
	 * 
	 * @requires the document format must be XML.
	 */
	private static String xmlDocumentToString(Document document) {
		String xml = null;
		try {
			StringWriter stringWriter = new StringWriter();
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

			transformer.transform(new DOMSource(document), new StreamResult(stringWriter));

			xml = stringWriter.toString();
		} catch (Exception ex) {
			Log.exception(ex);
		}

		return xml;
	}

	@Override
	public byte[] getBytes() {
		byte[] headers = this.getReponseLineAndHeaders().getBytes();
		byte[] result = headers;

		if (this.hasBody()) {
			byte[] body = ((String) this.getBody()).getBytes();
			result = this.concatenateBytes(headers, body);
		}

		return result;
	}

}
