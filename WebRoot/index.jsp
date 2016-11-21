<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>案例与框架说明</title>
  </head>
  
  <body>
    	<s:a namespace="/" action="courseTypeAction_findAll">
    	Demo1:分页+条件查询
    	</s:a>
    	
    	<br/>
    	
    	<s:a namespace="/" action="classesAction_findAll">
    	Demo2:上传文件
    	</s:a>
    	
    	<h1>使用说明</h1>
    	
    	<h3>1、正常运行以上两个Demo，需要创建一个数据库并运行config目录下的crm.sql创建表和数据</h3>
    	
    	<h3>2、使用前需要修改dbinfo.properties中的数据库连接信息</h3>
    	
    	<h3>3、登录过滤器</h3>
    	<h5>①登录过滤器配置为对Action的login方法不拦截，如需要添加多个不拦截方法，请用逗号将多个方法隔开，如：login,register</h5>
    	<h5>②开发中如需暂时不使用此过滤器，可在loginStack中将LoginInterceptor的引用注释掉</h5>
    	
    	<h3>4、struts2</h3>
    	<h5>①每次增加一个Action类，请将与之对应的xml配置文件放到config/struts2文件夹中，并在config目录下的struts.xml中使用include导入</h5>
    	<h5>②struts.xml中配置了uiAction，可以统一访问WEB-INF/pages下的页面，详情请查看struts.xml文件，项目开发中应该根据自己的需求修改</h5>
    	
    	<h3>5、hibernate</h3>
    	<h5>①该框架不使用hibernate.cfg.xml配置文件，其中原本的所有配置都转移到了spring的配置文件中（SessionFactory）</h5>
    	<h5>②在applicationContext.xml中对.hbm.xml进行了通配符配置，开发时需要根据自己的包名修改mappingLocations属性的值</h5>
    	
    	<h3>6、spring</h3>
    	<h5>①该框架使用了AOP对所有的Service进行了事务管理，开发时需要根据自己项目的包名对aop:advisor的pointcut进行修改</h5>
    	<h5>②不使用注解：每次增加一个Dao和Service类时，请在config/spring目录下创建一个对应的applicationContext-xxx.xml文件用于配置不同模块的Dao与Service，并在config目录下的applicationContext.xml文件中使用import导入</h5>
    	<h5>③使用注解：项目如需使用注解方式完成组件注入、属性注入及事务管理，可以将applicationContext.xml中下方的注解配置的注释去掉，使用注解的时候，记得将4.2与4.3和各Dao、Service的配置注释掉，可能会冲突。</h5>
    	
  </body>
</html>
