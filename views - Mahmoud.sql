CREATE VIEW PorAno_View
BUILD IMMEDIATE
REFRESH FORCE ON DEMAND
AS
WITH todas_ocorrencias AS (
    SELECT oco_cla_id AS cla_id, oco_dia AS data_ocorrencia
    FROM ocorrencias
    WHERE oco_dia IS NOT NULL

    UNION ALL

    SELECT hoco_cla_id AS cla_id, hoco_dia AS data_ocorrencia
    FROM h_ocorrencias
    WHERE hoco_dia IS NOT NULL
)

SELECT
    c.cla_nome AS classificacao,
    COUNT(CASE WHEN EXTRACT(YEAR FROM t.data_ocorrencia) = 2021 THEN 1 END) AS "2021",
    COUNT(CASE WHEN EXTRACT(YEAR FROM t.data_ocorrencia) = 2022 THEN 1 END) AS "2022",
    COUNT(CASE WHEN EXTRACT(YEAR FROM t.data_ocorrencia) = 2023 THEN 1 END) AS "2023"
FROM
    todas_ocorrencias t
JOIN classificacoes_ocorrencias c ON c.cla_id = t.cla_id
GROUP BY
    c.cla_nome
ORDER BY
    c.cla_nome;



CREATE MATERIALIZED VIEW PorAno_View
BUILD IMMEDIATE
REFRESH FORCE ON DEMAND
AS
WITH todas_ocorrencias AS (
    SELECT oco_cla_id AS cla_id, oco_dia AS data_ocorrencia
    FROM ocorrencias
    WHERE oco_dia IS NOT NULL

    UNION ALL

    SELECT hoco_cla_id AS cla_id, hoco_dia AS data_ocorrencia
    FROM h_ocorrencias
    WHERE hoco_dia IS NOT NULL
)

SELECT
    c.cla_nome AS classificacao,
    COUNT(CASE WHEN EXTRACT(YEAR FROM t.data_ocorrencia) = 2021 THEN 1 END) AS "2021",
    COUNT(CASE WHEN EXTRACT(YEAR FROM t.data_ocorrencia) = 2022 THEN 1 END) AS "2022",
    COUNT(CASE WHEN EXTRACT(YEAR FROM t.data_ocorrencia) = 2023 THEN 1 END) AS "2023"
FROM
    todas_ocorrencias t
JOIN classificacoes_ocorrencias c ON c.cla_id = t.cla_id
GROUP BY
    c.cla_nome
ORDER BY
    c.cla_nome;

