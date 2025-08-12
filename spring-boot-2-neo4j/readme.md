docker pull neo4j:5.26.2

# 创建neo4j容器
docker run -it -d -p 7474:7474 -p 7687:7687 \
-v /home/neo4j/data:/data \
-v /home/neo4j/logs:/logs \
-v /home/neo4j/conf:/var/lib/neo4j/conf \
-v /home/neo4j/import:/var/lib/neo4j/import \
-v /home/neo4j/plugins:/var/lib/neo4j/plugins \
-e NEO4J_AUTH=neo4j/password \
--name neo4j neo4j:5.26.2


@Node：标注的实体为数据库对象实体，表示一个节点，其value值就是标签，如果不设置就是类名；
@Id：标注的属性是主键字段；
@GeneratedValue：主键字段，必须设置它的一个生成方式，如果是自己设置可以忽略；
@Property标注的属性为数据库属性字段，可以不写这个注解，@Node标注的实体中的属性默认为数据库属性字段，同样，其value值也是数据库属性字段，也可以不写，类似做了映射；
@Relationship：标注的属性为关系属性，值为关系的标签值，另一个值为关系的指向，默认从当前节点向外指向，类似关系型数据库的关系；
@RelationshipProperties：标注的类为关系实体，是对@Relationship类型的描述；
@TargetNode：标注的属性是关系中的目标节点；
