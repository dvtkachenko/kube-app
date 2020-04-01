# Microservices for Google Cloud Platform. 

It is used for Google Kubernetes cluster


# WEB Angular client

open cmd/shell

cd web/client-angular

**to build app locally fulfill**

npm run build --prod

**to run it in a container fulfill**

docker build  -t angular-user-client-app .

docker run --rm --name angular-user-client-app-container -p 8888:80 angular-user-client-app

open localhost:8888 in browser

# Back end Spring boot service

open cmd/shell

cd user-service

mvn clean install

docker build  -t user-service .

docker run --rm --name user-service-container -p 8080:8080 user-service


# Useful links 

https://github.com/avatsaev/angular-contacts-app-example