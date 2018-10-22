FROM ubuntu:bionic

EXPOSE 8080

WORKDIR /assignment


RUN apt update && apt upgrade --assume-yes
RUN apt install openjdk-8-jdk --assume-yes
RUN apt install maven --assume-yes


# Copy the pom into the image, so it can be used with the next command
COPY pom.xml pom.xml

# install all dependencies, so that when the image is actually used, 
# there is no need to (re)download all the Spring dependencies

RUN mvn dependency:go-offline

CMD ["./run_webapp.sh"]
