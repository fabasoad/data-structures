### Immutable Queue
#### Description
Feel free to add it as a library as it's configured as a jar library.
#### Run tests
```shell script
mvn test
```
### Google Analytics design
#### Diagram
![Diagram](https://raw.githubusercontent.com/fabasoad/data-structures/master/diagram.png)
#### Overview
When some actions happened like user click on any button or some another event that should be saved to Google Analytics (GA) - request is sent to GA API endpoint. As we have microservices architecture, first of all request reaches Service Mesh (SM) to understand what service should handle this request. SM redirects the request to specific service. Service works with the request based on business needs and sends the data as well as logs to Kafka cluster. Kafka saves the data to MongoDB storage cluster and logs to AWS S3 bucket. Also, logs are handled by ELK stack that is subscribed to Kafka cluster as well. Grafana is used for analytics. Metrics frontend works with GA cluster as well to get needed information for the end customers. Metrics Service works with Kafka cluster to retrieve needed data. Also, it works with Redis instance for caching purposes where we can store the intermediate data for faster response (so no need to always wait for the Kafka event to retrieve the data).
#### Covering the requirements
* handle large write volume: Billions write events per day.

_Kafka cluster can handle billions requests in real-time. Also, we have microservices infrastructure that allows us to split responsibility that gives us better productivity in comparing with monolith._

* handle large read/query volume: Millions merchants want to get insight about their business. Read/Query patterns are time-series related metrics.

_Again, Kafka + Microservices + MongoDB will help us to improve read/query performance (MongoDB is a good candidate for read/query purposes)._

* provide metrics to customers with at most one hour delay.

_Metrics service works with Redis. We can configure caching based on business needs as well as better customer experience. Can configure clear caching once per hour and even test the possibility to decrease the time-frame to have more up-to-date data._

* run with minimum downtime.

_All our microservices are running in orchestration tool (OT) such as Docker Swarm or Kubernetes. OT can be configured to automatically scale our services horizontally. For example have at least 3 instances of each service and if one of them will be down, another two instances will be handled all the requests while 3rd instance is restarting. Also, if 3 instances are not enough, OT can be configured to increase count of instances automatically (should be investigated and probably make some design restructuring in case such situations will be happened)._

* have the ability to reprocess historical data in case of bugs in the processing logic.

_Elasticsearch and AWS S3 storages might store all the data and information about requests/responses. We can investigate the problem based on this data._