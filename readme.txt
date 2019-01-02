**********************************
**          IMPORTANT           **
**********************************
In order for the reflection necessary to inoke the controllers at runtime, you must do the following:
In eclipse, navigate to:
	Window
		Preferences
			Java
				Compiler
	
Then check the box at the bottom that says:
	"Store information about method parameters (usable via reflection)"
	
Done!	




**************************
** Example HTTP Message **
**************************

PUT api/example/key/value HTTP/1.1
Host: www.something.com
Header: value1,value2
Header-2: value3
Content-Type: application/zip
Cookie: cookie1=value&cook

{
	"KEY": "VALUE",
	"KEY2": {
		"NESTED": 1
	}
}




***********************************
**    Notes on response types    **
***********************************

	XML:
		- The API implementer will build the XML, but this is likely how they will do it
			- https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
		- Constructor will simply accept a Document object
			- Should overload to have constructor for string as well
	
	IMAGES:
		- This will be helpful:
			- BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
			- https://stackoverflow.com/questions/25086868/how-to-send-images-through-sockets-in-java
			- Piece of cake. All images will be read as BufferedImage, then will be converted to byte array 
			  during response, where we can set the image type to jpg, png, gif, etc.
		- Constructors will simply accept a BufferedImage object. 
			- The file type will be set by us when we send the response (in the Content-Type header)
		- I think an ImageResponse abstract base class may be helpful. It should extend Response and then
		  all the specific image response types will implement it. Like PNGResponse, JPGResponse, GIFResponse
	
	JSON:
		- Very simple. Just use "JSONParser" and "JSONObject"
		- There will be at least 2 Constructors for this one
			- One will accept a JSONObject, the other will accept a JSONArray
			- Maybe overload to have another constructor for a string
	
	Response Types:
		- HTMLResponse
		- TextResponse
		- XMLResponse
		- JSONResponse
		- PNGResponse
		- JPGResponse
		- GIFResponse
		- PDFResponse
		- This should be enough for now. We can expand later or as needed




























