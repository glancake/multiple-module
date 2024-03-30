
# java 版本
FROM openjdk:17

RUN mkdir /app

WORKDIR /app

# 将jar包 copy 到镜像中
COPY gl-core/target/gl-core-0.0.1-SNAPSHOT.jar gl-core.jar

# 设置启动命令
ENTRYPOINT ["java", "-jar", "gl-core.jar","--spring.config.location=./application-pro.yml"]
