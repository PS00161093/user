# user
###Swapper endpoint: http://localhost:8080/swagger-ui.html
###HAL for monitoring: spring-data-rest-hal-explorer
###To ignore a field in json response, use either @JsonIgnore at field level or use @JsonIgnoreProperties at class level
###Types of API versioning:
- Media type versioning
- Headers versioning
- URI versioning
- Request parameter versioning
###Factors to consider while choosing versioning type:
 - URI pollution
 - Misue of HTTP header
 - Caching
 - Can we execute the request from the browser
 - API documentation