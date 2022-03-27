insert into usuario (ID, EMAIL, USERNAME, SENHA, NOME, TIPO_USUARIO)
values(1001, 'fulano@tcc.com', 'fulaninho', 'senhadofulano', 'Fulano', 'COORDENADOR');

insert into usuario (ID, EMAIL, USERNAME, SENHA, NOME, TIPO_USUARIO)
values(1002, 'joao@tcc.com', '11020030006', 'senhadojoao', 'João Gabriel', 'ALUNO');

insert into usuario (ID, EMAIL, USERNAME, SENHA, NOME, TIPO_USUARIO)
values(1006, 'everton@tcc.com', '1230000000', 'senhadoeverton', 'Everton', 'PROFESSOR');

insert into coordenador (ID, CPF)
values(1001, 10020030006);

insert into aluno (ID, PERIODO_CONCLUSAO)
values(1002, 2023);

insert into professor (ID, QUOTA)
values(1006, 5);

--insert into professor (ID, EMAIL, USERNAME, SENHA, NOME, QUOTA)
--values(1005, 'fubica@tcc.com', '12312312300', 'senhadofubica', 'Fubica', 5);

insert into area_de_estudo (ID, NOME)
values(1001, 'DESENVOLVIMENTO DE SOFTWARE');

insert into area_de_estudo (ID, NOME)
values(1002, 'STRING');

insert into area_de_estudo (ID, NOME)
values(1003, 'BANCO DE DADOS');