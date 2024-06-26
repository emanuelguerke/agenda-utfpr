-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 26-Jun-2024 às 04:28
-- Versão do servidor: 10.4.32-MariaDB
-- versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `agendadb`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `agenda`
--

CREATE TABLE `agenda` (
  `nome` varchar(100) NOT NULL,
  `descricao` varchar(500) NOT NULL,
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Extraindo dados da tabela `agenda`
--

INSERT INTO `agenda` (`nome`, `descricao`, `id`, `id_usuario`) VALUES
('rodeios', 'rodeios em ponta grossa', 8, 13),
('uiui', 'uiui123', 9, 13),
('aniversarios', 'aniversarios boladoes', 13, 13),
('dsdsds', 'dsds', 16, 13),
('aaaaaaaaaaaaa', 'aaaaaaaaaaaaaaaaaaaaa', 17, 13),
('zzzzzzzzzzzzzzzzz', 'xxxxxxxxxxxxxxxxxxxxxxxxx', 18, 13),
('ccccccccccccccc', 'xxxxxxxxxxxxxxxxx', 19, 13),
('qqqqqqqqqqqq', 'sssssssssss', 20, 13),
('aaaaaaaaaaaaaa', 'qqqqqqqq', 21, 13),
('dddddddddddd', 'ssss', 22, 13),
('ddddddddd', 'aaaaaa', 23, 13),
('ddddddddddd', 'aaaaaaaaaaaaa', 24, 13),
('wwwwwwwwww', 'wwwwwwwww', 25, 13),
('Aniversario', 'aninini', 26, 1),
('provas', 'provas da universidade', 27, 1),
('Festas', 'festas legais', 28, 1),
('agenda', 'agenda', 29, 13),
('Festas', 'festas legais', 30, 16),
('utena festas ', 'utena123', 32, 16),
('sunraku festas', '123', 33, 21),
('kkkkk', 'kkkkkk', 36, 13),
('Aniversarios', 'aniversarios do sunraku', 38, 22),
('Bailes', 'agenda para bailes', 39, 22);

-- --------------------------------------------------------

--
-- Estrutura da tabela `compromisso`
--

CREATE TABLE `compromisso` (
  `titulo` varchar(100) NOT NULL,
  `descricao` varchar(500) NOT NULL,
  `dataInicio` datetime NOT NULL,
  `dataFim` datetime NOT NULL,
  `id` int(11) NOT NULL,
  `local` varchar(100) NOT NULL,
  `id_agenda` int(11) NOT NULL,
  `horaInicio` varchar(100) NOT NULL,
  `horaFim` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Extraindo dados da tabela `compromisso`
--

INSERT INTO `compromisso` (`titulo`, `descricao`, `dataInicio`, `dataFim`, `id`, `local`, `id_agenda`, `horaInicio`, `horaFim`) VALUES
('aniversario da maria', 'festa na casa da maria', '2024-06-16 00:00:00', '2024-06-30 00:00:00', 9, 'curitiba', 13, '15:14:13', '13:14:15'),
('aniversario do di', 'aniversario', '2024-06-27 00:00:00', '2024-06-28 00:00:00', 11, 'londrina', 13, '12:12:12', '13:13:13'),
('Aniversario do emanuel', 'niver do emanuel', '2024-06-26 00:00:00', '2024-06-27 00:00:00', 15, 'arapoti', 13, '12:12:12', '13:13:13'),
('aniversario do arthur', 'aniversario', '2024-06-26 00:00:00', '2024-06-27 00:00:00', 17, 'jaguariaiva', 13, '10:10:10', '10:10:23'),
('bailao123', 'bailao', '2024-06-28 00:00:00', '2024-06-29 00:00:00', 18, 'pirai', 39, '12:12:12', '11:11:11'),
('baile15', '1515', '2024-06-28 00:00:00', '2024-06-28 00:00:00', 19, 'Itararé', 39, '05:05:05', '06:06:06');

-- --------------------------------------------------------

--
-- Estrutura da tabela `convite`
--

CREATE TABLE `convite` (
  `id` int(11) NOT NULL,
  `aceite` tinyint(1) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_compromisso` int(11) NOT NULL,
  `id_agenda` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `imagem`
--

CREATE TABLE `imagem` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `nome_completo` varchar(100) NOT NULL,
  `data_nascimento` date NOT NULL,
  `genero` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `nome_usuario` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`nome_completo`, `data_nascimento`, `genero`, `email`, `nome_usuario`, `senha`, `id`) VALUES
('EMANUEL', '2000-01-10', 'Masculino', 'EMANUEL@GMAIL.COM', 'EMANUEL', '123', 1),
('EMANUEL2', '2012-01-12', 'Não informado', 'EMANUEL@GMAIL.COM', 'EMANUEL2', '23323232', 3),
('emanuel', '2232-01-22', 'Masculino', 'emanuel@gmail.com', 'emanuel3', '233223', 6),
('sdsds', '1222-01-12', 'Não informado', 'sddsds@gmail.com', 'sdsds', '23232', 7),
('sddsds', '2112-01-21', 'Não informado', '212121', '323223', '323223', 8),
('ssdsd', '2012-01-12', 'Não informado', 'sdsdds', 'Emanuel4', '323232', 11),
('guerke', '2012-01-12', 'Masculino', 'emanuel@ddsds.com', 'guerke', '1234', 13),
('Utena', '2022-12-10', 'Feminino', 'utena@gmail.com', 'utena', '123', 16),
('eu123', '2022-12-12', 'Masculino', 'eu123@gmail.com', 'eu123', '123', 17),
('utena12', '2022-12-12', 'Feminino', 'utena12@gmail.com', 'utena12', '123', 18),
('Testando', '2022-12-12', 'Masculino', 'utenna@gmail.com', 'utenaa', '123', 19),
('eee', '1222-12-12', 'Masculino', 'eeeee@gmail.com', 'eeee', '123', 20),
('sunraku', '2020-12-12', 'Masculino', 'sunraku@gmail.com', 'sunraku', '123', 21),
('sunraku123', '2022-12-12', 'Masculino', 'sunraku1234@gmail.com', 'sunraku1234', '1234', 22);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `agenda`
--
ALTER TABLE `agenda`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_agenda_usuario` (`id_usuario`);

--
-- Índices para tabela `compromisso`
--
ALTER TABLE `compromisso`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_compromisso_agenda` (`id_agenda`);

--
-- Índices para tabela `convite`
--
ALTER TABLE `convite`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_convite_usuario` (`id_usuario`),
  ADD KEY `fk_convite_agenda` (`id_agenda`),
  ADD KEY `fk_convite_compromisso` (`id_compromisso`);

--
-- Índices para tabela `imagem`
--
ALTER TABLE `imagem`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_imagem_usuario` (`id_usuario`);

--
-- Índices para tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `INDICE1` (`nome_usuario`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `agenda`
--
ALTER TABLE `agenda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de tabela `compromisso`
--
ALTER TABLE `compromisso`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de tabela `convite`
--
ALTER TABLE `convite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `imagem`
--
ALTER TABLE `imagem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `agenda`
--
ALTER TABLE `agenda`
  ADD CONSTRAINT `fk_agenda_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `compromisso`
--
ALTER TABLE `compromisso`
  ADD CONSTRAINT `fk_compromisso_agenda` FOREIGN KEY (`id_agenda`) REFERENCES `agenda` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `convite`
--
ALTER TABLE `convite`
  ADD CONSTRAINT `fk_convite_agenda` FOREIGN KEY (`id_agenda`) REFERENCES `agenda` (`id`),
  ADD CONSTRAINT `fk_convite_compromisso` FOREIGN KEY (`id_compromisso`) REFERENCES `compromisso` (`id`),
  ADD CONSTRAINT `fk_convite_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

--
-- Limitadores para a tabela `imagem`
--
ALTER TABLE `imagem`
  ADD CONSTRAINT `fk_imagem_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
