# Clínica Veterinária

## Status do Projeto

**Em desenvolvimento**

Projeto desenvolvido como parte do **Projeto Integrador do curso Técnico em Desenvolvimento de Sistemas – SENAC**.

## Tecnologias Aplicadas

- Java
- Java Swing
- JDBC
- MySQL
- MySQL Workbench
- Apache NetBeans
- Git
- GitHub

## Time de Desenvolvedores

- Ricardo — Desenvolvimento e implementação do sistema

## Objetivo do Software

O sistema **Clínica Veterinária** tem como objetivo auxiliar na administração de uma clínica veterinária, permitindo o gerenciamento de tutores, animais, consultas, atendimentos e pagamentos.

O software foi desenvolvido para funcionar como uma aplicação desktop integrada a um banco de dados MySQL.

## Funcionalidades do Sistema

### Cadastro de Tutores
Permite cadastrar e consultar informações dos tutores, incluindo nome, CPF, telefone e endereço.

### Cadastro de Animais
Permite cadastrar animais e vinculá-los a um tutor existente, armazenando informações como nome, tipo, raça e idade.

### Agendamento de Consultas
Permite realizar o agendamento de consultas para animais previamente cadastrados.

### Registro de Atendimento
Permite registrar informações relacionadas à consulta, incluindo tratamento, observações e veterinário responsável.

### Busca
Permite pesquisar informações cadastradas no sistema por animal ou tutor.

### Registro de Pagamentos
Permite registrar pagamentos relacionados às consultas e acompanhar a situação do pagamento.

### Autenticação
O sistema possui uma tela de login para controle de acesso dos usuários.

## Banco de Dados

O sistema utiliza **MySQL** para armazenamento persistente das informações.

O script para criação e configuração inicial do banco de dados está disponível em:

`database/clinica_veterinaria.sql`

Banco utilizado:

`clinica_veterinaria`

## Execução do Projeto

1. Instalar e iniciar o MySQL.
2. Executar o arquivo `database/clinica_veterinaria.sql` no MySQL Workbench.
3. Abrir o projeto no Apache NetBeans.
4. Configurar as credenciais locais de acesso ao MySQL quando solicitado.
5. Executar o projeto.

Para testes do sistema, o usuário inicial da aplicação é:

- **Usuário:** admin
- **Senha:** admin123

## Versionamento

O projeto utiliza **Git** para controle de versão e **GitHub** como repositório remoto.

As alterações realizadas no projeto são registradas por meio de commits e sincronizadas com o repositório remoto.