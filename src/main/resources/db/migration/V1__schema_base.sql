-- V1: schema inicial do order-api.
-- Aplicar somente em banco dedicado e vazio.
-- Todos os IDs são BIGINT/BIGSERIAL, compatíveis com Long + GenerationType.IDENTITY.

CREATE TABLE estado (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    CONSTRAINT uk_estado_uf UNIQUE (uf),
    CONSTRAINT ck_estado_uf CHECK (uf ~ '^[A-Z]{2}$')
);

CREATE TABLE cidade (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    estado_id BIGINT NOT NULL,
    CONSTRAINT fk_cidade_estado FOREIGN KEY (estado_id) REFERENCES estado(id),
    CONSTRAINT uk_cidade_estado_nome UNIQUE (estado_id, nome)
);

CREATE TABLE cliente (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    contato VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    cidade_id BIGINT NOT NULL,
    CONSTRAINT uk_cliente_cnpj UNIQUE (cnpj),
    CONSTRAINT ck_cliente_cnpj CHECK (cnpj ~ '^[0-9]{14}$'),
    CONSTRAINT fk_cliente_cidade FOREIGN KEY (cidade_id) REFERENCES cidade(id)
);

CREATE TABLE fornecedor (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    contato VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    cidade_id BIGINT NOT NULL,
    CONSTRAINT uk_fornecedor_cnpj UNIQUE (cnpj),
    CONSTRAINT ck_fornecedor_cnpj CHECK (cnpj ~ '^[0-9]{14}$'),
    CONSTRAINT fk_fornecedor_cidade FOREIGN KEY (cidade_id) REFERENCES cidade(id)
);

CREATE TABLE marca (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    CONSTRAINT uk_marca_nome UNIQUE (nome)
);

CREATE TABLE produto (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    preco NUMERIC(12,2) NOT NULL,
    marca_id BIGINT NOT NULL,
    categoria VARCHAR(20) NOT NULL,
    CONSTRAINT fk_produto_marca FOREIGN KEY (marca_id) REFERENCES marca(id),
    CONSTRAINT ck_produto_categoria CHECK (categoria IN ('HIGIENE', 'ELETRO', 'MECANICA'))
);

CREATE TABLE pedido (
    id BIGSERIAL PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    data_pedido TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDENTE',
    CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    CONSTRAINT ck_pedido_status CHECK (status IN ('PENDENTE', 'PROCESSANDO', 'FINALIZADO', 'CANCELADO'))
);

CREATE TABLE item_pedido (
    id BIGSERIAL PRIMARY KEY,
    pedido_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INTEGER NOT NULL,
    preco NUMERIC(12,2) NOT NULL,
    CONSTRAINT fk_item_pedido_pedido FOREIGN KEY (pedido_id) REFERENCES pedido(id),
    CONSTRAINT fk_item_pedido_produto FOREIGN KEY (produto_id) REFERENCES produto(id),
    CONSTRAINT ck_item_pedido_quantidade CHECK (quantidade > 0)
);

CREATE TABLE produto_fornecedor (
    id BIGSERIAL PRIMARY KEY,
    produto_id BIGINT NOT NULL,
    fornecedor_id BIGINT NOT NULL,
    data_prevista_entrega DATE NOT NULL,
    CONSTRAINT fk_produto_fornecedor_produto FOREIGN KEY (produto_id) REFERENCES produto(id),
    CONSTRAINT fk_produto_fornecedor_fornecedor FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(id),
    CONSTRAINT uk_produto_fornecedor UNIQUE (produto_id, fornecedor_id)
);

CREATE INDEX idx_cliente_cidade_id ON cliente(cidade_id);
CREATE INDEX idx_fornecedor_cidade_id ON fornecedor(cidade_id);
CREATE INDEX idx_produto_marca_id ON produto(marca_id);
CREATE INDEX idx_pedido_cliente_id ON pedido(cliente_id);
CREATE INDEX idx_item_pedido_pedido_id ON item_pedido(pedido_id);
CREATE INDEX idx_item_pedido_produto_id ON item_pedido(produto_id);
CREATE INDEX idx_produto_fornecedor_fornecedor_id ON produto_fornecedor(fornecedor_id);
