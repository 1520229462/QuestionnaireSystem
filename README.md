# QuestionnaireSystem
A questionnaire system that includes the functions of questionnaire creation, publication and data statistics

# 高校满意度调查问卷系统
该项目是一个综合管理系统，名为"高校满意度调查问卷"。该系统采用层次化的架构方式，包含了实体类层（Entity）、数据访问层（Mapper）、服务层（Service）、控制层（Controller）以及工具层（Utils）五个部分构成。

# 相关技术
在本项目的实践过程中，我们主要使用了以下技术：

Spring Boot工程结构：项目采用了标准的Maven项目结构，通过该结构可以方便地组织和管理项目的代码、配置和资源文件。

# 系统设计
"高校满意度调查问卷"系统是一个综合管理系统，包含多个模块，采用层次化的架构方式。系统由以下五个部分构成：

实体类层（Entity）：负责定义系统中的实体对象，如用户信息、项目信息等。

数据访问层（Mapper）：负责与数据库进行交互，实现数据的持久化和访问。

服务层（Service）：负责处理业务逻辑，包括用户管理、项目管理、问卷管理等功能。

控制层（Controller）：负责接收用户请求并调用相应的服务层方法进行处理，返回结果给前端。

工具层（Utils）：提供一些通用的工具类和方法，辅助其他模块的实现。

# 系统中的模块包括：

用户管理模块：负责管理系统的用户信息，包括用户注册、登录、权限管理等功能。

项目管理模块：用于管理各类项目的进展和相关信息，包括项目创建、编辑、删除等功能。

问卷管理模块：用于创建和管理问卷调查，包括问卷的创建、编辑、发布等功能。

问题选项管理模块：用于设置问题和选项的相关信息，包括问题的创建、编辑、删除等功能。

答题信息记录和答题明细记录管理模块：负责记录和存储答题信息，包括答题记录的保存、查询、统计等功能。

在开发过程中，我们已经完成了用户管理模块和项目管理模块的功能设计和开发。同时，我们还进行了问卷管理、问题选项管理、答题信息记录和答题明细记录管理的系统架构设计。我在项目中负责了创建问卷和数据分析-题目统计这两个功能模块的设计和开发。通过整体和局部功能的设计，我们确保了系统的稳定性和高效性。
