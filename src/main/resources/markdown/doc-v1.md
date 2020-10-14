# 后端文档

## 图示

### 用例图
![](http://qiniu-cdn.alexyan.cn/aspirin%E9%80%89%E8%AF%BE%E7%B3%BB%E7%BB%9F%E7%94%A8%E4%BE%8B%E5%9B%BE.png)

### 简化类图
![](http://qiniu-cdn.alexyan.cn/aspirin%E9%80%89%E8%AF%BE%E7%B3%BB%E7%BB%9F%E7%AE%80%E5%8C%96%E7%B1%BB%E5%9B%BE.png)

## 后端代码规范

-   Controller 统一返回 `JsonWrapper<T>` 类型的对象，Controller 层不应该出现和业务逻辑有关的代码，一般情况下 Controller 层每个函数只有一句调用 Service方法的语句
-   异常直接抛出,交给全局异常处理器 (UnifiedExceptionHandler) 就行, 不要原地捕获
-   新建的文件开头署名，尽量不要修改其他人写的文件，如果需要修改，限定为添加和对自己添加的代码做修改、删除(IDEA 自带的 git blame 可以看出哪些代码是自己写的)
-   service 接口的每个方法都要有注释，说明功能（不用给每个参数和返回值写注释）。serviceImpl 的 public 方法尽量写注释， 方法内部复杂的流程要写注释说明大概做了什么或者为什么这么做
-   对于自己不熟悉或不确定的地方要写单元测试, 单元测试要用 AssertEqual, AssertNull 等自动验证，而不能用 System.out 人肉验证
-   `push` 前要把所有的测试跑一遍， 千万不要 `push -f`
-   尽量避免数字和字符串硬编码，应该改成 final 常量或者枚举
-   如果 List 里面没有元素，那么应该返回空的 List 而不是 null
-   Entity，BO，DTO之间的转换用MapStruct
-   尽量用Restful风格命名访问路径

参考资料：

-   [一篇文章讲清楚VO，BO，PO，DO，DTO的区别](https://juejin.im/post/6844904046097072141)

其他：

- 只在dev分支开发

## 后端分工

### 用到的技术

- Spring, SpringBoot 基础框架
- Mybatis, MybatisPlus 对象关系映射(ORM)框架

- MapStruct 对象转换框架
- Shiro 安全框架
- 七牛云 文件云存储服务
- lombok 简化代码
- Swagger 生成api文档信息       Knife4J美化Swagger的文档UI

### 具体分工

#### 一 颜宇

设计数据表，项目基础配置、框架搭建，部署到云服务器，登录，验证码，修改密码，基于角色的权限管理，管理员信息查看与修改，根据学号/教师编号修改教师学生信息

#### 二 杨帅

图片、文件上传下载（七牛云） ，课程CRUD【在第一次展示前要完成】，给教师分配课程

#### 三 张阳

画详细类图，学生端的全部功能（选课，查看一周课表等） 设置选课开关，系统定时开放开关

#### 四 黄涛

手动填充各种数据库数据，教师端全部功能(不用选课，需要查看课表)，发公告修改公告
