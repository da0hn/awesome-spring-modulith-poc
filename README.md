# Spring Modulith POC

* [Documentação Spring Modulith](https://docs.spring.io/spring-modulith/reference/index.html)
* [Exemplos Spring Modulith](https://github.com/spring-projects/spring-modulith/tree/main/spring-modulith-examples)
* [Spring Modulith Crash Course : Building Modular Monoliths using Spring Boot By SivaLabs](https://www.youtube.com/watch?v=FkP2aZiBrhg)
* [Melhore a Modularidade dos Seus Projetos com Spring Modulith – Guia Prático By Giuliana Bezerra](https://www.youtube.com/watch?v=Y1VlOLLJ6Ik)

## Domínio utilizado

### 1. Catalogação (Catalog).

* Responsabilidade: Gerenciar o catálogo de livros.

#### Entidades:

* Livro (ID, título, autor, categoria, ISBN, status - disponível ou indisponível).
* Autor (ID, nome, biografia).
* Categoria (ID, nome, descrição).

#### Funcionalidades:

* CRUD de livros, autores e categorias.
* Alterar status de disponibilidade do livro.
* Pesquisar livros por título, autor ou categoria.

#### Eventos Publicados:

* LivroAtualizado (ex.: para informar outros módulos sobre alterações de status).

### 2. Usuários (Users)

* Responsabilidade: Gerenciar o cadastro e autenticação de usuários.

#### Entidades:

* Usuario (ID, nome, email, tipo - leitor ou administrador, data de cadastro).

#### Funcionalidades:

* Cadastro de novos usuários.
* Atualização de informações.
* Ver histórico de empréstimos e reservas.

#### Eventos Publicados:

* UsuarioCadastrado (ex.: para enviar boas-vindas ou ativar notificações).
* UsuarioAtualizado.

### 3. Empréstimos (Loans)

* Responsabilidade: Gerenciar o processo de empréstimos e devoluções.

#### Entidades:

* Emprestimo (ID, usuário, livro, data de empréstimo, data de devolução, status - ativo ou concluído).

#### Funcionalidades:

* Registrar novos empréstimos.
* Registrar devoluções.
* Consultar histórico de empréstimos.

#### Eventos Publicados:

* LivroEmprestado (ex.: notificar outros módulos sobre empréstimos).
* LivroDevolvido (ex.: atualizar status de disponibilidade no módulo Catalogação).

### 4. Reservas (Reservations)

* Responsabilidade: Permitir que usuários reservem livros indisponíveis.

#### Entidades:

* Reserva (ID, usuário, livro, data de reserva, status - ativo ou concluído).

#### Funcionalidades:

* Criar reserva para livros indisponíveis.
* Liberar reserva quando o livro for devolvido.
* Cancelar reservas.

#### Eventos Consumidos:

* LivroDevolvido (para liberar a reserva e notificar o próximo da fila).
#### Eventos Publicados:

* ReservaCriada.
* ReservaConcluida.

### 5. Notificações (Notifications)

* Responsabilidade: Enviar mensagens e notificações aos usuários.

#### Entidades:

* Notificacao (ID, tipo - email ou SMS, mensagem, destinatário, data de envio, status).

#### Funcionalidades:

* Enviar notificações sobre empréstimos vencidos.
* Informar sobre a disponibilidade de livros reservados.

#### Eventos Consumidos:

* LivroEmprestado (notificar prazo de devolução).
* ReservaConcluida (informar usuário sobre livro disponível).

### Interações entre Módulos

#### Cenário: Empréstimo de Livro

* O usuário solicita o empréstimo de um livro.
* O módulo Empréstimos registra o empréstimo e publica o evento LivroEmprestado.
* O módulo Catalogação consome o evento e atualiza o status do livro para "indisponível".
* O módulo Notificações consome o evento e envia um email ao usuário informando o prazo de devolução.

#### Cenário: Devolução de Livro

* O usuário devolve um livro.
* O módulo Empréstimos registra a devolução e publica o evento LivroDevolvido.
* O módulo Catalogação consome o evento e atualiza o status do livro para "disponível".
* O módulo Reservas verifica se há reservas ativas para o livro e publica o evento ReservaConcluida.
* O módulo Notificações consome o evento e avisa ao próximo usuário sobre a disponibilidade do livro.
