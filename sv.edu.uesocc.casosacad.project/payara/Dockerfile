FROM payara/server-full

COPY /wait-for-it.sh ./
ENV JDBC_URL=https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-5.0.8.zip  
ENV JDBCL_PKG=mysql-connector-java-5.0.8.zip
ADD psw.txt /psw.txt

RUN wget --quiet --no-check-certificate $JDBC_URL
RUN unzip -o $JDBCL_PKG && \
cp mysql-connector-java-5.0.8/mysql-connector-java-5.0.8-bin.jar /opt/payara41/glassfish/lib/

#RUN cp /mysql-connector-java-5.0.8-bin.jar /opt/payara41/glassfish/lib/

RUN /opt/payara41/bin/asadmin start-domain
RUN /opt/payara41/bin/asadmin restart-domain && \
/opt/payara41/bin/asadmin create-jdbc-connection-pool --datasourceclassname=com.mysql.jdbc.jdbc2.optional.MysqlDataSource --restype=javax.sql.DataSource --property='user=root:password=12345:url="jdbc:mysql://172.17.0.2:3306/casostpi":portNumber=3306:dataBaseName=casostpi:driverClass=com.mysql.jdbc.Driver:serverName=localhost' pool_casostpi --user admin --passwordfile /psw.txt && \
/opt/payara41/bin/asadmin create-jdbc-resource --connectionpoolid pool_casostpi jndi_casostpi --user admin --passwordfile /psw.txt

#ADD /CasosAcadUesTPIApp-ear-1.0-SNAPSHOT.ear /opt/payara41/glassfish/domains/domain1/autodeploy/CasosAcadUesTPIApp-ear-1.0-SNAPSHOT.ear





