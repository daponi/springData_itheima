# springData_itheima
Learning springData through Itheima 


学习SpringData JPA的事例项目

视频：https://www.bilibili.com/video/BV1hE411s72B

课件：当前目录/file



该教程是2018.9出的，讲解的SpringData JPA是1.x版本算老版本建议尽量看些新版本的吧了，现在springboot下载的springdataJpa是2.5.5引入了JDK8的特性，所以部分语法得到了更新。

在P76中若是报错是新版的mysql不支持type=MyISAM的写法，而是用engine=MyISAM替代了，将xml文件里的org.hibernate.dialect.MySQLDialect改为org.hibernate.dialect.MySQL5Dialect就行，若是出现栈溢出的是因为两个对象之间的tostring疯狂互相调用，所以不能用lombok的@Data而要用@Setter和@Getter

springbootJPA 2.x版改掉了视频里的部分方法如findOne改为了findById,返回的是Optional、deleteOne改为了deleteById、Sort已经不能再实例化了，构造方法已经是私有的了，可以改用Sort.by获得Sort对象、PageRequest(int page, int size)改为用PageRequest.of(0,3)创建.....