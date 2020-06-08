# https://github.com/maciekrb/gcs-fuse-sample/blob/master/Dockerfile

# https://github.com/GoogleCloudPlatform/gcsfuse/issues/274

# https://code.stanford.edu/xuwang/docker-gcsfuse

# https://cloud.google.com/docs/authentication/production


# docker build -t bucket-service .
# docker build -f bucket-service/Dockerfile -t bucket-service .

# docker run -it --name bucket-service-container --entrypoint "/bin/sh" --rm bucket-service
# docker run --device /dev/fuse --cap-add SYS_ADMIN -it --name bucket-service-container --entrypoint "/bin/sh" --rm bucket-service

# gcsfuse dvt-kube-app-modules /mnt/bucket

# gcsfuse --key-file /storage/secret/dvt-kube-app-key.json dvt-kube-app-modules /mnt/bucket

#FROM debian:stretch
FROM debian:jessie
#ENV GCSFUSE_REPO gcsfuse-stretch
#ENV GCSFUSE_REPO=gcsfuse-`lsb_release -c -s`

#RUN apt-get update && apt-get install --yes --no-install-recommends \
#    ca-certificates \
#    curl \
#    gnupg \
#  && echo "deb http://packages.cloud.google.com/apt $GCSFUSE_REPO main" \
#    | tee /etc/apt/sources.list.d/gcsfuse.list \
#  && curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key add - \
#  && apt-get update \
#  && apt-get install --yes gcsfuse \
#  && apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

#RUN apt-get update && apt-get install -y --no-install-recommends apt-utils curl lsb-release gnupg kmod sudo rsync
RUN apt-get update && apt-get install -y curl lsb-release gnupg kmod sudo rsync
RUN export GCSFUSE_REPO=gcsfuse-`lsb_release -c -s` \
    && echo "deb http://packages.cloud.google.com/apt $GCSFUSE_REPO main" | tee /etc/apt/sources.list.d/gcsfuse.list \
    && curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key add - \
    && apt-get update  && apt-get install -y gcsfuse

ADD /secret/dvt-kube-app-a4064082d535.json /storage/secret/dvt-kube-app-key.json

CMD ["sleep", "3600"]