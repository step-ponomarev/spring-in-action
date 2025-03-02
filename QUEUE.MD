# Запустим брокер
1) docker run --rm confluentinc/cp-kafka kafka-storage random-uuid 
2) 
docker run --rm --name kafka \
-p 9092:9092 -p 9093:9093 \
-e CLUSTER_ID="jGnfiIFJTYiwf9bL6DGVeg" \
-e KAFKA_NODE_ID=1 \
-e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092,CONTROLLER://localhost:9093 \
-e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 \
-e KAFKA_PROCESS_ROLES=broker,controller \
-e KAFKA_CONTROLLER_QUORUM_VOTERS=1@localhost:9093 \
-e KAFKA_CONTROLLER_LISTENER_NAMES=CONTROLLER \
-e KAFKA_LOG_DIRS=/tmp/kraft-combined-logs \
-e KAFKA_AUTO_CREATE_TOPICS_ENABLE=true \
-e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
confluentinc/cp-kafka
