# 一、 虚拟机环境搭建

> 视频教程：https://www.bilibili.com/video/BV1h94y1k7Jf/

<iframe width="560" height="315"  src="//player.bilibili.com/player.html?aid=362210588&bvid=BV1h94y1k7Jf&cid=1230550765&p=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> 
搭建虚拟机视频教程
</iframe>
## 虚拟机下载

> 下载地址：https://customerconnect.vmware.com/en/downloads/details?downloadGroup=WKST-PLAYER-1750&productId=1377&rPId=111473

## Ubuntu镜像下载

> 下载地址：https://cn.ubuntu.com/download/desktop

## 虚拟机搭建与设置

### 新建虚拟机

<img src="https://typora-1308640872.cos.ap-beijing.myqcloud.com/img/image-20231124164353358.png" alt="image-20231124164353358" style="zoom:67%;" />



### 安装中文

> 参考：https://zhuanlan.zhihu.com/p/529892064



### 安装Docker

> ```shell
> // 安装
> sudo apt-get install docker.io
> // 测试
> sudo docker run hello-world
> ```



## 远程开发环境准备



1. ifconfig查看虚拟机IP地址

   ```shell
   sudo apt-get install net-tools
   ifconfig
   ```

2. ping命令

   ```shell
   ping 192.168.145.128
   ```

3. 安装SSH

   1. 安装SSH服务

      ```shell
      sudo apt-get install openssh-server
      ```

      

   2. 查看是否已开启服务

      ```shell
      ps -ef | grep ssh
      ```

      

4. 安装Java环境

   1. 安装Java8

      ```shell
      sudo apt-get install openjdk-8-jdk
      ```

      

   2. 检查是否安装成功

      ```shell
      java -version
      ```

5. 安装Maven

   1. 安装Maven

      ```shell
      sudo apt-get install maven
      ```

      

   2. 检查

      ```shell
      mvn -v
      ```

      

## 远程部署

> :link: 见第四部分



## 纯远程开发





# 二、 Docker容器技术

> :link: 视频教程：https://youtu.be/Ozb9mZg7MVM?si=eAQDFm5Ykes_EUEx
>
> :link: docker官网：https://www.docker.com/



# 三、 命令行操作Docker

1. 查看命令用法

   ```shell
   docker --help
   ```

   查看具体子命令的用法：

   ```shell
   docker run --help
   ```

   

2. 从远程仓库拉取现成的镜像

   ```shell
   docker pull [OPTIONS] NAME[:TAG|@DIGEST]
   ```

   示例：

   ```shell
   docker pull hello-world
   ```

   

3. 根据镜像创建容器实例

   ```sh
   docker create [OPTIONS] IMAGE [COMMAND] [ARG...]
   ```

   启动实例，得到容器状态：

   ```shell
   sudo docker create hello-world
   ```

   

4. 查看容器状态

   ```shell
   sudo docker ps -a
   ```

   

5. 启动容器

   ```shell
   docker start [OPTIONS] CONTAINER [CONTAINER...]
   ```

   启动示例：

   ```shell
   sudo docker start mystifying_shamir
   ```

   

6. 查看日志

   ```shell
   docker logs [OPTIONS] CONTAINER
   ```

   启动示例：

   ```shell
   sudo docker logs mystifying_shamir
   ```

   

7. 删除容器实例

   ```shell
   docker rm [OPTIONS] CONTAINER [CONTAINER...]
   ```

   删除示例：

   ```shell
   sudo docker rm mystifying_shamir
   ```

   

8. 删除镜像

   ```shell
   docker rmi --help
   ```

   示例，强制删除

   ```shell
   sudo docker rmi hello-world -f
   ```

   

9. 其他：build、push、run、exec等



# 四、Java操作Docker

## 前置准备

使用 Docker-Java:  :link: https://github.com/docker-java/docker-java

官方入门:  :link: https://github.com/docker-java/docker-java/blob/main/docs/getting_started.md

先引入依赖:

> 引入地址：:link: https://mvnrepository.com/artifact/com.github.docker-java/docker-java-transport-httpclient5/3.3.0

```xml
<!-- https://mvnrepository.com/artifact/com.github.docker-java/docker-java -->
<dependency>
    <groupId>com.github.docker-java</groupId>
    <artifactId>docker-java</artifactId>
    <version>3.3.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/com.github.docker-java/docker-java-transport-httpclient5 -->
<dependency>
    <groupId>com.github.docker-java</groupId>
    <artifactId>docker-java-transport-httpclient5</artifactId>
    <version>3.3.0</version>
</dependency>
```

- `DockerClientConfig`:  用于定义初始化 DockerClient 的配置(类比 MySQL 的连接、线程数配置)
- `DockerHttpClient`: 用于向 Docker 守护进程(操作 Docker 的接口) 发送请求的客户端，低层封装 (不推荐使用)，你要自己构建请求参数 (简单地理解成JDBC)
- `DockerClient`(推荐): 才是真正和 Docker 守护进程交互的、最方便的 SDK，高层封装，对DockerHttpClient 再进行了一层封装(理解成 MyBatis)，提供了现成的增删改查

## Linux Docker远程开发

### 连接远程虚拟机与文件映射

使用`IDEA Deployment`先上传代码到Linux，然后使用JetBrains远程开发完全连接Linux实时开发；

![image-20231127193548953](https://typora-1308640872.cos.ap-beijing.myqcloud.com/img/image-20231127193548953.png)

![image-20231127193257745](https://typora-1308640872.cos.ap-beijing.myqcloud.com/img/image-20231127193257745.png)

![image-20231127193358761](https://typora-1308640872.cos.ap-beijing.myqcloud.com/img/image-20231127193358761.png)

![image-20231127220824778](https://typora-1308640872.cos.ap-beijing.myqcloud.com/img/image-20231127220824778.png)

### 文件同步

![image-20231127221005996](https://typora-1308640872.cos.ap-beijing.myqcloud.com/img/image-20231127221005996.png)



### 远程开发Linux项目

> 依次点击 `file -> Remote Development`，新建SSH连接到Linux服务器端

<img src="https://typora-1308640872.cos.ap-beijing.myqcloud.com/img/image-20231212160924239.png" alt="image-20231212160924239" style="zoom:50%;" />

连接成功后，在本地打开项目，并等待远程下载资源：

<img src="https://typora-1308640872.cos.ap-beijing.myqcloud.com/img/image-20231212161008017.png" alt="image-20231212161008017" style="zoom: 50%;" />

## 常用操作

### 1. 拉取镜像

```java
// 获取默认的 Docker Client
DockerClient dockerClient = DockerClientBuilder.getInstance().build();
PingCmd pingCmd = dockerClient.pingCmd();
pingCmd.exec();
// 拉取镜像
String image = "nginx:latest";
PullImageCmd pullImageCmd = dockerClient.pullImageCmd(image);
PullImageResultCallback pullImageResultCallback = new PullImageResultCallback(){
    @Override
    public void onNext(PullResponseItem item) {
        System.out.println("下载镜像：" + item.getStatus());
        super.onNext(item);
    }
};
pullImageCmd
        .exec(pullImageResultCallback)
        .awaitCompletion();
System.out.println("下载完成");
```



### 2. 创建容器





### 3. 查看容器状态





### 4. 启动容器





### 5. 查看日志



### 6. 删除容器





### 7. 删除镜像





# 五、Docker实现代码沙箱

## 创建容器，上传编译文件



## 启动容器，执行代码



### 执行代码



### 获取程序执行时间



### 获取程序占用内存





# 六、Docker容器安全性

## 超时控制



## 内存资源



## 网络资源



## 权限控制



# 七、阶段成果





