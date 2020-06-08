# took here https://medium.com/@wkrzywiec/build-and-run-angular-application-in-a-docker-container-b65dbbc50be8

# read more about config here https://medium.com/@tiangolo/angular-in-docker-with-nginx-supporting-environments-built-with-multi-stage-docker-builds-bb9f1724e984
# https://medium.com/@avatsaev/create-efficient-angular-docker-images-with-multi-stage-builds-907e2be3008d

#FROM nginx:1.17.1-alpine
#COPY /dist/user-client-app /usr/share/nginx/html

### STAGE 1: Build ###
FROM node:12-alpine AS build_stage
WORKDIR /usr/src/app
COPY package.json ./
## Storing node modules on a separate layer will prevent unnecessary npm installs at each build
RUN npm install
COPY . .
## Build the angular app in production mode and store the artifacts in dist folder
RUN npm run build --prod --output-path=dist

### STAGE 2: Run ###
FROM nginx:1.17.1-alpine

## Copy our default nginx config
COPY nginx/default.conf /etc/nginx/conf.d/

## Remove default nginx website
RUN rm -rf /usr/share/nginx/html/*

## From ‘builder’ stage copy over the artifacts in dist folder to default nginx public folder
COPY --from=build_stage /usr/src/app/dist/user-client-app /usr/share/nginx/html

CMD ["nginx", "-g", "daemon off;"]
