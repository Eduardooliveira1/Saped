#!/bin/sh

set -eu

cd cliente/
npm install
npm run build

cd dist
tar zcvf cliente.tar.gz *
mv cliente.tar.gz ../../docker/nginx/
cd ../../docker/nginx
chmod -R a+x .
