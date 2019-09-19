##GS社区

##资料
[Spring 文档](https://spring.io/guides)  
[Spring Web](https://spring.io/guides/gs/serving-web-content/)  
[ES社区](https://elasticsearch.cn/)  
[BootStrap](https://v3.bootcss.com/)  
[GitHub OAuth Doc](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

##工具
[Git](https://git-scm.com/download)  
[Visual-Pardigm](https://www.visual-pardigm.com)

##脚本  
```sql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `token` char(36) DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

##语句
```bash
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```
