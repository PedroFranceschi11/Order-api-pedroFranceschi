-- ============================================================
-- V2: Popula as tabelas Estado e Cidade
-- ============================================================

INSERT INTO estado (nome, uf) VALUES
                                  ('Santa Catarina', 'SC'),
                                  ('Paraná', 'PR'),
                                  ('Rio Grande do Sul', 'RS');

-- Santa Catarina
INSERT INTO cidade (nome, estado_id) VALUES
                                         ('Florianópolis', (SELECT id FROM estado WHERE uf = 'SC')),
                                         ('Joinville',     (SELECT id FROM estado WHERE uf = 'SC')),
                                         ('Blumenau',      (SELECT id FROM estado WHERE uf = 'SC')),
                                         ('Chapecó',       (SELECT id FROM estado WHERE uf = 'SC'));

-- Paraná
INSERT INTO cidade (nome, estado_id) VALUES
                                         ('Curitiba',      (SELECT id FROM estado WHERE uf = 'PR')),
                                         ('Londrina',      (SELECT id FROM estado WHERE uf = 'PR')),
                                         ('Maringá',       (SELECT id FROM estado WHERE uf = 'PR')),
                                         ('Cascavel',      (SELECT id FROM estado WHERE uf = 'PR'));

-- Rio Grande do Sul
INSERT INTO cidade (nome, estado_id) VALUES
                                         ('Porto Alegre',  (SELECT id FROM estado WHERE uf = 'RS')),
                                         ('Caxias do Sul', (SELECT id FROM estado WHERE uf = 'RS')),
                                         ('Santa Maria',   (SELECT id FROM estado WHERE uf = 'RS')),
                                         ('Sobradinho',    (SELECT id FROM estado WHERE uf = 'RS'));