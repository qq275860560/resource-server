#!/bin/bash
set -xev
source /etc/profile
#
echo "当前目录"
SHELL_FOLDER=$(cd "$(dirname "$0")";pwd)
echo ${SHELL_FOLDER}
cd ${SHELL_FOLDER}
#

#执行打包
cd ${SHELL_FOLDER}
timeout 600 mvn clean  deploy -DskipTests -Dgpg.skip

#代码分析
cd ${SHELL_FOLDER}
mvn sonar:sonar  || true

#(git add src pom.xml README.md  || true) && (git commit -m "commit" || true) && git push

mvn spring-boot:run  -Dspring-boot.run.jvmArguments="-Dlogging.level.root=ERROR -Dserver.port=8083 -Xms128m -Xmx1024m -Dsecurity.oauth2.resource.jwt.key-uri=http://localhost:8080/oauth/token_key -Dsecurity.oauth2.client.client-id=app1 -Dsecurity.oauth2.client.client-secret=123456"

mvn spring-boot:run  -Dspring-boot.run.jvmArguments="-Dlogging.level.root=ERROR -Dserver.port=8084 -Xms128m -Xmx1024m -Dsecurity.oauth2.resource.jwt.key-uri=http://localhost:8080/oauth/token_key -Dsecurity.oauth2.client.client-id=app2 -Dsecurity.oauth2.client.client-secret=123456"

