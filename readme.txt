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

