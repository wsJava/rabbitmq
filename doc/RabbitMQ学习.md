## centos 7 安装和使用
### 安装
#### 下载相关软件包
- erlang
- socat
- rabbitmq
```
wget https://www.rabbitmq.com/releases/erlang/erlang-18.3-1.el7.centos.x86_64.rpm
wget http://repo.iotti.biz/CentOS/7/x86_64/socat-1.7.3.2-5.el7.lux.x86_64.rpm
wget https://www.rabbitmq.com/releases/rabbitmq-server/v3.6.5/rabbitmq-server-3.6.5-1.suse.noarch.rpm
```
#### rpm方式安装
```
rpm -ivh erlang-18.3-1.el7.centos.x86_64.rpm 
rpm -ivh socat-1.7.3.2-5.el7.lux.x86_64.rpm 
rpm -ivh rabbitmq-server-3.6.5-1.suse.noarch.rpm 
```
#### 更改配置文件
更改配置文件, 启动用户 
```
vim /usr/lib/rabbitmq/lib/rabbitmq_server-3.6.5/ebin/rabbit.app

{loopback_users, ["guest"]},
```
### 启动
#### 相关命令
```
启动
rabbitmq-server start &

停止
rabbitmqctl app_stop
```
#### 启动管控台插件
```
查看所有插件
rabbitmq-plugins list

启动管控台插件
rabbitmq-plugins enable rabbitmq_management
```
#### 开放端口供远程查看
```
firewall-cmd --zone=public --add-port=15672/tcp --permanent
```
#### 登陆
```
http://host:15672/
```

## RabbitMQ 核心概念
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190503132823637.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1d1X1NoYW5nMDAx,size_16,color_FFFFFF,t_70)
- **Server** 又称Broker，接受客户端的连接，实现AMQP实体服务
- **Connection**：连接，应用程序与Broker的网络连接
- **Channel**：网络信道，多路复用连接中的一条独立的双向数据流通道。几乎所有的操作都在Channel中进行，Channel是进行消息读写的通道。客户端可建立多个Channel，每个Channel代表一个会话任务。
- **消息**：服务器和应用程序之间传送的数据，RabbitMQ的消息分为Properties和Body组成。Properties可以对消息进行修饰，比如路由键routing-key、消息的优先级priority、延迟等高级特性；Body则是消息体内容。
- **Virtual host**：虚拟主机，用于进行逻辑隔离，最上层的消息路由。一个Virtual Host里面可以有若干个Exchange和Queue,同一个Virtual Host里面不能有相同名称的Exchange或Queue。
- **Exchange**：交换机，接收消息，根据路由键转发消息到绑定的队列。
	- fanout：把所有发送到该 exchange 的消息路由到所有与它绑定的队列中。
	- direct： 把消息路由到那些binding key 与 routing key 完全匹配的队列中。
	- topic： 与 direct 相似，但支持模糊匹配，点 “.” 表示字符串的分隔，“*” 匹配一个单词， “#” 匹配零个或多个单词。
	- headers：根据发送消息中的 headers 属性进行匹配。
- **Binding**：Exchange和Queue之间的虚拟连接，bingding中可以包含routing key。
- **Routing key**：一个路由规则，虚拟机可以用它来确定如何路由一个特定消息。
- **Queue**：也称Message Queue，消息队列，保存消息并将它们转发给消费者。

## springboot 使用
 该小项目分为三个模块
 - rabbitmq-model 提供消息传递的实体类, 及相关信息
 - rabbitmq-producer 生产者, 负责生产消息, 并提供失败重发功能,提高可用性
 - rabbitmq-consumer 消费者, 负责消费消息