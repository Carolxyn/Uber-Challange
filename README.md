# Serviço de Email

Um microsserviço Spring Boot para envio de emails usando Amazon Simple Email Service (SES). Este projeto foi desenvolvido como parte de um desafio de código da Uber e segue os princípios de arquitetura limpa.

## 🏗️ Arquitetura

O projeto segue os princípios da **Arquitetura Limpa** com clara separação de responsabilidades:

```
src/main/java/com/uber/email_service/
├── core/                    # Lógica de negócio e entidades
│   ├── EmailRequest.java    # DTO de requisição
│   ├── EmailSendUserCase.java # Interface do caso de uso
│   └── exceptions/          # Exceções customizadas
├── application/             # Serviços da aplicação
│   └── EmailSenderService.java
├── adapters/                # Adaptadores de interface
│   └── EmailSenderGateway.java
├── infra/                   # Camada de infraestrutura
│   └── ses/                 # Implementação do AWS SES
└── Controllers/             # Controladores web
    └── EmailSenderController.java
```

## 🚀 Funcionalidades

- API RESTful para envio de emails
- Integração com AWS SES
- Implementação de arquitetura limpa
- Tratamento de exceções
- Maven wrapper incluído
- Spring Boot DevTools para desenvolvimento

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven 3.6+ (ou use o Maven wrapper incluído)
- Conta AWS com SES configurado
- Endereço de email verificado no AWS SES

## ⚙️ Configuração

### Configuração do AWS SES

1. Crie uma conta AWS e configure o SES
2. Verifique seu endereço de email remetente no console do AWS SES
3. Configure suas credenciais AWS

### Configuração da Aplicação

Atualize `src/main/resources/application.properties`:

```properties
spring.application.name=email-service

aws.accessKeyId=SUA_CHAVE_DE_ACESSO_AWS
aws.secretKey=SUA_CHAVE_SECRETA_AWS
aws.region=us-east-1
```

**Importante**: Nunca faça commit de credenciais AWS reais no controle de versão. Use variáveis de ambiente ou roles do AWS IAM em produção.

### Variáveis de Ambiente (Recomendado)

```bash
export AWS_ACCESS_KEY_ID=sua_chave_de_acesso
export AWS_SECRET_ACCESS_KEY=sua_chave_secreta
export AWS_DEFAULT_REGION=us-east-1
```

## 🔧 Instalação e Execução

### Usando Maven Wrapper (Recomendado)

```bash
# Clone o repositório
git clone <url-do-repositorio>
cd email-service

# Execute a aplicação (Unix/Linux/Mac)
./mvnw spring-boot:run

# Execute a aplicação (Windows)
mvnw.cmd spring-boot:run
```

### Usando Maven Local

```bash
# Compile e execute
mvn spring-boot:run

# Ou construa e execute o JAR
mvn clean package
java -jar target/email-service-0.0.1-SNAPSHOT.jar
```

A aplicação será iniciada em `http://localhost:8080`

## 📡 Documentação da API

### Enviar Email

**Endpoint**: `POST /api/email`

**Corpo da Requisição**:
```json
{
  "to": "destinatario@exemplo.com",
  "subject": "Assunto do Teste",
  "body": "Conteúdo do corpo do email"
}
```

**Resposta**:
- **200 OK**: `"email send sucessfully"`
- **500 Internal Server Error**: `"Error while sending email"`

### Exemplo de Requisição cURL

```bash
curl -X POST http://localhost:8080/api/email \
  -H "Content-Type: application/json" \
  -d '{
    "to": "destinatario@exemplo.com",
    "subject": "Olá do Serviço de Email",
    "body": "Este é um email de teste enviado do serviço de email."
  }'
```

## 🧪 Testes

```bash
# Execute testes com Maven wrapper
./mvnw test

# Execute testes com Maven local
mvn test
```

## 📦 Dependências

- **Spring Boot 3.5.0** - Framework
- **Spring Boot Starter Web** - Suporte à API REST
- **Spring Boot DevTools** - Ferramentas de desenvolvimento
- **AWS Java SDK SES 1.12.472** - Integração com Amazon SES
- **Lombok** - Reduz código boilerplate
- **Spring Boot Starter Test** - Framework de testes

## 🏭 Considerações para Produção

### Segurança
- Use roles do AWS IAM em vez de credenciais fixas
- Implemente validação e sanitização de entrada
- Adicione limitação de taxa para prevenir abuso
- Use HTTPS em produção

### Configuração
- Use Spring Profiles para diferentes ambientes
- Externalize configurações usando variáveis de ambiente
- Considere usar AWS Parameter Store ou AWS Secrets Manager

### Monitoramento
- Adicione logging com formato estruturado
- Implemente verificações de saúde
- Adicione coleta de métricas
- Monitore uso e limites do AWS SES

### Exemplo de Configuração para Produção

```properties
# application-prod.properties
logging.level.com.uber.email_service=INFO
management.endpoints.web.exposure.include=health,info,metrics

# Use variáveis de ambiente
aws.region=${AWS_REGION:us-east-1}
```

## 🛠️ Desenvolvimento

### Estrutura do Código

O projeto segue estes padrões:
- **Casos de Uso**: Definem operações de negócio
- **Gateways**: Abstraem dependências externas
- **Serviços**: Implementam lógica de negócio
- **Controladores**: Lidam com requisições HTTP
- **Records**: Objetos de transferência de dados imutáveis

### Adicionando Novas Funcionalidades

1. Defina caso de uso no pacote `core`
2. Crie interface de gateway no pacote `adapters`
3. Implemente serviço no pacote `application`
4. Adicione implementação de infraestrutura no pacote `infra`
5. Crie endpoint no controlador no pacote `Controllers`

## 🐛 Solução de Problemas

### Problemas Comuns

1. **Credenciais AWS Não Encontradas**
   - Certifique-se de que as credenciais AWS estão configuradas corretamente
   - Verifique variáveis de ambiente ou arquivo de credenciais AWS

2. **Email Não Enviado**
   - Verifique se o email remetente está verificado no AWS SES
   - Verifique os limites de envio do AWS SES
   - Certifique-se de que o email destinatário é válido

3. **Porta Já em Uso**
   - Altere a porta em `application.properties`: `server.port=8081`

### Logs

Verifique os logs da aplicação para informações detalhadas de erro:
```bash
tail -f logs/spring.log
```

## 📄 Licença

Este projeto faz parte de um desafio de código da Uber.

## 🤝 Contribuindo

1. Faça fork do repositório
2. Crie uma branch para feature
3. Faça suas alterações
4. Adicione testes
5. Submeta um pull request

## 📧 Suporte

Para dúvidas ou problemas, por favor crie uma issue no repositório ou entre em contato com a equipe de desenvolvimento.

## 🔧 Melhorias Sugeridas

### Configurações Recomendadas

1. **Mover email remetente para configuração**:
```properties
# application.properties
email.sender.address=seu-email@exemplo.com
```

2. **Adicionar validação de entrada no controlador**:
```java
@Valid @RequestBody EmailRequest request
```

3. **Melhorar tratamento de erros**:
```java
@ControllerAdvice
public class EmailControllerAdvice {
    // Implementar handlers de exceção
}
```

4. **Adicionar logging estruturado**:
```java
private static final Logger logger = LoggerFactory.getLogger(SesEmailSender.class);
```

### Próximos Passos

- [ ] Implementar templates de email
- [ ] Adicionar suporte a anexos
- [ ] Implementar fila para processamento assíncrono
- [ ] Adicionar métricas e monitoramento
- [ ] Implementar retry automático para falhas
- [ ] Adicionar suporte a múltiplos provedores de email


