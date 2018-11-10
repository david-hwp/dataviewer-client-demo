#Dataviewer工程Java客户端Demo

在开始运行本项目之前，请确保已经完成如下步骤：

  1、安装dataviewer-client-*.jar到本地maven库，最新版本为HA_2.1，安装命令参考如下：
    
    mvn install:install-file -Dfile=./dataviewer-client-HA_2.1.jar -DgroupId=com.hansight.dataviewer -DartifactId=dataviewer-client -Dversion=HA_2.1 -Dpackaging=jar
    
  2、登录Master管理页面，跳转到『用户管理』页，获得用户对应的『客户端签名』
    
  3、client.conf 配置文件中找到client.remote部分，配置正确的master地址和端口，其中sign一项填写步骤2中获得的『客户端签名』


