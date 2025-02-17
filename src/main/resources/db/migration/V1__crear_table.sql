CREATE TABLE `gestareas`.`gestareas_estados` (
	`id_estados` INT NOT NULL AUTO_INCREMENT,
	`nombre_estado` VARCHAR(45) NOT NULL COLLATE 'utf8mb3_general_ci',
	`descripcion_estado` VARCHAR(150) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`fecha_creacion` DATETIME NULL DEFAULT NULL,
	`usuario_creacion` VARCHAR(45) NOT NULL COLLATE 'utf8mb3_general_ci',
	`fecha_modificacion` DATETIME NULL DEFAULT NULL,
	`usuario_modificacion` VARCHAR(45) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	PRIMARY KEY (`id_estados`) USING BTREE,
	UNIQUE INDEX `nombre_estado_UNIQUE` (`nombre_estado`) USING BTREE
);

CREATE TABLE `gestareas`.`gestareas_usuarios` (
	`id_usuarios` INT NOT NULL AUTO_INCREMENT,
	`usuario` VARCHAR(45) NOT NULL COLLATE 'utf8mb3_general_ci',
	`nombre` VARCHAR(60) NOT NULL COLLATE 'utf8mb3_general_ci',
	`correo_electronico` VARCHAR(60) NOT NULL COLLATE 'utf8mb3_general_ci',
	`contrasenia` VARCHAR(500) NOT NULL COLLATE 'utf8mb3_general_ci',
	`fecha_creacion` DATETIME NOT NULL,
	`usuario_creacion` VARCHAR(45) NOT NULL COLLATE 'utf8mb3_general_ci',
	`fecha_modificacion` DATETIME NULL DEFAULT NULL,
	`usuario_modificacion` VARCHAR(45) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	PRIMARY KEY (`id_usuarios`) USING BTREE,
	UNIQUE INDEX `unq_usuario` (`usuario`) USING BTREE,
	UNIQUE INDEX `unq_correo_electronico` (`correo_electronico`) USING BTREE
);

CREATE TABLE `gestareas`.`gestareas_tareas` (
	`id_tareas` INT NOT NULL AUTO_INCREMENT,
	`id_usuarios` INT NOT NULL,
	`id_estados` INT NOT NULL,
	`titulo` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
	`descripcion` VARCHAR(500) NOT NULL COLLATE 'utf8mb3_general_ci',
	`fecha_creacion` DATETIME NOT NULL,
	`usuario_creacion` VARCHAR(45) NOT NULL COLLATE 'utf8mb3_general_ci',
	`fecha_modificacion` DATETIME NULL DEFAULT NULL,
	`usuario_modificacion` VARCHAR(45) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	PRIMARY KEY (`id_tareas`, `id_usuarios`, `id_estados`) USING BTREE,
	INDEX `idx_gesTareas_estado` (`id_estados`) USING BTREE,
	INDEX `idx_gesTareas_usuario` (`id_usuarios`) USING BTREE,
	CONSTRAINT `fk_gesTareas_estados` FOREIGN KEY (`id_estados`) REFERENCES `gestareas_estados` (`id_estados`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `fk_gesTareas_usuario_` FOREIGN KEY (`id_usuarios`) REFERENCES `gestareas_usuarios` (`id_usuarios`) ON UPDATE NO ACTION ON DELETE NO ACTION
);


