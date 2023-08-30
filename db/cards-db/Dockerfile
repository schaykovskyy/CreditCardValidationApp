FROM mysql:8.1

ENV MYSQL_DATABASE=cards-db\
    MYSQL_ROOT_PASSWORD=pass

ADD schema.sql /docker-entrypoint-initdb.d

EXPOSE 3306
