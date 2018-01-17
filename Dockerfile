#using official tomcat image
FROM tomcat:9.0.2-jre8-alpine
LABEL maintainer "admin@test.com"

RUN ["rm", "-fr", "/usr/local/tomcat/webapps/ROOT"]
COPY ./target/app.war /usr/local/tomcat/webapps/springdemo.war

CMD ["catalina.sh", "run"]
