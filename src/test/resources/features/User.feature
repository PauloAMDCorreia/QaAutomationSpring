# language: pt
Funcionalidade: Fluxo completo de usuário e livros

  Cenário: Criar e gerenciar um usuário na API BookStore
    Dado que desejo criar um usuário
    Quando gero um token de acesso para o usuário criado
    E verifico se o usuário está autorizado
    E listo os livros disponíveis
    E alugo dois livros de minha escolha
    Então confirmo que os livros estão associados ao usuário
