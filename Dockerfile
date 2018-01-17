#using official tomcat image
FROM tomcat:9.0.2-jre8-alpine
LABEL maintainer "admin@test.com"

RUN ["rm", "-fr", "/usr/local/tomcat/webapps/ROOT"]
COPY ./target/springbootapp-1.0.war /usr/local/tomcat/webapps/

CMD ["catalina.sh", "run"]
