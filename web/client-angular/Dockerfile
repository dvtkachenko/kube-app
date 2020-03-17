# took here https://habr.com/ru/company/ruvds/blog/440656/

#FROM node:10 As build
## Папки
#RUN mkdir /app && mkdir /src
#WORKDIR /src
## Установка зависимостей
#COPY package*.json ./
#RUN npm install
## Для использования в продакшне
## RUN npm install --production
## Копирование файлов проекта и сборка проекта
#COPY . .
#RUN npm run build:production
## В результате получается образ, состоящий из одного слоя
#FROM node:alpine
## Копируем собранные файлы из папки build в папку app
#COPY --from=build ./src/build/* /app/
#ENTRYPOINT ["/app"]
#CMD ["--help"]


# took here https://medium.com/@wkrzywiec/build-and-run-angular-application-in-a-docker-container-b65dbbc50be8

#FROM nginx:1.17.1-alpine
#COPY /dist/user-client-app /usr/share/nginx/html

### STAGE 1: Build ###
FROM node:12-alpine AS build
WORKDIR /usr/src/app
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build

### STAGE 2: Run ###
FROM nginx:1.17.1-alpine
COPY --from=build /usr/src/app/dist/user-client-app /usr/share/nginx/html
