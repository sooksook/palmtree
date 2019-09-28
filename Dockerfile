FROM maven:3.6.1-jdk-8-slim

ENV APP_HOME=/home/deploy/palmtree
WORKDIR $APP_HOME

COPY pom.xml .
RUN mvn dependency:go-offline

COPY . $APP_HOME
RUN mvn package

RUN chmod 777 ./target/palmtree-0.0.1-SNAPSHOT.jar

EXPOSE 8000

CMD ["java", "-jar", "-Dapp.phase=dev", "-Dapp.shared=/home/deploy/palmtree", "./target/palmtree-0.0.1-SNAPSHOT.jar", "-Dserver.port=8090"]