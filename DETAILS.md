# Details for Current Branch

###### default profile
|Property                   |Values                         |
|---------------------------|-------------------------------|
|`server.port`              |`8081`                         |
|`zipkin.base-url`          |`http://localhost:9411/`       |
|`eureka url`               |`http://localhost:8095/eureka/`|

###### cloud profile
|Property                   |Values                         |
|---------------------------|-------------------------------|
|`server.port`              |`8080`                         |
|`zipkin.base-url`          |`http://zipkin-server/`        |
|`eureka url`               |`http://eureka-server/eureka/` |


## API Exposed for image version : e1
|API                                |Description                        |
|-----------------------------------|-----------------------------------|
|**`/catalog/users/{userId}`**      |Get Ratings for movies watched by this user and based on ratings get Movie Info and return data|
