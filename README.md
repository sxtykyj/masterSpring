# masterSpring
SSM + Zookeeper + RPC + Dubbo + MQ(RocketMQ, Kafka) 学习实践
## 目录
### 1. [论坛登录Demo (Spring + SpringMVC + Jetty + Dubbo)](https://github.com/sxtykyj/masterSpring/tree/master/code/chapter2)
#### 1）. Tool Version：
          JDK            ：1.8
          IDE            ：IntelliJ IDEA 2018.3.2 x64
          Encoding       ：UTF-8
          spring.version ：4.6.2
          mysql.version  ：8.0.18
#### 2）. Jetty登录：http://localhost:8080/bbs/index.html
#### 3）. 数据库代码
        mysql> DROP DATABASE IF EXISTS sampledb01;
        Query OK, 0 rows affected, 1 warning (0.01 sec)

        mysql> CREATE DATABASE sampledb01 DEFAULT CHARACTER SET utf8;
        Query OK, 1 row affected, 1 warning (0.01 sec)

        mysql> USE sampledb01;
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
#### 4）. Dubbo配置示例
    <!--dubbo配置 —— provider-->
        <!-- 发布dubbo服务 -->
        <!-- 提供方应用信息，用于计算依赖关系 -->
        <dubbo:application name="eshop-manager"/>
        <!-- 注册中心的地址 -->
        <!--这里因为我的zookeeper注册在本机，所以ip地址是这个，大家根据自己的不同修改-->
        <dubbo:registry protocol="zookeeper" address="127.0.0.1:2181"/>
        <!-- 用dubbo协议在20880端口暴露服务 -->
        <dubbo:protocol name="dubbo" port="20880"/>
        <!-- 声明需要暴露的服务接口 -->
        <dubbo:service interface="com.smart.service.UserService" ref="userServiceImpl"/>
    
    <!-- 引用dubbo服务 —— consumer -->
        <dubbo:application name="eshop-manager-web"/>
        <dubbo:registry protocol="zookeeper" address="127.0.0.1:2181"/>
        <dubbo:reference interface="com.smart.service.UserService" id="userService"/>

    <!--补充说明：-->
    <!--1. dubbo:registry 标签一些属性的说明：-->
    <!--         register是否向此注册中心注册服务，如果设为false，将只订阅，不注册。-->
    <!--         check注册中心不存在时，是否报错。-->
    <!--         subscribe是否向此注册中心订阅服务，如果设为false，将只注册，不订阅。-->
    <!--         timeout注册中心请求超时时间(毫秒)。-->
    <!--         address可以Zookeeper集群配置，地址可以多个以逗号隔开等。-->
    <!--2. dubbo:service标签的一些属性说明：-->
    <!--         interface服务接口的路径-->
    <!--         ref引用对应的实现类的Bean的ID-->
    <!--         registry向指定注册中心注册，在多个注册中心时使用，值为的id属性，多个注册中心ID用逗号分隔，如果不想将该服务注册到任何registry，可将值设为N/A-->
    <!--         register 默认true ，该协议的服务是否注册到注册中心。-->


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
#### 1）基本概念：
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
           注意：基于CGLib字节码动态代理的方案是通过扩展被增强类，动态创建其子类的方法进行AOP增强植入的。由于使用final、static、private修饰符的方法都不能被子类覆盖，相应地这些方法将无法实施AOP增强。
##### c. 注：建议使用Spring提供的代理工厂ProxyFactory，可在Spring中配置 [实例](https://github.com/sxtykyj/masterSpring/tree/master/code/chapter4/src/main/java/aop_study/AOP_For_ProxyFactory)    
 
#### 3）通知类型（增强类型）
##### a. @Before  在切点方法之前执行 [实例](https://github.com/sxtykyj/masterSpring/tree/master/code/chapter4/src/main/java/aop_study/advice/beforeAdvice)
##### b. @After  在切点方法之后执行 [实例](https://github.com/sxtykyj/masterSpring/tree/master/code/chapter4/src/main/java/aop_study/advice/afterAdvice)
##### c. @Around 环绕通知  [实例](https://github.com/sxtykyj/masterSpring/tree/master/code/chapter4/src/main/java/aop_study/advice/aroundAdvice)
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


### 5. Spring对DAO的支持
#### 1）数据源
         1. Apache的DBCP数据源
            1）实例
                <!--定义一个使用DBCP实现的数据源-->
                <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
                      destroy-method="close">
                    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
                    <property name="url" value="jdbc:mysql://localhost:3306/sampledb"/>
                    <property name="username" value="root"/>
                    <property name="password" value="940925"/>
                </bean>
            2）高并发优化：
               问题：若采用默认设置，testOnBorrow属性默认值为true，数据源在将连接交给DAO前会事先检测连接是否完好。
                     这样会避免“8小时问题”，但高并发条件会带来性能问题。
               优化：将testOnBorrow设置为false，而将testEhileIdle设置为true，再设置好timeBetweenEvictionRunsMillis的值。
               原理：DBCP将通过一个后台线程定时检测空闲连接，并清除发现的无用空闲连接。
                    只要将timeBetweenEvictionRunsMillis的值设置为小于8小时，那些被MySQL关闭的空闲连接就可以被清除，从而避免“8小时问题”。

         2. C3P0数据源              
            * 实例
                <!--定义一个使用DBCP实现的数据源-->
                <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource"
                      destroy-method="close">
                    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
                    <property name="url" value="jdbc:mysql://localhost:3306/sampledb"/>
                    <property name="username" value="root"/>
                    <property name="password" value="940925"/>
                </bean>
         

### 6. Spring事务
#### 1）数据库事务相关知识点巩固
        a. 数据库事务特性：ACID （一般采用重启日志来保证ACD；采用数据库锁机制来保证I）
           原子性（Atomicity）  ：原子性是指事务是一个不可分割的工作单位，事务中的操作要么都发生，要么都不发生。
           一致性（Consistency）：事务前后数据的完整性必须保持一致。
           隔离性（Isolation）  ：事务的隔离性是多个用户并发访问数据库时，数据库为每一个用户开启的事务，不能被其他事务的操作数据所干扰，多个并发事务之间要相互隔离。
           持久性（Durability） ：持久性是指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，接下来即使数据库发生故障也不应该对其有任何影响
        
        b. 数据并发问题
           1. 脏读（dirty read）：B事务修改了一个数据并未提交，A事物读取了这个数据，然后B事务回滚了，最后A又读取了一次，两次读取的数据不一致，称为脏读。
           2. 不可重复读（unrepeatable read）：A事务读取了一个数据后，B事务修改了这个数据，A事务又读取了这个数据，两次读取的数据也不一致，称为不可重复读。
           3. 幻读（phantom read）： A事务更新了某个字段（范围是整个数据表的）（以id=1为条件的），B事务又插入了一条新的记录，导致A事务认为自己没有完全更新过来，就像出现幻觉一样。
           4. 丢失更新：A和B两个事物同时修改同一个数据，A修改的提交在B提交之后，导致B好像没有修改，丢失修改。
 
        c. 数据并发问题的解决：隔离级别（读未提交Read uncommitted、读已提交Read committed、可重复读Repeatable read、串行化Serializable）
           1. 脏读，是因为读取其他事物未提交的数据，因此设置读已提交隔离级别可以解决这个问题。但不可解决不可重复读和幻读的问题。
           2. 不可重复读，是因为B事物的修改影响了A事务的读取数据，设置可重复读隔离级别，使得B事务修改数据和A事务读取数据互不影响，隔离开来，从而解决这个问题，同时解决脏读问题。
           3. 幻读问题，是因为A事务更新完数据后，B事务又插入了新的数据，设置串行化隔离级别（最高的事务隔离级别，同时代价也花费最高，性能很低，一般很少使用）可解决，并且这种隔离级别解决上面所有的问题。除了串行化，多版本并发控制（MVCC，Multiversion Concurrency Control）机制也可以解决该问题。
           4. 丢失修改一般使用加锁锁来解决，因此串行化可以解决，并且串行化可以解决上面出现的所有问题。
           5. 读未提交Read uncommitted -> 最低级别，以上情况均无法保证。
           6. MySQL的默认事务隔离级别 ：可重复读Repeatable read
              大多数数据库的默认级别是读已提交Read committed，比如Sql Server , Oracle

#### 2）数据库锁机制相关知识点巩固
        MySQL中的锁机制：MySQL中有着Lock和Latch的概念，在数据库中，这两者都可以被称为“锁”，但是两者有着截然不同的含义。
           * Latch：一般称为闩锁（轻量级的锁），因为其要求锁定的时间必须非常短。若持续的时间长，则应用的性能会非常差，在InnoDB引擎中，Latch又可以分为mutex（互斥量）和rwlock（读写锁）。其目的是用来保证并发线程操作临界资源的正确性，并且通常没有死锁检测的机制。
           * Lock的对象是事务，用来锁定的是数据库中的对象，如表、页、行。并且一般lock的对象仅在事务commit或rollback后进行释放（不同事务隔离级别释放的时间可能不同）。
##### a. Lock锁的类型    
        对数据的操作其实只有两种，也就是读和写，而数据库在实现锁时，也会对这两种操作使用不同的锁；InnoDB 实现了标准的行级锁，也就是共享锁（Shared Lock）和互斥锁（Exclusive Lock）。
           1）共享锁（读锁）：允许事务读一行数据。共享锁之间是兼容的。
           2）互斥锁（写锁）：允许事务删除或更新一行数据。互斥锁与其他任意锁都不兼容。
##### b. Lock锁的粒度
         
   ![Lock锁的粒度](https://github.com/sxtykyj/masterSpring/blob/master/pic_for_README/MySQL%E9%94%81%E7%B2%92%E5%BA%A6.png)  
             

###### *表锁：开销小，加锁快；不会出现死锁；锁定粒度大，发生锁冲突的概率最高，并发度最低。
          * 特点：MySQL各存储引擎中最大颗粒度的锁定机制。该锁定机制最大的特点是实现逻辑非常简单，带来的系统负面影响最小。所以获取锁和释放锁的速度很快。由于表级锁一次会将整个表锁定，所以可以很好的避免困扰我们的死锁问题。
          * 缺点：出现锁定资源争用的概率也会最高，致使并发度大打折扣。
          * 使用：更适合于以查询为主，只有少量按索引条件更新数据的应用，如Web应用
                  使用表级锁定的主要是MyISAM，MEMORY，CSV等一些非事务性存储引擎。 
          * 语法：
                          // 获取表锁
                          LOCK TABLES
                                tbl_name [[AS] alias] lock_type
                                [, tbl_name [[AS] alias] lock_type] ...
 
                          lock_type:
                                READ [LOCAL]
                               | [LOW_PRIORITY] WRITE
 
                          // 释放表锁
                          UNLOCK TABLES

###### *页锁：开销和加锁时间界于表锁和行锁之间；会出现死锁；锁定粒度界于表锁和行锁之间，并发度一般。
      页级锁定是MySQL中比较独特的一种锁定级别，在其他数据库管理软件中也并不是太常见。
         
          * 特点：页级锁定的锁定颗粒度介于行级锁定与表级锁之间，所以获取锁定所需要的资源开销，以及所能提供的并发处理能力也同样是介于上面二者之间。
          * 缺点：页级锁定和行级锁定一样，会发生死锁。          
          * 使用：使用页级锁定的主要是BerkeleyDB存储引擎。
          * 注：在数据库实现资源锁定的过程中，随着锁定资源颗粒度的减小，锁定相同数据量的数据所需要消耗的内存数量是越来越多的，实现算法也会越来越复杂。不过，随着锁定资源颗粒度的减小，应用程序的访问请求遇到锁等待的可能性也会随之降低，系统整体并发度也随之提升。

###### *行锁：开销大，加锁慢；会出现死锁；锁定粒度最小，发生锁冲突的概率最低，并发度也最高。
         * 特点：行级锁定最大的特点就是锁定对象的粒度很小，也是目前各大数据库管理软件所实现的锁定颗粒度最小的。由于锁定颗粒度很小，所以发生锁定资源争用的概率也最小，能够给予应用程序尽可能大的并发处理能力而提高一些需要高并发应用系统的整体性能。（在并发处理能力上面有较大的优势）
         * 缺点：由于锁定资源的颗粒度很小，所以每次获取锁和释放锁需要做的事情也更多，带来的消耗自然也就更大了。此外，行级锁定也最容易发生死锁。     
         * 使用：更适合于有大量按索引条件并发更新少量不同数据，同时又有并发查询的应用，如一些在线事务处理（OLTP）系统。
                使用行级锁定的主要是InnoDB存储引擎。
        

#### 3）JDBC对事务的支持

     Connection conn;
     try{
        // 获取数据连接
        conn = DriverManager.getConnection();
        // 关闭自动提交机制
        conn.setAutoCommit(false);
        // 设置事务隔离级别
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        Statement stmt = conn.createStatement();
        int rows = stmt.executeUpdate("INSERT INTO t_topic VALUES(1, 'tom') ");
        // 设置一个保存点
        Savepoint svpt = conn.setSavepoint("savePoint1");
        rows = stmt.executeUpdate("UPDATE t_user set topic_nums = topic_nums + 1 WHERE user_id = 1");
        // 提交事务
        conn.commit();
     } catch(Exception e){
        ...
        // 回滚事务至savePoint1
        conn.rollback(svpt);
     } finally{
        ...
     }
        
#### 4）ThreadLocal:使Spring的模板类访问底层数据时实现线程安全而无须线程同步 
      
        原理    ：为每一个线程维护一份独立的变量副本
        实现思路：在ThreadLocal里有一个Map，用于存储每一个线程的变量副本，键为线程对象，值为对应线程的变量副本
        接口方法：void set(T value)            设置当前线程的线程局部变量值
                 public T get()               返回当前线程所对应的线程局部变量
                 public void remove()              删除当前线程局部变量
                 protexted T initialValue()   返回该线程局部变量的初始值
        

### 7. [Spring + SpringMVC + Mybatis搭建项目](https://github.com/sxtykyj/masterSpring/tree/master/code/chapter18) 


### 8. [Zookeeper学习](https://github.com/sxtykyj/masterSpring/blob/master/code/zookeeperStudy/src/main/java/com/smart/zkDemo.java) 


### 9. [RPC学习:手写实现RPC框架Demo](https://github.com/sxtykyj/masterSpring/tree/master/code/RpcStudy) 
