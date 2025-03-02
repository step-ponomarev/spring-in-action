# Запустим брокер
docker run -it --rm --name artemis -p 61616:61616 -p 8161:8161 apache/activemq-artemis
