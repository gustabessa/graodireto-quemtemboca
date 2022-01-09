echo off

SET SERVIDOR=127.0.0.1
SET BANCO_DADOS=quemtemboca
SET DB_USUARIO=postgres
SET DB_SENHA=48500

cd scripts-update

call ..\liquibase-3.6.2\liquibase --driver=org.postgresql.Driver --changeLogFile=master.xml --url="jdbc:postgresql://%SERVIDOR%:5432/%BANCO_DADOS%" --username=%DB_USUARIO% --password=%DB_SENHA% migrate


PAUSE