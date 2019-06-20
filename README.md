playersCrud: A sample case-study application for Sosyal Yazilim.  
* @author Oguz Erhan Eker
* @version 0.0.1-SNAPSHOT
* @since 2019-06-19  

By default, the application runs at the active profile "test".  
That is based on the h2 dynamic in-memory database with PostgreSql mode on.  
Actual development was completed at "dev" mode with a real PostgreSql database.  
Structure of this database can be viewed through the liquibase migration changelog XML file.  

Intentionally omitted building a custom DTO layer for data-display and call inputs in order to achieve simplicity.  
Usage of @JsonIgnore annotations was sufficient for the purpose.  

Please check src/test/java folder for unit tests.  

Sample Postman collections can be found under src/main/resources/postman  