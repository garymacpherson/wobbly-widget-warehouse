FROM tomcat:9.0.60-jre11-openjdk-slim-buster

ADD /build/libs/api-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

CMD ["catalina.sh", "run"]

EXPOSE 8080