FROM maven:3-jdk-8-alpine
VOLUME /tmp
COPY . .
RUN mvn -DskipTests clean install
RUN cp ./target/teachngo.jar ./teachngo.jar
RUN chmod a+x ./teachngo.jar
EXPOSE 8080
CMD ["java","-Dspring.profiles.active=prod","-jar","teachngo.jar"]