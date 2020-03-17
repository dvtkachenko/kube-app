# Microservices for Google Cloud Platform. 

It is used for Google Kubernetes cluster


# WEB Angular client

open cmd/shell

cd web/client-angular

npm run build --prod

docker build  -t angular-user-client-app .

docker run --rm --name angular-user-client-app-container -p 8888:80 angular-user-client-app

open localhost:8888 in browser

# Back end Spring boot service

open cmd/shell

cd user-service

mvn clean install

docker build  -t user-service .

docker run --rm --name user-service-container -p 8080:8080 user-service
