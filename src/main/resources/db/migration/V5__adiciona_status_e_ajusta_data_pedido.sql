-- ============================================================
-- V5: Ajustes na tabela pedido (Coluna status e tipo timestamp)
-- ============================================================

-- 1. Adiciona a coluna 'status' na tabela 'pedido' com valor padrão 'PENDENTE'
ALTER TABLE pedido 
ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'PENDENTE';

-- 2. Altera o tipo da coluna 'data_pedido' para TIMESTAMP (para aceitar data e hora do LocalDateTime)
ALTER TABLE pedido 
ALTER COLUMN data_pedido TYPE TIMESTAMP;