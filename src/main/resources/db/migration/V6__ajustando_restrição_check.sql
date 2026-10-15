CREATE TABLE produto (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(30) NOT NULL,
    
    -- Restrição CHECK garante que só aceita valores válidos do Enum Java
    CONSTRAINT chk_produto_categoria CHECK (categoria IN ('FERRAMENTAS', 'ELETRONICOS', 'DECORACAO'))
);