-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 23-Jun-2024 às 06:51
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
('Aniversarios', 'agenda de aniversarios', 1, 13),
('Festas', 'agenda para festas ', 2, 13),
('Festas123', 'agenda de festas do emanuel', 3, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `compromisso`
--

CREATE TABLE `compromisso` (
  `titulo` varchar(100) NOT NULL,
  `descricao` varchar(500) NOT NULL,
  `dataHoraInicio` date NOT NULL,
  `dataHoraFim` date NOT NULL,
  `id` int(11) NOT NULL,
  `local` varchar(100) NOT NULL,
  `id_agenda` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Extraindo dados da tabela `compromisso`
--

INSERT INTO `compromisso` (`titulo`, `descricao`, `dataHoraInicio`, `dataHoraFim`, `id`, `local`, `id_agenda`) VALUES
('aniversario do joaquim', 'festa na casa do joaquim muito legal', '2024-06-13', '2024-06-15', 1, 'casa do joaquim', 1);

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
('EMANUEL', '2000-01-10', 'Masculino', 'EMANUEL@GMAIL.COM', 'EMANUEL', '332323232', 1),
('EMANUEL2', '2012-01-12', 'Não informado', 'EMANUEL@GMAIL.COM', 'EMANUEL2', '23323232', 3),
('emanuel', '2232-01-22', 'Masculino', 'emanuel@gmail.com', 'emanuel3', '233223', 6),
('sdsds', '1222-01-12', 'Não informado', 'sddsds@gmail.com', 'sdsds', '23232', 7),
('sddsds', '2112-01-21', 'Não informado', '212121', '323223', '323223', 8),
('ssdsd', '2012-01-12', 'Não informado', 'sdsdds', 'Emanuel4', '323232', 11),
('guerke', '2012-01-12', 'Masculino', 'emanuel@ddsds.com', 'guerke', '1234', 13);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `compromisso`
--
ALTER TABLE `compromisso`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
