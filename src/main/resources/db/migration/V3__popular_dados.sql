-- ============================================================
-- V3: Ajusta o enum Categoria e popula Marca, Fornecedor e Produto
-- ============================================================

-- Como a tabela produto ainda está vazia, é seguro recriar o tipo
-- em vez de tentar renomear/remover valores individualmente
-- (Postgres não permite remover valores de um enum diretamente).

ALTER TABLE produto ALTER COLUMN categoria TYPE VARCHAR USING categoria::text;
DROP TYPE categoria;
CREATE TYPE categoria AS ENUM ('HIGIENE', 'ELETROS', 'MECANICA');
ALTER TABLE produto ALTER COLUMN categoria TYPE categoria USING categoria::categoria;

-- Marca
INSERT INTO marca (nome) VALUES
                             ('Bralimpia'),
                             ('Volttec'),
                             ('Ferrari Ferramentas');

-- Fornecedor (dados fictícios, mesma lógica da V2 para referenciar cidade por nome)
INSERT INTO fornecedor (nome, contato, cnpj, cidade_id) VALUES
                                                            ('Distribuidora Higiene Sul Ltda', '(51) 3222-1000', '12.345.678/0001-90',
                                                             (SELECT id FROM cidade WHERE nome = 'Porto Alegre')),
                                                            ('Voltmax Eletro Distribuidora', '(41) 3233-2000', '23.456.789/0001-80',
                                                             (SELECT id FROM cidade WHERE nome = 'Curitiba')),
                                                            ('Ferragens Blumenau Comércio', '(47) 3244-3000', '34.567.890/0001-70',
                                                             (SELECT id FROM cidade WHERE nome = 'Blumenau'));

-- Produto
INSERT INTO produto (nome, descricao, preco, marca_id, categoria) VALUES
                                                                      ('Detergente Multiuso 5L', 'Detergente concentrado para limpeza geral', 39.90,
                                                                       (SELECT id FROM marca WHERE nome = 'Bralimpia'), 'HIGIENE'),
                                                                      ('Furadeira de Impacto 750W', 'Furadeira elétrica com maleta e acessórios', 289.90,
                                                                       (SELECT id FROM marca WHERE nome = 'Volttec'), 'ELETROS'),
                                                                      ('Jogo de Chaves Combinadas 12pç', 'Conjunto de chaves combinadas em aço cromo-vanádio', 149.90,
                                                                       (SELECT id FROM marca WHERE nome = 'Ferrari Ferramentas'), 'MECANICA');