#### 项目说明
该项目是一个典型的由springcloud管理的微服务项目，主要包括如下模块

|服务名 | 父级依赖 | 模块说明|
|----|----|----        |
|thoth|无|为微服务提供统一的pom管理，以及通用组件|  
|thoth-registry-server|无|注册中心,Eureka|  
|thoth-config-server| 无|微服务统一配置中心|  
|thoth-robot-ms|无|springcloud中的一个微服务,其中包含一个简单的增删改查demo|  
|thoth-docs|无|机器人微服务，内部目前只实现了图灵机器人的调用| 
|thoth-docs|无|相关文档，技术规范以及编码规范| 

* 修改日志  

|修改日志 | 修改人 | 修改日期|版本计划|
|----|----|----        |---|
|如下，v1.0|阿亮|20171202        |加入zuul功能|

  - [v1.0](https://github.com/liangliang1259/springcloud-thoth/blob/v1.0/thoth-docs/update-log/2017-12-02.md)

#### To Do List
* 1.thoth基础组件的管理，后面会对pom中jar的版本管理做细粒度的切分和管理
* 2.网关以及熔断器等项目的更新
* 3.配置中心以及注册中心的使用
* 4.微服务之间的RPC通信，会有FeignClient和RestTemplate以及Grpc的整合。
* 5.项目部署方案，安全，授权等通用模块的更新。
* 6、项目中使用到的技术，包括es，kafaka，mongo，redis等集群模式的搭建及使用文档。



### Most import
*  文档：  
这一点我想很多同学跟我一样深受其害，就像github一样，很多项目拿下来不能用，没有文档和环境的说明，然后需要花费大量的时间去看代码。  
希望看到的小伙伴多给我提issue，我会尽量回复并文档化和规范化。


#### End
相关的技术说明会写在如下三个地方：  
* [简书](http://www.jianshu.com/u/13c5fab7db82)
* [个人博客](http://sunliangliang.com/)
* [公众号：阿亮私语]  
 


![](http://ovheeg7ro.bkt.clouddn.com/aLiangcode.jpg)



    