# thoth-data-kafka-consumer

## Kafka 操作
- 创建Topic：`kafka-topics --zookeeper thoth195:2181,thoth211:2181,thoth215:2181/thoth-kafka --create --topic hello --replication-factor 2 --partitions 3`
- 查看所有可以使用的Topic，`kafka-topics --zookeeper thoth195:2181,thoth211:2181,thoth215:2181/thoth-kafka --list`
- 查：查看Topic为hello的信息，`kafka-topics --zookeeper thoth195:2181,thoth211:2181,thoth215:2181/thoth-kafka --topic hello --describe`

```
Topic:hello	PartitionCount:3	ReplicationFactor:3	Configs:
Topic: hello	Partition: 0	Leader: 81	Replicas: 81,82,80	Isr: 81,82,80
Topic: hello	Partition: 1	Leader: 82	Replicas: 82,80,81	Isr: 82,80,81
Topic: hello	Partition: 2	Leader: 80	Replicas: 80,81,82	Isr: 80,81,82
 说明：Leader：partition的leader，主要用的是brokerid
 说明：Replicas：partition的总副本brokerid的集合
 说明：Isr：partition可用的副本brokerid的集合
```
- Producer（生产者）创建：`kafka-console-producer --topic hello --broker-list thoth195:9092,thoth211:9092,thoth215:9092/thoth-kafka`
- 生产者：topic、broker--list
- Consumer（消费者）创建：`kafka-console-consumer --topic hello --zookeeper thoth195:2181,thoth211:2181,thoth215:2181/thoth-kafka --from-beginning`

- `kafka-console-consumer --topic biz-log --zookeeper thoth195:2181,thoth211:2181,thoth215:2181/thoth-kafka --from-beginning`

- `kafka-topics --zookeeper 10.12.28.195:2181,10.12.28.211:2181,10.12.28.215:2181/thoth-kafka --create --topic visitor_info --replication-factor 3 --partitions 3`
- `kafka-topics --zookeeper 10.12.28.195:2181,10.12.28.211:2181,10.12.28.215:2181/thoth-kafka --create --topic session --replication-factor 3 --partitions 3`
- `kafka-topics --zookeeper 10.12.28.195:2181,10.12.28.211:2181,10.12.28.215:2181/thoth-kafka --create --topic im_msg --replication-factor 3 --partitions 3`
- `kafka-topics --zookeeper 10.12.28.195:2181,10.12.28.211:2181,10.12.28.215:2181/thoth-kafka --create --topic biz-log --replication-factor 3 --partitions 3`

- `kafka-topics --zookeeper 10.132.103.71:2181,10.132.103.72:2181,10.132.103.73:2181/thoth-kafka --create --topic session --replication-factor 3 --partitions 3`
- `kafka-topics --zookeeper 10.132.103.71:2181,10.132.103.72:2181,10.132.103.73:2181/thoth-kafka --create --topic im_msg --replication-factor 3 --partitions 3`
- `kafka-topics --zookeeper 10.132.103.71:2181,10.132.103.72:2181,10.132.103.73:2181/thoth-kafka --create --topic visitor_info --replication-factor 3 --partitions 3`
- `kafka-topics --zookeeper 10.132.103.71:2181,10.132.103.72:2181,10.132.103.73:2181/thoth-kafka --create --topic biz-log --replication-factor 3 --partitions 3`