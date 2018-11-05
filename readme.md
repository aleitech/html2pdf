## 功能

* html转pdf

## 注意

* 从start.spring.io初始化的项目，必须要添加ognl的依赖，否则thymeleaf报错
* ognl的版本，3.2.8会报错，所以退回3.0.8
* 依赖whhtmltopdf的可执行程序

## 调用方法

### 传入json数据和html模板，返回html

* post方法访问http://localhost:8080/html
* body里面传{"jsonData":"xxxxjson.格式的数据","template":"xxxx.html模板"}

### 传入json数据和html模板，返回pdf

* post方法访问http://localhost:8080/pdf
* body里面传{"jsonData":"xxxxjson.格式的数据","template":"xxxx.html模板"}
