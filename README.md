# Docker-Envoy [![Build Status](https://travis-ci.org/Paritosh-Anand/Docker-Envoy.svg?branch=master)](https://travis-ci.org/Paritosh-Anand/Docker-Envoy)
Utility to send docker events to kafka.

Plan is to create left side of the below design. Pushing required Docker events to Kafka will be useful to store/maintain state of services running in Docker ecosystem. Also we can consume events as messages from Kafka and execute desired custom tasks.
![Docker Events]
(https://d2mxuefqeaa7sj.cloudfront.net/s_5E4E5D4E7819918F4A7FFC5AD14BA5406AEE511BEF98A38F756ED666B912BEA8_1469560303675_docker+events.PNG)

sample messages that are being pushed to kafka 

```
{
  "nodeName" : "Paritoshs-MacBook-Air.local",
  "eventType" : "container",
  "eventTime" : 1471019668,
  "containerId" : "133d87308a588560da1847d7dfb23c4332713d3c30e0c2143921fa9f8581d938",
  "fromImage" : "busybox",
  "status" : "create",
  "oOMKilled" : false,
  "labels" : { },
  "hostExposedPorts" : { }
}
```
## Configuration
Configurations regarding Docker engine and kafka are defined in `docker.properties`. Make sure these configurations are correct
Docker related configurations:

* com.docker.envoy.dockerUri: Docker engine's endpoint.
* com.docker.envoy.tlsverify: set it to true if Docker engine is secured via ca certificates.
* com.docker.envoy.certpath: location to certs, required if tlsverify is set to true.

Kafka related configurations(these are standard configuration required to create KafkaProducer):

* metadata.broker.list
* serializer.class
* partitioner.class
* request.required.acks

## Documentation

Run `com.panand.docker.envoy.EventListener` class after configuring `docker.properties` with correct pointing to Docker host and Kafka broker list.

Topic names that will be created on Apache Kafka will be in sync with Docker terminology for e.g. container, network, images.

## Roadmap
Docker Envoy aims to make *customized processing* over Docker events feasible. Design enables to publish events to Apache Kafka, this happens instantly (i.e. NRT). This project can run as an agent on each of the Docker hosts and publish messages to a single Kafka cluster.

Since this project has the capability to process each event before-hand and publish more meaningful messages to Kafka. We can write Apache Storm topologies to consume these messages and do required processing something like [Docker-Serf](https://github.com/Paritosh-Anand/Docker-Serf).

## Community
Contributions, questions, and comments are all welcomed and encouraged! Do open issues/pull requests for any concerns.

Just started on this project, so there may be bugs right now. Will be adding test cases soon.
