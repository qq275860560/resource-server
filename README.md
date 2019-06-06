[TOC]
[资源服务器](https://github.com/qq275860560/resource-server)


 

## 运行
1.运行[认证服务器](https://github.com/qq275860560/authorization-server)

2.命令行切换到项目根目录下，执行

```
mvn spring-boot:run  -Dspring-boot.run.jvmArguments="-Dlogging.level.root=ERROR -Dserver.port=8083 -Xms128m -Xmx1024m -Dsecurity.oauth2.resource.jwt.key-uri=http://localhost:8080/oauth/token_key -Dsecurity.oauth2.client.client-id=app1 -Dsecurity.oauth2.client.client-secret=123456"
```

```
mvn spring-boot:run  -Dspring-boot.run.jvmArguments="-Dlogging.level.root=ERROR -Dserver.port=8084 -Xms128m -Xmx1024m -Dsecurity.oauth2.resource.jwt.key-uri=http://localhost:8080/oauth/token_key -Dsecurity.oauth2.client.client-id=app2 -Dsecurity.oauth2.client.client-secret=123456"

```

此时，本地会默认开启8083端口和8084端口

3.运行[网关服务器](https://github.com/qq275860560/gateway-server)



## 测试
### 获取token
/login
### 访问资源
/api
# 温馨提醒

* 此项目将会长期维护，增加或改进实用的功能
* 右上角点击star，给我继续前进的动力,谢谢