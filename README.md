# About

Конспект урока: https://www.youtube.com/watch?v=vz4HLGy2-lk&list=PLs_aLxm3VDLt24V_BLaM5MLbh59tOEXX3&index=2

## Запуск приложения

1. Через IDE
2. Maven Plugin
```shell
Maven -> selmag-parent -> manager-app -> Plugins -> spring-boot -> spring-boot:run
```
3. Service
4. CLI
```shell
% cd manager-app
% mvn:spring-boot:run
```
4. Manual

Собираем проект
```shell
mvn clean package
```
в директорию target/ положит исполняемый jar

- `manager-app-21.1.1-SNAPSHOT.jar` - весит 21Mb содержит все зависимости
- `manager-app-21.1.1-SNAPSHOT.jar.original` - весит 2.9K нужно указать зависимости в class path

Запуск
```shell
java -jar target/manager-app-21.1.1-SNAPSHOT.jar
```