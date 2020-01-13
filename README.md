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
          原因：SpringBoot项目会采用内嵌的Tomcat运行当前应用，而搭建项目时同时还加入了Jetty依赖，导致该报错
#### 4）. 知识点整理：
              1. 配置依赖相关
                 1）代码
                            <!--配置JSP相关依赖-->
                            <dependency>
                                <groupId>org.apache.tomcat.embed</groupId>
                                <artifactId>tomcat-embed-jasper</artifactId>
                                <scope>provided</scope>
                            </dependency>
                            
                 2）添加provided限制：
                        容器或JDK已提供范围，表示该依赖包已经由目标容器（如tomcat）和JDK提供，只在编译的classpath中加载和使用，
                    打包的时候不会包含在目标包中。最常见的是j2ee规范相关的servlet-api和jsp-api等jar包，一般由servlet容器提供，
                    无需在打包到war包中，如果不配置为provided，把这些包打包到工程war包中，在tomcat6以上版本会出现冲突无法正常运
                    行程序（版本不符的情况)。
                    
              2. SpringBoot配置检测功能: Actuator
                 1）application.properties监控配置：
                     # 数据库监控配置
                     management.health.db.enabled=true
                     management.health.defaults.enabled=true
                     # 应用磁盘空间检查配置
                     management.health.diskspace.enabled=true
                     management.health.diskspace.path=D:/IDEA/project/masterSpring/code
                     management.health.diskspace.threshold=0
                     # ElasticSearch服务健康检查配置
                     management.health.elasticsearch.enabled=true
                     management.health.elasticsearch.indices=index1,index2
                     management.health.elasticsearch.response-timeout=100
                     # Solr服务健康检查配置
                     management.health.solr.enabled=true
                     # JMS服务健康检查配置
                     management.health.jms.enabled=true
                     # Mail服务健康检查配置
                     management.health.mail.enabled=true
                     # MongoDB服务健康检查配置
                     management.health.mongo.enabled=true
                     # Rabbit MQ服务健康检查配置
                     management.health.rabbit.enabled=true
                     # Redis服务健康检查配置
                     management.health.redis.enabled=true
                     management.health.status.order=DOWN,OUT_OF_SERVICE,UNKNOWN,UP
                     
                 2）监控地址：http://localhost:8080/health
### 3. [Spring Ioc学习](https://github.com/sxtykyj/masterSpring/tree/master/code/chapter4/src/main/java/Ioc_study)
#### 知识点整理：
      * Java反射机制
             1. 类装载器将类装入JVM步骤：
               1）. 装载：查找和导入Class文件 -> 由ClassLoader以及其子类负责
               2）. 链接：执行校验、准备和解析步骤（其中解析步骤可选）
                        校验：检查载入Class文件数据的正确性
                        准备：给类的静态变量分配空间
                        解析：将符号引用转换成直接引用
               3）. 初始化：对类的静态变量、静态代码块执行初始化工作
          
             2. 主要反射类：
               1）. Constructor ：类的构造函数反射类。通过Class#getConstructors()方法可获取类的所有构造函数反射对象数组。
               2）. Method      ：类方法的反射类。通过Class#getDeclaredConstructor()方法可以获取类的所有方法反射类对象数组Method[]。
                                  主要方法：invoke(Object obj, Object[] args);
               3）. Field       ：类的成员变量的反射类。通过Class#getDeclaredFields()方法可以获取类的成员变量反射对象数组。
                                  主要方法：set(Object obj, Object value)
             3. 实例：
                 // 1. 通过类装载器获取Car类对象
                 ClassLoader loader = Thread.currentThread().getContextClassLoader();
                 Class clazz = loader.loadClass("Ioc_study.reflect.Car");

                 // 2. 获取类的默认构造器对象并通过它实例化Car
                 Constructor cons = clazz.getDeclaredConstructor((Class[]) null);
                 Car car = (Car) cons.newInstance();

                 // 3. 通过反射方法设置属性
                 Method setBrand = clazz.getMethod("setBrand", String.class);
                 setBrand.invoke(car, "红旗CA72");
                 Method setColor = clazz.getMethod("setColor", String.class);
                 setColor.invoke(car, "黑色");
                 Method setMaxSpeed = clazz.getMethod("setMaxSpeed", String.class);
                 setMaxSpeed.invoke(car, "200");
                 
      * Ioc的注入类型：构造函数注入，属性注入，接口注入(不建议)
             其中，spring支持构造函数注入和属性注入
### 4. [Spring AOP：使用动态代理技术在运行期间织入增强的代码 ](https://github.com/sxtykyj/masterSpring/tree/master/code/chapter4/src/main/java/aop_study)
#### 1）知识点整理：
          * 基本概念：
                AOP: Aspect Oriented Programing  面向切面（方面/剖面）编程
                Advice（通知）:把各组件中公共业务逻辑抽离出来作为一个独立 的组件
                Weave（织入） : 把抽离出来的组件（Advice）,使用到需要使用该逻辑 地方的过程。
                JoinPoint （连接点）： Advice 组件可以weave的特征点。
                PointCut（切入点）：用来明确Advice需要织入的连接点
                Aspect（切面）：Aspect=Advice + PointCut
#### 2）动态代理机制：对于Singleton的代理对象或者具有实例池的代理，适合采用CGLib动态代理技术，反之则适合采用JDK动态代理技术
       
##### a. [基于JDK的proxy](https://github.com/sxtykyj/masterSpring/tree/master/code/chapter4/src/main/java/aop_study/AOP_For_JDKProxy)
           局限：只能为接口创建代理实例
##### b. [基于CGLib的proxy](https://github.com/sxtykyj/masterSpring/tree/master/code/chapter4/src/main/java/aop_study/AOP_For_CGLib)
           优点 ：可以为类创建子类；性能优于基于JDK的proxy
           局限 ：不能代理目标类中的final或private方法；创建代理对象时所花费的时间多于基于JDK的proxy
##### c. 注：建议使用Spring提供的代理工厂ProxyFactory，可在Spring中配置 [实例](https://github.com/sxtykyj/masterSpring/tree/master/code/chapter4/src/main/java/aop_study/AOP_For_ProxyFactory)    
 
#### 3）通知类型（增强类型）
##### a. @Before  在切点方法之前执行 [实例](https://github.com/sxtykyj/masterSpring/tree/master/code/chapter4/src/main/java/aop_study/advice/beforeAdvice)
##### b. @After  在切点方法之后执行 [实例](https://github.com/sxtykyj/masterSpring/tree/master/code/chapter4/src/main/java/aop_study/advice/afterAdvice)
##### c. @Around环绕通知  [实例](https://github.com/sxtykyj/masterSpring/tree/master/code/chapter4/src/main/java/aop_study/advice/aroundAdvice)
##### d. @AfterReturning 切点方法返回后执行
##### e. @AfterThrowing 切点方法抛异常执行
               
            * 执行顺序：
                    @Around环绕通知
                    @Before通知执行
                    @Before通知执行结束
                    @Around环绕通知执行结束
                    @After后置通知执行了!
                    @AfterReturning

#### 4）切面
            1）切面设置：可以使用&&、||、!、三种运算符来组合切点表达式
                  execution表达式："execution(public * com.xhx.springboot.controller.*.*(..))"
                        *只能匹配一级路径 
                        ..可以匹配多级，可以是包路径，也可以匹配多个参数
                        + 只能放在类后面，表明本类及所有子类
            2）切面配置：可使用Spring提供的DefaultAdviceAutoProxyCreator自动代理创建器，它可以将容器中的所有Advisor自动织入目标Bean中
