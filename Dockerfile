FROM openjdk:21

WORKDIR /app
# Копируйте исходный код в контейнер
COPY ./build/libs/*.jar app.jar

# Команда для запуска приложения (замените на свою)
CMD ["java", "-jar", "app.jar"]