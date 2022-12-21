## flink实验
*(wsl安装kafka，本地安装mysql，安装DockerDesktop)*
###安装kafka，启动kafka

####下载：

进入[下载页面](https://kafka.apache.org/downloads) 选择需要的版本

#### 安装：
```shell
#下载
wget https://www.apache.org/dyn/closer.cgi?path=/kafka/3.1.0/kafka_2.12-3.1.0.tgz
#解压
tar -xzf kafka_2.13-3.1.0.tgz
```
#### 启动：(使用wsl启动之前需要将server.properties内指定监听的地址修改一下)
```shell
cd kafka_2.13-3.1.0
#启动zk
bin/zookeeper-server-start.sh config/zookeeper.properties
#启动kafka server
bin/kafka-server-start.sh config/server.properties
```
#### *修改server.properties文件内的监听端口：
```shell
#本来是
listeners=PLAINTEXT://:9092
#修改之后，增加了自己的ip地址
listeners=PLAINTEXT://192.168.100.121:9092
#没修改的时候启动server存在不容易发现的错误
```
### 安装mysql数据库
> 可以通过docker直接安装mysql。 [Mysql的Docker地址](https://registry.hub.docker.com/_/mysql)
#### 创建库
```sql
create database flink_demo;
```
#### 创建表
```sql
use flink_demo;
CREATE TABLE `student`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` bigint(20) NULL DEFAULT NULL,
  `createDate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
);
```

### 安装kafdrop作为kafka控制台
#### 启动kafdrop容器
```shell
docker pull obsidiandynamics/kafdrop:latest
docker run -d --rm -p 9000:9000 \
    -e KAFKA_BROKERCONNECT=192.168.100.121:9092 \
    -e JVM_OPTS="-Xms32M -Xmx64M" \
    -e SERVER_SERVLET_CONTEXTPATH="/" \
    obsidiandynamics/kafdrop:latest
#启动之后可以直接通过DockerDesktop查看容器日志
```
#### 访问容器
浏览器访问 http://192.168.100.121:9000/

注意：kafdrop当前代码是基于java11的，使用容器启动不需要考虑jdk版本问题，如果是需要拉取代码在本地启动或者jar包启动的话需要提前安装java11

