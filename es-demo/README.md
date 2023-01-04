## ES学习

###  安装es
使用docker安装：
```shell script
# 拉取镜像
docker pull elasticsearch
# 创建网络
docker network create somenetwork
# 运行容器（指定参数）
docker run -d --name elasticsearch --net somenetwork -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:tag

```
安装node版本的可视化控制台：
```shell script
# 下载安装包https://github.com/mobz/elasticsearch-head
# 解压
# 安装node环境
# 安装构建工具
npm install ‐g grunt‐cli
# 进入解压文件夹，执行构建和启动
# 构建（由于默认构建报错，增加了参数）
npm install phantomjs-prebuilt@2.1.16 --ignore-scripts
# 启动
grunt server
# 访问控制台
localhost:9100
```
### 基本使用
通过调用restful接口
### 代码使用

