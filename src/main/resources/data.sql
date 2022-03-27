insert into usuario (ID, EMAIL, USERNAME, SENHA, NOME, TIPO_USUARIO)
values(1001, 'fulano@tcc.com', 'fulaninho', 'senhadofulano', 'Fulano', 'COORDENADOR');

insert into usuario (ID, EMAIL, USERNAME, SENHA, NOME, TIPO_USUARIO)
values(1002, 'joao@tcc.com', '11020030006', 'senhadojoao', 'João Gabriel', 'ALUNO');

insert into usuario (ID, EMAIL, USERNAME, SENHA, NOME, TIPO_USUARIO)
values(1005, 'everton@tcc.com', '1230000000', 'senhadoeverton', 'Everton', 'PROFESSOR');

insert into coordenador (ID, CPF)
values(1001, 10020030006);

insert into aluno (ID, PERIODO_CONCLUSAO)
values(1002, 2023);

insert into professor (ID, QUOTA)
values(1005, 5);

insert into area_de_estudo (ID, NOME)
values(1001, 'DESENVOLVIMENTO DE SOFTWARE');

insert into area_de_estudo (ID, NOME)
values(1002, 'STRING');

insert into area_de_estudo (ID, NOME)
values(1003, 'BANCO DE DADOS');

insert into aluno_areas_de_estudo(ALUNO_ID, AREAS_DE_ESTUDO_ID)
values(1002, 1002);

insert into professor_areas_de_estudo(PROFESSOR_ID, AREAS_DE_ESTUDO_ID)
values(1005, 1002);

insert into tema_tcc(ID, DESCRICAO, STATUS, TITULO, USUARIO_CRIADOR_ID)
values(10, '', '', 'STRING', 1002);

insert into tema_tcc(ID, DESCRICAO, STATUS, TITULO, USUARIO_CRIADOR_ID)
values(11, '', '', 'STRING2', 1005);

insert into tema_tcc_areas_de_estudo_relacionadas(TEMA_TCC_ID, AREAS_DE_ESTUDO_RELACIONADAS_ID)
values(10, 1002);

insert into tema_tcc_areas_de_estudo_relacionadas(TEMA_TCC_ID, AREAS_DE_ESTUDO_RELACIONADAS_ID)
values(11, 1002);