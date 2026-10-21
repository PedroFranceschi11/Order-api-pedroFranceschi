ALTER TABLE produto
    ADD CONSTRAINT chk_produto_categoria
        CHECK (categoria IN ('FERRAMENTAS', 'ELETRONICOS', 'DECORACAO'));
    
    -- Restrição CHECK garante que só aceita valores válidos do Enum Java
    CONSTRAINT chk_produto_categoria CHECK (categoria IN ('FERRAMENTAS', 'ELETRONICOS', 'DECORACAO'))
);