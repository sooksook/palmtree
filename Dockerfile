FROM maven:3.6.1-jdk-8-slim

ENV APP_HOME=/home/deploy/palmtree
WORKDIR $APP_HOME

COPY . $APP_HOME
RUN mvn package

RUN chmod 777 ./target/palmtree-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "-Dapp.phase=dev", "-Dapp.shared=/home/deploy/palmtree", "./target/palmtree-0.0.1-SNAPSHOT.jar"]