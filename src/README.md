#READ ME
Spring boot project handling REST queries on a Ships H2 database.

##Requirements
For building and running the application you need:
* JDK 13.0.2
* Maven 3

##Running the application locally
Open the project (as maven project, from pom.xml) in an IDE of your choice ( E.g. Intellij, Eclipse..)
Execute the main method in the src/main/java/com/shipping/ships/ShipsApplication class.
Connect (via Postman or browser) to url: http://localhost:8080/ships/

##Troubleshooting
##### LF/CRLF warnings from GIT
Developed on a windows machine. If compiling/running on Mac/Linux, check git core.autocrlf settings are enabled to convert
windows format to Linux/Mac LF. 
See here:
https://stackoverflow.com/questions/5834014/lf-will-be-replaced-by-crlf-in-git-what-is-that-and-is-it-important
