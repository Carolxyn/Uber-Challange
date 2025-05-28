Uber Challenge - Serviço de E-mail
Este repositório contém uma implementação de um serviço de e-mail desenvolvido como parte de um desafio técnico da Uber. O objetivo principal deste serviço é fornecer uma abstração simples para o envio de e-mails, utilizando diferentes provedores de serviço de e-mail.

Descrição do Projeto
O Uber Challenge - Serviço de E-mail é um microserviço que permite o envio de mensagens de e-mail de forma programática. Ele foi construído com foco em simplicidade e flexibilidade, buscando abstrair a complexidade de múltiplos provedores de e-mail e facilitar o processo de envio de mensagens dentro de uma arquitetura de microserviços.

Características Principais:

Envio de E-mails: Permite que outras aplicações enviem e-mails por meio de uma API RESTful.
Integração com Provedores de E-mail: Projetado para se integrar com provedores de serviço de e-mail populares (por exemplo, Amazon SES, Mailgun, SendGrid – dependendo da sua implementação específica).
Construído com Spring Boot: Utiliza o framework Spring Boot para desenvolvimento rápido e fácil implantação de aplicações Java.
Tecnologias Utilizadas
Java: Linguagem de programação principal.
Spring Boot: Framework para construção de aplicações Java robustas e independentes.
Maven: Ferramenta para gerenciamento de dependências e construção do projeto.
AWS SDK para Java: Para interação com serviços AWS (como Amazon SES, se utilizado).
Como Configurar e Executar o Projeto
Para colocar o serviço de e-mail em funcionamento, siga os passos abaixo:

Pré-requisitos
Certifique-se de ter os seguintes softwares instalados em sua máquina:

Java Development Kit (JDK) 17 ou superior
Apache Maven 3.x ou superior
1. Obter as Chaves de API
Este serviço se conecta a provedores de e-mail externos (por exemplo, Amazon SES). Você precisará obter as credenciais (chaves de API, segredos, etc.) do provedor que pretende usar.

Amazon SES (exemplo): Para usar o Amazon SES, você precisará configurar suas credenciais AWS (Access Key ID e Secret Access Key) e a região em que o SES está configurado. Além disso, é crucial verificar o(s) endereço(s) de e-mail do remetente no SES para que você possa enviar e-mails a partir deles.
2. Configurar o Projeto
Clone o Repositório:

Bash

git clone https://github.com/Carolxyn/Uber-Challange.git
cd Uber-Challange/email-service # Navegue até a pasta do serviço de e-mail
Configurar as Credenciais:

Abra o arquivo src/main/resources/application.properties (ou application.yml, dependendo da sua configuração).

Localize as entradas para as chaves de API e configure-as com suas credenciais obtidas dos provedores de e-mail. Por exemplo, para AWS SES, você pode adicionar ou modificar as seguintes propriedades (substitua pelos seus valores reais e pela região correta):

Properties

# Exemplo para AWS SES
aws.accessKeyId=SUA_ACCESS_KEY_ID_AWS
aws.secretKey=SUA_SECRET_KEY_AWS
aws.region=sa-east-1 # Exemplo: sa-east-1 para São Paulo. Escolha a região onde seu SES está configurado.
Importante: Se você estiver executando o serviço fora de uma instância EC2, a região deve ser explicitamente definida no código ou nas propriedades. O erro Unable to find a region via the region provider chain. Must provide an explicit region in the builder or setup environment to supply a region. indica a necessidade dessa configuração.

3. Compilar e Executar
Compile o Projeto:

Bash

mvn clean install
Execute a Aplicação Spring Boot:

Bash

mvn spring-boot:run
O serviço será iniciado, geralmente na porta 8080 por padrão. Você verá logs no console indicando que a aplicação está pronta para receber requisições.

Como Usar a API de Envio de E-mail
Com o serviço em execução, você pode enviar e-mails fazendo uma requisição POST para o endpoint /email/send.

Endpoint
POST /email/send

Exemplo de Requisição (JSON Payload)
Envie uma requisição HTTP POST para o endpoint com um corpo JSON contendo os detalhes do e-mail:

JSON

{
    "from": "remetenteRegistrado@example.com",
    "to": "destinatario@example.com",
    "subject": "Olá do Serviço de E-mail Uber!",
    "body": "Este é o corpo do e-mail enviado através do desafio da Uber."
}
Exemplo de Requisição (usando curl)
Você pode testar o envio de e-mails usando o curl no seu terminal:

Bash

curl -X POST \
  http://localhost:8080/email/send \
  -H 'Content-Type: application/json' \
  -d '{
    "from": "remetenteRegistrado@example.com",
    "to": "destinatario@example.com",
    "subject": "Assunto do E-mail",
    "body": "Conteúdo do corpo do e-mail."
  }'
Atenção: Alguns provedores de e-mail, como o Amazon SES, exigem que o endereço de e-mail do remetente ("from") seja previamente verificado na plataforma deles para que o envio seja bem-sucedido.

Observações
Este projeto foi desenvolvido como um desafio de codificação e pode não ser adequado para uso em ambientes de produção sem considerações adicionais de segurança, escalabilidade e resiliência.
Para o cenário de falha de conexão (Network is unreachable) ou falha na detecção de região, certifique-se de que sua máquina tem acesso à internet e que as configurações de proxy/firewall não estão bloqueando a comunicação com os endpoints da AWS ou outros provedores de e-mail. A definição explícita da região no código ou nas propriedades é crucial.
