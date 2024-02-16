FROM openjdk:17-alpine
ARG DB_USERNAME
ARG DB_PASSWORD
ARG SPRING_PROFILE
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV SPRING_PROFILE=${SPRING_PROFILE}
COPY ./target/api.pedido.valhalla.kitchen-1.0.0.jar /usr/src/myapp/app.jar
WORKDIR /usr/src/myapp
ENTRYPOINT java -Dspring.profiles.active="$SPRING_PROFILE" -jar app.jar