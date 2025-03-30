FROM amd64/amazoncorretto:21

WORKDIR /app

COPY ./build/libs/pillmein-0.0.1-SNAPSHOT.jar /app/pillmein.jar

CMD ["java", "-Duser.timezone=Asia/Seoul", "-Dspring.profiles.active=dev", "-jar", "pillmein.jar"]