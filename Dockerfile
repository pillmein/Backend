FROM ubuntu:22.04

RUN apt-get update && \
    apt-get install -y ffmpeg openjdk-21-jdk && \
    apt-get clean

WORKDIR /app

COPY ./build/libs/pillmein-0.0.1-SNAPSHOT.jar /app/pillmein.jar

CMD ["java", "-Duser.timezone=Asia/Seoul", "-Dspring.profiles.active=dev", "-jar", "pillmein.jar"]