-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 25-Jun-2024 às 05:23
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
('pasteladas', 'pasteladas da igreja', 6, 13),
('rodeios', 'rodeios em ponta grossa', 8, 13),
('uiui', 'uiui123', 9, 13),
('aniversarios', 'aniversarios boladoes', 13, 13),
('sdds', 'dsdsds', 15, 13),
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
('guerke', '123', 31, 13),
('utena festas ', 'utena123', 32, 16),
('sunraku festas', '123', 33, 21);

-- --------------------------------------------------------

--
-- Estrutura da tabela `compromisso`
--

CREATE TABLE `compromisso` (
  `titulo` varchar(100) NOT NULL,
  `descricao` varchar(500) NOT NULL,
  `dataHoraInicio` datetime NOT NULL,
  `dataHoraFim` datetime NOT NULL,
  `id` int(11) NOT NULL,
  `local` varchar(100) NOT NULL,
  `id_agenda` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Extraindo dados da tabela `compromisso`
--

INSERT INTO `compromisso` (`titulo`, `descricao`, `dataHoraInicio`, `dataHoraFim`, `id`, `local`, `id_agenda`) VALUES
('festa na casa do joaquim', 'festa na casa do joaquim', '2024-06-24 07:13:00', '2024-07-17 11:28:00', 1, 'ponta grossa', 13),
('aniversario da paula', 'aniversario da paula', '2024-06-25 12:26:00', '2024-06-24 15:18:16', 2, 'curitiba', 13),
('aniversario do emanuel', 'aniversario muito bom', '2024-06-24 16:26:38', '2024-06-24 17:26:38', 3, 'casa do emanuel', 26);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT de tabela `compromisso`
--
ALTER TABLE `compromisso`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `imagem`
--
ALTER TABLE `imagem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

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
-- Limitadores para a tabela `imagem`
--
ALTER TABLE `imagem`
  ADD CONSTRAINT `fk_imagem_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
