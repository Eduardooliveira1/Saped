#SCRIPT RODA NO JENKINS
#GERAÇÃO DO .TAR.GZ
cd cliente
npm install
npm run build
mv dist ../docker/nginx/cliente

cd ../docker/nginx
tar czfv cliente.tar.gz cliente
chmod -R a+x .

#PRÉ-ENVIO
CONTAINER_DOCKER=$(docker ps -a | grep -E '*mme-cliente-tst*' | awk -e '{print $1}')
IMAGEM_DOCKER=$(docker images | grep -E '*mme-saped-nginx.*latest-tst*' | awk -e '{print $3}')
docker stop $CONTAINER_DOCKER
docker rm $CONTAINER_DOCKER
docker rmi -f $IMAGEM_DOCKER

#
#
#
#DOCKER BUILD AND PUBLISH
#
#
#

#PÓS-ENVIO
docker run --name mme-cliente-tst -p 48980:80 -d \
-e "TZ=America/Sao_Paulo" \
-e ENDERECO_API="172.20.0.41:48880" \
honiara.basis.com.br/jenkins/mme-saped-nginx:latest-tst
