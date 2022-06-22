FROM tomcat:9.0.60-jre11-openjdk-slim-buster

ADD /build/libs/api.war /usr/local/tomcat/webapps/

CMD ["catalina.sh", "run"]

EXPOSE 8080