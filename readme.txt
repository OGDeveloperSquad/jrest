Java package naming conventions are to use kind of a "reverse web site name".
For example, "com.domain_name.some_package"
Therefore I just adopted the package naming scheme of:

"com.og.jrest.your_package_name"

	Oracle docs here:
	https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html
	

**Do not use System.out or System.err anywhere in the program**
	We have a logging framework implemented, so always use one of these instead:
		Log.info, 
		Log.debug, 
		Log.error, 
		Log.exception





PUT /example/key/value HTTP/1.1
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


HTTP/1.1 200 OK

HTTP/1.1 404 NOT FOUND






























