CREATE TABLE IF NOT EXISTS `%s`
(
    `id`   BIGINT(20)  NOT NULL AUTO_INCREMENT,
    `qq`   BIGINT(20)  NOT NULL,
    `uuid` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `qq` (`qq`)
)
    COLLATE = 'utf8_general_ci'
    ENGINE = InnoDB
;
