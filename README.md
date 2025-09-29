# Arquitetura de Microserviços de Tarefas

Um sistema abrangente de gerenciamento de tarefas baseado em microserviços, construído com Spring Boot, apresentando descoberta de serviços, gerenciamento de configuração e comunicação entre serviços.

## Visão Geral da Arquitetura

Este projeto implementa uma arquitetura de microserviços seguindo os princípios de Domain-Driven Design (DDD) com padrões de arquitetura hexagonal:

```
┌─────────────────────────────────────────────────────────────────┐
│                    API Gateway / Service Mesh                    │
└─────────────────────────────────────────────────────────────────┘
                              │
┌─────────────────────────────────────────────────────────────────┐
│               Descoberta de Serviços (Eureka)                   │
│            Servidor de Configuração (Spring Cloud)               │
└─────────────────────────────────────────────────────────────────┘
                              │
    ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
    │   Service-Tasks │    │   Notfication   │    │   Service-Main  │
    │                 │    │                 │    │                 │
    │  ┌───────────┐  │    │  ┌───────────┐  │    │  ┌───────────┐  │
    │  │  API      │  │    │  │  API      │  │    │  │Eureka     │  │
    │  │Controller │  │    │  │Controller │  │    │  │Server     │  │
    │  └───────────┘  │    │  └───────────┘  │    │  └───────────┘  │
    │  ┌───────────┐  │    │                 │    │  ┌───────────┐  │
    │  │Service    │  │◄───┼──┤  Business  │ │    │  │Config     │  │
    │  │Layer      │  │    │  │  Logic     │ │    │  │Server     │  │
    │  └───────────┘  │    │  └────────────┘ │    │  └───────────┘  │
    │  ┌───────────┐  │    │                 │    │                 │
    │  │Repository │  │    │                 │    │                 │
    │  │  (H2)     │  │    │                 │    │                 │
    │  └───────────┘  │    │                 │    │                 │
    └─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 📋 Serviços

### 1. Service-Main (Porta 8761)
- **Eureka Server**: Registro de descoberta de serviços
- **Config Server**: Gerenciamento centralizado de configuração
- **Monitoramento de Saúde**: Verificação de saúde dos serviços

### 2. Service-Tasks (Porta 8081)
Microserviço de gerenciamento de tarefas com:
- **Operações CRUD**: Criar e gerenciar tarefas
- **Notificações Agendadas**: Lembretes automáticos de prazos
- **Arquitetura Hexagonal**: Separação clara de responsabilidades
- **Banco de Dados H2**: Persistência de dados em memória
- **Feign Client**: Integração com serviço de notificações

### 3. Service-Notification (Porta 8082)
Microserviço de notificações fornecendo:
- **API REST**: Endpoints HTTP para notificações
- **Arquitetura Desacoplada**: Escalonamento independente de serviços
- **Processamento de Mensagens**: Gerenciar solicitações de notificação

## 🚀 Funcionalidades

- **Descoberta de Serviços**: Registro e descoberta automáticos de serviços
- **Gerenciamento de Configuração**: Configuração centralizada com Spring Cloud Config
- **Comunicação entre Serviços**: Integração de API RESTful usando clientes Feign
- **Tarefas Agendadas**: Monitoramento automático de prazos de tarefas
- **Verificações de Saúde**: Monitoramento e verificação de saúde dos serviços
- **Suporte Docker**: Implantação conteinerizada com Docker Compose
- **Arquitetura Limpa**: DDD com padrões de arquitetura hexagonal

## 🛠️ Stack Tecnológica

- **Spring Boot 3.5.6**: Framework de aplicação
- **Spring Cloud 2025.0.0**: Infraestrutura de microserviços
- **Spring Cloud Netflix**: Descoberta de serviços Eureka
- **Spring Cloud Config**: Gerenciamento de configuração
- **Spring Data JPA**: Persistência de dados
- **H2 Database**: Banco de dados em memória
- **OpenFeign**: Cliente REST declarativo
- **Maven**: Automação de build
- **Docker**: Conteinerização
- **Java 21**: Linguagem de programação

## 📦 Estrutura do Projeto

```
tasksmicroservices/
├── service-main/           # Eureka + Config Server
├── service.tasks/          # Serviço de Gerenciamento de Tarefas
├── service.notification/   # Serviço de Notificações
├── config-server/         # Arquivos de Configuração
├── docker-compose.yaml     # Orquestração de Contêineres
└── README.md             # Este arquivo
```

## 🏃‍♂️ Início Rápido

### Pré-requisitos
- Docker e Docker Compose
- Java 21+
- Maven 3.6+

### Executando a Aplicação

1. **Clone o repositório**
   ```bash
   git clone <repository-url>
   cd tasksmicroservices
   ```

2. **Construa e execute com Docker Compose**
   ```bash
   docker-compose up --build
   ```

3. **Acesse os serviços**
   - Registry de Serviços: http://localhost:8761
   - Serviço de Tarefas: http://localhost:8081
   - Serviço de Notificações: http://localhost:8082

### Configuração Manual de Desenvolvimento

1. **Inicie o Service-Main**
   ```bash
   cd service-main
   mvn spring-boot:run
   ```

2. **Inicie o Service-Notification**
   ```bash
   cd service.notification
   mvn spring-boot:run
   ```

3. **Inicie o Service-Tasks**
   ```bash
   cd service.tasks
   mvn spring-boot:run
   ```

## 🔌 Documentação da API

### API do Service-Tasks

#### Criar Tarefa
```http
POST /tasks
Content-Type: application/json

{
    "title": "Completar documentação do projeto",
    "description": "Finalizar README e documentação da API",
    "deadline": "2024-12-31T23:59:59",
    "email": "usuario@exemplo.com"
}
```

#### Obter Todas as Tarefas
```http
GET /tasks
```

### API do Service-Notification

#### Enviar Notificação
```http
POST /notification
Content-Type: application/json

{
    "message": "Prazo da tarefa se aproximando",
    "email": "usuario@exemplo.com"
}
```

### Dashboard do Registry de Serviços
- **URL**: http://localhost:8761
- Visualize todos os serviços registrados e seu status

## 🔧 Configuração

### Configuração dos Serviços
Os arquivos de configuração estão localizados em `config-server/`:
- `service-tasks.properties`: Configuração do serviço de tarefas
- `service-notification.properties`: Configuração do serviço de notificações

### Variáveis de Ambiente
- `service.notification.url`: Endpoint do serviço de notificações
- `SPRING_PROFILES_ACTIVE`: Perfil Spring (dev, prod, etc.)

## 🔄 Comunicação entre Serviços

Os serviços se comunicam através de:

1. **Descoberta de Serviços**: Eureka para registro e busca de serviços
2. **APIs REST**: Comunicação HTTP síncrona
3. **Clientes Feign**: Implementações declarativas de cliente REST
4. **Servidor de Configuração**: Gerenciamento centralizado de configuração

## 📊 Monitoramento

### Verificações de Saúde
Cada serviço fornece endpoints de saúde:
- `GET /actuator/health`: Status de saúde do serviço
- `GET /actuator/info`: Informações do serviço

### Registry de Serviços
- **Dashboard Eureka**: http://localhost:8761
- Monitore instâncias de serviços e disponibilidade

## 🧪 Testes

### Executando Testes
```bash
# Executar testes para todos os serviços
mvn test

# Executar testes para serviço específico
cd service.tasks && mvn test
```

### Cobertura de Testes
- Testes unitários para camadas de serviço
- Testes de integração para endpoints da API
- Testes de componente para comunicação entre serviços

## 🚢 Implantação

### Implantação Docker
```bash
# Construir e implantar todos os serviços
docker-compose up -d

# Escalonar serviços
docker-compose up -d --scale service-tasks=2
```

### Considerações de Produção
- Configurações específicas do ambiente
- Persistência de banco de dados (substituir H2 por PostgreSQL/MySQL)
- Balanceamento de carga e integração de service mesh
- Agregação de monitoramento e logging
- Segurança e autenticação

## 🤝 Contribuindo

1. Faça um fork do repositório
2. Crie um branch de funcionalidade
3. Faça suas alterações
4. Adicione testes para novas funcionalidades
5. Certifique-se de que todos os testes passem
6. Envie um pull request

## 📄 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE para detalhes.

## 🙏 Agradecimentos

- Equipe Spring Boot pelo excelente framework
- Spring Cloud pela infraestrutura de microserviços
- Comunidade Docker pelas ferramentas de conteinerização

---

**Construído com ❤️ usando Arquitetura de Microserviços Spring Boot**