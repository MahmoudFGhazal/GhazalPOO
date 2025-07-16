use ghazal;

-- 5 categorias
INSERT INTO categories (cat_category) VALUES
('Sala de Jantar'),
('Sala de Estar'),
('Quarto'),
('Escritório'),
('Cozinha');

-- 5 cores
INSERT INTO colors (col_color) VALUES
('Branco'),
('Preto'),
('Marrom'),
('Cinza'),
('Natural');

-- 3 tipos de móveis
INSERT INTO furniture_types (fut_furniture_type) VALUES
('Cadeira'),
('Sofá'),
('Mesa');

-- 3 fabricantes
INSERT INTO manufacturers (man_manufacturer) VALUES
('Ghazal Móveis'),
('DecorArt'),
('Mobiliário Premium');

-- 3 materiais
INSERT INTO materials (mat_material) VALUES
('Madeira Maciça'),
('MDF'),
('Metal');

-- 10 móveis (fur_image deixado NULL)
INSERT INTO furnitures (
  fur_model, fur_height, fur_width, fur_depth, fur_weight,
  fur_characteristics, fur_image, fur_price,
  fur_stock, fur_fut_id, fur_col_id, fur_man_id
) VALUES
('Cadeira Viena', 0.90, 0.45, 0.50, 7.500, 'Assento estofado, estrutura em madeira', "https://thonart.com.br/public/files/item-27355000-cadeira-viena-assento-em-palha-v1-a.jpg", 249.90, 20, 1, 1, 1),
('Cadeira Roma', 0.95, 0.48, 0.52, 8.200, 'Encosto alto, pés metálicos', "https://images.tcdn.com.br/img/img_prod/793921/cadeira_roma_tecido_linho_bege_base_aco_preto_ooca_moveis_365_1_ad4567da0632ca71b7ef37eece94b8a4.jpg" , 279.90, 15, 1, 2, 2),
('Sofá Milano 3 lugares', 0.85, 2.10, 0.90, 45.000, 'Revestimento em tecido cinza, 3 assentos', NULL, 1999.00, 5, 2, 4, 1),
('Sofá Napoli 2 lugares', 0.80, 1.60, 0.85, 35.000, 'Tecido marrom, reclinável', NULL, 1499.00, 8, 2, 3, 3),
('Mesa Verona 6 cadeiras', 0.75, 1.80, 0.90, 40.000, 'Tampo MDF, pés de madeira', NULL, 2499.00, 3, 3, 5, 2),
('Mesa Ricci', 0.75, 1.20, 0.80, 25.000, 'Mesa lateral para sala de estar', NULL, 899.00, 10, 3, 2, 1),
('Cadeira Oslo', 0.88, 0.46, 0.50, 7.000, 'Estrutura metálica e assento de couro sintético', NULL, 319.90, 12, 1, 3, 3),
('Sofá Berlin 4 lugares', 0.90, 2.30, 0.95, 55.000, 'Tecido preto, almofadas soltas', NULL, 2599.00, 2, 2, 2, 2),
('Mesa Cooktop', 0.85, 1.40, 0.80, 30.000, 'Mesa para cozinha com suporte para fogão', NULL, 1799.00, 4, 3, 5, 2),
('Cadeira Capri', 0.92, 0.47, 0.51, 8.500, 'Design moderno, estofado acolchoado', NULL, 359.90, 18, 1, 4, 3);

-- Relacionamento de móveis ↔ categorias
INSERT INTO furnitures_categories (fca_cat_id, fca_fur_id) VALUES
(1,1),(1,2),(2,3),(2,4),(1,5),(2,6),(3,7),(2,8),(5,9),(1,10);

-- Relacionamento de móveis ↔ materiais
INSERT INTO furnitures_materials (fma_mat_id, fma_fur_id) VALUES
(2,1),(1,2),(2,3),(1,4),(2,5),(1,6),(3,7),(2,8),(1,9),(2,10);

insert into users (usr_email, usr_password, usr_name, usr_cpf) values 
	("admin@gmail.com", "1234", "Admin", "123.456.789-90");

insert into favorites (fav_usr_id, fav_fur_id) values (1, 3), (1,5);