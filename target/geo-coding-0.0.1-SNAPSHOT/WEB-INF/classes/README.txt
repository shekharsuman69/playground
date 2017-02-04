Geocode Project Version 1.0 02/04/2017
@Author: Shekhar Suman

General Project Overview
------------------------
1. The objective of the project is to provide REST endpoints to the user to:
   - Perform reverse Geocode lookup by providing latitude and longitude information using different maps.
   - Perform a lookup of last 10 successful reverse geocode lookups.
2. The different types of map sources supported are - Google, MapQuest and Microsoft Bing.
3. REST API's developed using Jersey JAX-RS implementation.
4. It is a Maven project and target is war file.
5. Project tested against JBoss instance. 
6. It can work on Tomcat or any other dockerized container too.

API Details
-----------
1. Reverse Geocode lookup API
   URL: http://localhost:7180/maps/geocode/v1/reverse
   Request Type: POST
   Mandatory Inputs: latitude and longitude. If no source selected, it defaults to 'Google'.
   POST Parameters:
  	 				{"latitude":93.969601,
					 "longitude":-94.100033,
					 "source":"Google"}
					 
2. Metadata lookup (Last 10 successful lookups) API
   URL: http://localhost:7180/maps/geocode/v1/meta
   Request Type: GET
   GET Parameters: None
   
Test Steps
----------
1. Import the project into Eclipse or any IDE.
2. Build the project.
3. Copy the war file into /standalone/ directory of JBoss.
4. Go the /bin directory and start ./standalone.sh
5. The war file should be exploded/launched.
6. Once up the REST endpoints are available to use.

More Information
----------------
1. The metadata lookup is implemented using ArrayBlockingQueue which is backed by an array.
2. ArrayBlockingQueue is a bounded collection(fixed size) and thread safe.
3. The oldest element is pushed out to fit the new element if the size is equal to 10.

Contact
-------
shekhar1.suman@gmail.com