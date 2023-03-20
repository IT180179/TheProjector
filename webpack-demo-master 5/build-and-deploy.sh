#!/usr/bin/env bash

pushd TheProjector
mvn clean
mvn package install || exit
popd

pushd FrontEnd
npm install
ng build --configuration production
popd

pushd k8s
./deploy.sh
popd

pushd FrontEnd
./deploy.sh
popd