FROM nginx

COPY default.conf /etc/nginx/conf.d/
COPY proxy.conf /etc/nginx/
COPY env.sh /etc/nginx/
ADD  cliente.tar.gz /usr/share/nginx/html/

RUN chmod +x /etc/nginx/env.sh

CMD ["sh", "-c", "/etc/nginx/env.sh ; nginx -g 'daemon off;'"]
