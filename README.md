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

Just started on this project, so there may be bugs right now. Will be adding review and test cases soon.
