# 博客管理系统(后端)
合作开发博客管理系统的后端部分，使用SpringBoot、MyBatis-Plus、阿里云OSS编写了多个功能接口、采用SpringCloud、Nacos设计了多个微服务并远程调用，并用Swagger进行测试。
## 安装与运行
依赖版本管理详情见pom.xml文件    
运行时请变更application.yml文件中的数据库配置以及阿里云OSS信息，注意开启nacos   
question微服务中部分功能需远程调用article微服务接口，请注意运行顺序    
运行后测试端口http://localhost:8001/article/swagger-ui.html（不同微服务变更端口号以及/article即可）
