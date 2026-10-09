FROM mysql:8.4

ENV MYSQL_ROOT_PASSWORD=rootpass
ENV MYSQL_DATABASE=kennelDB

COPY init/ /docker-entrypoint-initdb.d/
