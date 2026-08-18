-- ============================================================
-- V4: Altera IDs para BIGINT, tipo do Preço e ajusta ENUM Categoria
-- ============================================================

-- 1. Atualizar ENUM Categoria para 'HIGIENE', 'ELETRO', 'MECANICA'
ALTER TABLE produto ALTER COLUMN categoria TYPE VARCHAR USING categoria::text;
DROP TYPE categoria;
CREATE TYPE categoria AS ENUM ('HIGIENE', 'ELETRO', 'MECANICA');

-- Se houver dados com 'ELETROS', atualiza para 'ELETRO' antes de reverter para ENUM
UPDATE produto SET categoria = 'ELETRO' WHERE categoria = 'ELETROS';

ALTER TABLE produto ALTER COLUMN categoria TYPE categoria USING categoria::categoria;

-- 2. Alterar IDs e Chaves Estrangeiras para BIGINT (BIGSERIAL no PostgreSQL)
ALTER TABLE estado ALTER COLUMN id TYPE BIGINT;

ALTER TABLE cidade ALTER COLUMN id TYPE BIGINT;
ALTER TABLE cidade ALTER COLUMN estado_id TYPE BIGINT;

ALTER TABLE cliente ALTER COLUMN id TYPE BIGINT;
ALTER TABLE cliente ALTER COLUMN cidade_id TYPE BIGINT;

ALTER TABLE fornecedor ALTER COLUMN id TYPE BIGINT;
ALTER TABLE fornecedor ALTER COLUMN cidade_id TYPE BIGINT;

ALTER TABLE marca ALTER COLUMN id TYPE BIGINT;

ALTER TABLE produto ALTER COLUMN id TYPE BIGINT;
ALTER TABLE produto ALTER COLUMN marca_id TYPE BIGINT;

ALTER TABLE pedido ALTER COLUMN id TYPE BIGINT;
ALTER TABLE pedido ALTER COLUMN cliente_id TYPE BIGINT;

ALTER TABLE item_pedido ALTER COLUMN id TYPE BIGINT;
ALTER TABLE item_pedido ALTER COLUMN pedido_id TYPE BIGINT;
ALTER TABLE item_pedido ALTER COLUMN produto_id TYPE BIGINT;

ALTER TABLE produto_fornecedor ALTER COLUMN produto_id TYPE BIGINT;
ALTER TABLE produto_fornecedor ALTER COLUMN fornecedor_id TYPE BIGINT;

-- 3. Alterar tipo do Preço para DOUBLE PRECISION (equivalente ao Double)
ALTER TABLE produto ALTER COLUMN preco TYPE DOUBLE PRECISION;
ALTER TABLE item_pedido ALTER COLUMN preco TYPE DOUBLE PRECISION;