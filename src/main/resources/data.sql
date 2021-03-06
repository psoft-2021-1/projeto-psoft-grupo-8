insert into usuario (ID, EMAIL, USERNAME, SENHA, NOME, TIPO_USUARIO)
values(1001, 'fulano@tcc.com', 'fulaninho', 'senhadofulano', 'Fulano', 'COORDENADOR');

insert into usuario (ID, EMAIL, USERNAME, SENHA, NOME, TIPO_USUARIO)
values(1002, 'joao@tcc.com', '11020030006', 'senhadojoao', 'João Gabriel', 'ALUNO');

insert into usuario (ID, EMAIL, USERNAME, SENHA, NOME, TIPO_USUARIO)
values(1003, 'jose@tcc.com', '11020036506', 'senhanova', 'José', 'ALUNO');

insert into usuario (ID, EMAIL, USERNAME, SENHA, NOME, TIPO_USUARIO)
values(1005, 'everton@tcc.com', '1230000000', 'senhadoeverton', 'Everton', 'PROFESSOR');

insert into coordenador (ID, CPF)
values(1001, 10020030006);

insert into aluno (ID, PERIODO_CONCLUSAO)
values(1002, 2023);

insert into aluno (ID, PERIODO_CONCLUSAO)
values(1003, 2023);

insert into professor (ID, QUOTA)
values(1005, 5);

insert into area_de_estudo (ID, NOME)
values(1001, 'DESENVOLVIMENTO DE SOFTWARE');

insert into area_de_estudo (ID, NOME)
values(1002, 'IA');

insert into area_de_estudo (ID, NOME)
values(1003, 'BANCO DE DADOS');

insert into aluno_areas_de_estudo(ALUNO_ID, AREAS_DE_ESTUDO_ID)
values(1002, 1002);

insert into professor_areas_de_estudo(PROFESSOR_ID, AREAS_DE_ESTUDO_ID)
values(1005, 1002);

insert into tema_tcc(ID, DESCRICAO, STATUS, TITULO, USUARIO_CRIADOR_ID)
values(10, '', '', 'SQL', 1002);

insert into tema_tcc(ID, DESCRICAO, STATUS, TITULO, USUARIO_CRIADOR_ID)
values(11, '', '', 'PADROES DE PROJ', 1005);

insert into tema_tcc_areas_de_estudo_relacionadas(TEMA_TCC_ID, AREAS_DE_ESTUDO_RELACIONADAS_ID)
values(10, 1002);

insert into tema_tcc_areas_de_estudo_relacionadas(TEMA_TCC_ID, AREAS_DE_ESTUDO_RELACIONADAS_ID)
values(11, 1002);

insert into orientacao(ID, FINALIZADA, SEMESTRE, ALUNO_ID, PROFESSOR_ID, TEMA_TCC_ID)
values(1, FALSE, '20.1', 1002, 1005, 10);

insert into orientacao(ID, FINALIZADA, SEMESTRE, ALUNO_ID, PROFESSOR_ID, TEMA_TCC_ID)
values(2, FALSE, '20.2', 1003, 1005, 11);