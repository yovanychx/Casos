FROM mariadb

ENV MYSQL_ROOT_PASSWORD=
ENV MYSQL_DATABASE=casostpi

ADD /casostpi.sql /docker-entrypoint-initdb.d/casostpi.sql

EXPOSE 3306
ENTRYPOINT ["docker-entrypoint.sh"]
CMD ["mysqld"]
