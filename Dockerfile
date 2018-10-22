FROM tomcat:8-jre8

ADD target/Restful-Spring-MongoDB-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

RUN sh -c 'touch /usr/local/tomcat/webapps/Restful-Spring-MongoDB-0.0.1-SNAPSHOT.war'

ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/tomcat/webapps/Restful-Spring-MongoDB-0.0.1-SNAPSHOT.war"]

EXPOSE 5556