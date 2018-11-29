web: application/target/universal/stage/bin/application -Dhttp.port=${PORT} -Dplay.evolutions.db.default.autoApply=true -Ddb.default.driver=org.postgresql.Driver -Ddb.default.jdbc=${JDBC_DATABASE_URL} -Dconfig.resource=prod.conf -Dconfig.file=/opt/conf/prod.conf
console: application/target/universal/stage/bin/application -main scala.tools.nsc.MainGenericRunner -usejavacp
