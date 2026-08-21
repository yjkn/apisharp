# APISharp

APISharp 是一个面向开发者与测试人员的 API 测试平台，目标是围绕 API 注册、合规性检查、文档生成、测试用例管理、测试任务执行以及压力测试形成一套完整的工作流。

## 技术架构

项目规划采用以下技术栈：

- Spring Boot：提供后端应用基础能力与模块化服务承载。
- Maven：负责多模块工程构建，并支持 `apisharp-maven-plugin` 插件开发。
- MySQL：存储 API、测试用例、测试任务、执行结果等业务数据。
- MyBatis-Plus：简化数据访问层开发与 CRUD 能力。
- Redis：支持缓存、任务状态、分布式锁或临时数据管理。
- JMeter：作为底层测试执行器，支撑接口测试与压力测试。
- Elasticsearch：支撑 API 文档、测试记录或执行日志的检索与分析。

## 项目结构规划

APISharp 按职责拆分为以下核心模块：

1. **apisharp-core**：核心模块，负责 API 注册、合规性检查、文档生成等核心能力，为其他模块提供统一基础能力。
2. **apisharp-maven-plugin**：Maven 插件模块，允许开发者在开发阶段进行 API 合规性检查、生成文档，并将 API 注册到 APISharp 管理平台。
3. **apisharp-service**：管理服务模块，提供 API、测试用例、测试任务的管理与执行能力。
4. **apisharp-actuator**：执行器服务模块，底层集成 JMeter，用于接口测试和压力测试执行。

## 项目描述

APISharp 是一个全面的 API 测试平台，专为开发者和测试人员设计。开发者可通过 `apisharp-maven-plugin` 快速注册并导入自己编写的接口，平台将自动进行合规性检查并生成接口文档，从而提升研发协作效率，帮助前端开发人员提前了解接口输入、输出及调用约束。

在开发者导入 API 数据后，测试人员可以基于 API 编辑、编排和保存测试用例，并进一步组织测试任务，实现 API 全链路测试覆盖。为满足更复杂的测试需求，APISharp 还规划集成压力测试能力，通过 `apisharp-actuator` 模块底层调用 JMeter，对 API 和测试任务进行接口测试与压力测试。

## 当前交付内容

当前仓库已完成首版多模块代码骨架，本次提交补充了：

- Maven 父工程与四个子模块。
- `apisharp-core` 中的 API、测试用例、测试任务领域模型，以及合规性检查和 Markdown 文档生成能力。
- `apisharp-service` 中的 API 注册、合规性 Review、文档生成、测试用例和测试任务管理接口。
- `apisharp-actuator` 中的测试执行入口和 JMeter 测试计划生成能力。
- `apisharp-maven-plugin` 中的 `apisharp:review` 目标，用于在 Maven `verify` 阶段执行 API 合规性检查并生成文档。
- 初始需求 Review 和 TodoList，用于持续跟踪后续实现。

详细内容见：

- [项目 Review](docs/review.md)
- [TodoList](docs/todolist.md)
