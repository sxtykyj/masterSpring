# masterSpring
Spring学习实践
## 目录
### 1. [论坛登录Demo (Spring + SpringMVC + Jetty)](https://github.com/sxtykyj/masterSpring/tree/master/code/chapter2)
#### 1）. Tool Version：
          JDK            ：1.8
          IDE            ：IntelliJ IDEA 2018.3.2 x64
          Encoding       ：UTF-8
          spring.version ：4.6.2
          mysql.version  ：8.0.18
#### 2）. Jetty登录：http://localhost:8080/bbs/index.html
#### 3）. 数据库代码
        mysql> DROP DATABASE IF EXISTS sampledb;
        Query OK, 0 rows affected, 1 warning (0.01 sec)

        mysql> CREATE DATABASE sampledb DEFAULT CHARACTER SET utf8;
        Query OK, 1 row affected, 1 warning (0.01 sec)

        mysql> USE sampledb;
        Database changed
        mysql>  CREATE TABLE t_user (
            -> user_id INT AUTO_INCREMENT PRIMARY KEY,
            ->  user_name VARCHAR(30),
            -> credits INT,
            -> password VARCHAR(32),
            -> last_visit datetime,
            -> last_ip VARCHAR(23)
            -> )ENGINE=InnoDB;
        Query OK, 0 rows affected (0.04 sec)

        mysql>  CREATE TABLE t_login_log (
            ->  login_log_id INT AUTO_INCREMENT PRIMARY KEY,
            -> user_id INT,
            ->  ip VARCHAR(23),
            ->  login_datetime datetime
            ->  )ENGINE=InnoDB;
        Query OK, 0 rows affected (0.03 sec)

        mysql> INSERT INTO t_user (user_name, password) VALUES('admin', '123456');
        Query OK, 1 row affected (0.01 sec)

        mysql> COMMIT;
        Query OK, 0 rows affected (0.00 sec)
### 2. [论坛登录Demo (SpringBoot + SpringMVC + Tomcat)](https://github.com/sxtykyj/masterSpring/tree/master/code/chapter3)
#### 1）. Tool Version：
          JDK                ：1.8
          IDE                ：IntelliJ IDEA 2018.3.2 x64
          Encoding           ：UTF-8
          springBoot.version ：1.3.3.RELEASE
          mysql.version      ：8.0.18
#### 2）. 论坛登录：http://localhost:8080/index.html
#### 3）. 报错整理：
          报错：org.apache.jasper.JasperException: Unable to compile class for JSP
          状态：已解决
          原因：SpringBoot项目会采用内嵌的Tomcat运行当前应用，而搭建项目时加入了Jetty依赖，导致该报错
