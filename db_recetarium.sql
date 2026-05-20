-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: db_recetarium
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alergias`
--

DROP TABLE IF EXISTS `alergias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alergias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKag47yvtmth5v58abkenbejl1j` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alergias`
--

LOCK TABLES `alergias` WRITE;
/*!40000 ALTER TABLE `alergias` DISABLE KEYS */;
INSERT INTO `alergias` VALUES (36,'Aditivos / Colorantes artificiales'),(14,'Altramuces'),(32,'Altramuz'),(10,'Apio'),(4,'Cacahuete'),(33,'Canela'),(31,'Carne roja (Alpha-gal)'),(7,'Crustáceos'),(17,'Fresa'),(21,'Fructosa'),(5,'Frutos de cáscara'),(1,'Gluten'),(23,'Histamina'),(3,'Huevo'),(25,'Kiwi'),(2,'Lactosa'),(16,'Legumbres'),(15,'Maíz'),(19,'Marisco'),(18,'Melocotón'),(8,'Moluscos'),(11,'Mostaza'),(35,'Níquel (dieta baja en níquel)'),(6,'Pescado'),(26,'Piña'),(30,'Pipas de calabaza'),(29,'Pipas de girasol'),(24,'Plátano'),(20,'Proteína de leche de vaca'),(12,'Sésamo'),(28,'Setas / Hongos'),(9,'Soja'),(22,'Sorbitol'),(13,'Sulfitos'),(27,'Tomate'),(34,'Vainilla');
/*!40000 ALTER TABLE `alergias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alergias_ingredientes`
--

DROP TABLE IF EXISTS `alergias_ingredientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alergias_ingredientes` (
  `alergia_id` bigint NOT NULL,
  `ingrediente_id` bigint NOT NULL,
  KEY `FKkn0ojpeh084whclg4ppex6pmx` (`ingrediente_id`),
  KEY `FKgbikfk4clg3t0498ogqvoueus` (`alergia_id`),
  CONSTRAINT `FKgbikfk4clg3t0498ogqvoueus` FOREIGN KEY (`alergia_id`) REFERENCES `alergias` (`id`),
  CONSTRAINT `FKkn0ojpeh084whclg4ppex6pmx` FOREIGN KEY (`ingrediente_id`) REFERENCES `ingredientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alergias_ingredientes`
--

LOCK TABLES `alergias_ingredientes` WRITE;
/*!40000 ALTER TABLE `alergias_ingredientes` DISABLE KEYS */;
INSERT INTO `alergias_ingredientes` VALUES (1,13),(2,3),(2,14),(3,2),(7,15),(4,18),(5,17);
/*!40000 ALTER TABLE `alergias_ingredientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favoritos`
--

DROP TABLE IF EXISTS `favoritos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favoritos` (
  `receta_id` bigint NOT NULL,
  `usuario_id` bigint NOT NULL,
  PRIMARY KEY (`receta_id`,`usuario_id`),
  KEY `FKq9wif2hcqfxj8t49wo613wm0h` (`usuario_id`),
  CONSTRAINT `FKq9wif2hcqfxj8t49wo613wm0h` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKs9ncw8r5p9o200hvhjfwf5b2` FOREIGN KEY (`receta_id`) REFERENCES `recetas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favoritos`
--

LOCK TABLES `favoritos` WRITE;
/*!40000 ALTER TABLE `favoritos` DISABLE KEYS */;
INSERT INTO `favoritos` VALUES (1,2),(5,2);
/*!40000 ALTER TABLE `favoritos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredientes`
--

DROP TABLE IF EXISTS `ingredientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredientes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `nombre_ingles` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKmswp95l2180nvkxkl3hoge6fy` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredientes`
--

LOCK TABLES `ingredientes` WRITE;
/*!40000 ALTER TABLE `ingredientes` DISABLE KEYS */;
INSERT INTO `ingredientes` VALUES (1,'Espaguetis','spaghetti'),(2,'Huevo','egg'),(3,'Queso Pecorino','Pecorino cheese'),(4,'Guanciale','guanciale'),(5,'Pimienta Negra','black pepper'),(6,'Aguacate','avocado'),(7,'Cebolla','onion'),(8,'Tomate','tomato'),(9,'Lima','lime'),(10,'Cilantro','cilantro'),(11,'Patatas','potato'),(12,'Aceite de Oliva','olive oil'),(13,'Harina de trigo','flour'),(14,'Leche entera','milk'),(15,'Gambas','shrimp'),(16,'Ajo','garlic'),(17,'Nueces','walnut'),(18,'Cacahuetes','peanut'),(19,'Lechuga','lettuce'),(20,'Chocolate','chocolate'),(21,'Azúcar','sugar'),(22,'Sal','salt'),(23,'Guindilla','chili pepper'),(30,'Arroz','rice'),(31,'Pollo','chicken'),(32,'Cerdo','pork'),(33,'Jamón Ibérico','Iberian ham'),(34,'Jamón Cocido','cooked ham'),(35,'Chorizo','chorizo'),(36,'Merluza','hake'),(37,'Salmón','salmon'),(38,'Calamares','squid'),(39,'Pan de hogaza','loaf bread'),(40,'Pan de molde','sliced bread'),(41,'Pan de hamburguesa','burger bun'),(42,'Patata','potato'),(44,'Calabacín','zucchini'),(45,'Champiñones','mushrooms'),(46,'Pepino','cucumber'),(47,'Zanahoria','carrot'),(48,'Guisantes','peas'),(49,'Piña','pineapple'),(50,'Yogur natural','natural yogurt'),(51,'Queso crema','cream cheese'),(52,'Queso cheddar','cheddar cheese'),(53,'Queso mozzarella','mozzarella cheese'),(54,'Quesitos','cheese portions'),(55,'Nata para montar','whipping cream'),(56,'Cuajada','curd'),(57,'Chocolate negro','dark chocolate'),(58,'Chocolate con leche','milk chocolate'),(59,'Chocolate blanco','white chocolate'),(60,'Canela en rama','cinnamon stick'),(61,'Canela en polvo','ground cinnamon'),(62,'Levadura química','baking powder'),(63,'Vinagre de Jerez','sherry vinegar'),(64,'Salsa de soja','soy sauce'),(65,'Salsa Teriyaki','teriyaki sauce'),(66,'Salsa BBQ','BBQ sauce'),(67,'Tinta de calamar','squid ink'),(68,'Caldo de pescado','fish stock'),(69,'Caldo de carne','meat stock'),(70,'Alioli','aioli'),(71,'Mantequilla','butter'),(72,'Totopos','tortilla chips'),(73,'Tortillas de maíz','corn tortillas'),(74,'Masa de pizza','pizza dough'),(75,'Placas de lasaña','lasagna sheets'),(76,'Macarrones','macaroni'),(77,'Laurel','bay leaf'),(78,'Pimentón dulce','sweet paprika'),(79,'Vino blanco','white wine'),(80,'Albahaca fresca','fresh basil'),(81,'Pan rallado','breadcrumbs'),(82,'Alitas de pollo','chicken wings'),(83,'Lentejas','lentils'),(84,'Marisco variado','mixed seafood'),(85,'Queso rallado','grated cheese'),(86,'Pimiento verde','green pepper'),(87,'Pimiento rojo','red pepper'),(88,'Bacon','bacon'),(156,'Frutos Secos',NULL),(157,'Vinagre',NULL);
/*!40000 ALTER TABLE `ingredientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lista_compra`
--

DROP TABLE IF EXISTS `lista_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lista_compra` (
  `ingrediente_id` bigint NOT NULL,
  `usuario_id` bigint NOT NULL,
  `comprado` bit(1) NOT NULL,
  PRIMARY KEY (`ingrediente_id`,`usuario_id`),
  KEY `FK8133amxdmgpsejpbruxw9y223` (`usuario_id`),
  CONSTRAINT `FK8133amxdmgpsejpbruxw9y223` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKqii8biy06wdtd25g5lx4klh2b` FOREIGN KEY (`ingrediente_id`) REFERENCES `ingredientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lista_compra`
--

LOCK TABLES `lista_compra` WRITE;
/*!40000 ALTER TABLE `lista_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `lista_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recetas`
--

DROP TABLE IF EXISTS `recetas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recetas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` text,
  `imagen_url` varchar(255) DEFAULT NULL,
  `instrucciones` text,
  `titulo` varchar(255) DEFAULT NULL,
  `dificultad` int DEFAULT '3',
  `duracion` int DEFAULT '30',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recetas`
--

LOCK TABLES `recetas` WRITE;
/*!40000 ALTER TABLE `recetas` DISABLE KEYS */;
INSERT INTO `recetas` VALUES (1,'Auténtica receta italiana con huevo y guanciale.','https://i.ibb.co/KjzJ3CZf/carbonara.jpg','1. Pon a hervir abundante agua en una olla con un puñado de sal y cocina los espaguetis al dente.\n2. Mientras tanto, corta el guanciale (o panceta) en tiras y dóralo en una sartén sin aceite a fuego medio hasta que quede crujiente. Reserva el guanciale y la grasa que ha soltado.\n3. En un bol, bate las yemas de huevo junto con el queso Pecorino Romano rallado y añade un toque generoso de pimienta negra molida hasta formar una pasta homogénea.\n4. Escurre la pasta guardando un vaso del agua de la cocción.\n5. Agrega la pasta caliente directamente a la sartén con la grasa del guanciale (con el fuego ya apagado).\n6. Vierte la mezcla de huevo y queso sobre la pasta caliente y remueve enérgicamente de inmediato. Añade un par de cucharadas del agua de cocción retenida para crear una salsa emulsionada, cremosa y sin grumos.\n7. Incorpora el guanciale crujiente, dale una última vuelta y sirve inmediatamente con más queso por encima.','Pasta Carbonara',4,45),(2,'Salsa mexicana cremosa a base de aguacate.','https://i.ibb.co/DPs6f9yF/guacamole.jpg','1. Corta los aguacates por la mitad, retira el hueso central y extrae toda la pulpa con la ayuda de una cuchara.\n2. Coloca la pulpa en un bol o molcajete y aplástala con un tenedor dejando algunos trozos enteros para darle una textura rústica deliciosa.\n3. Añade inmediatamente el zumo de la lima recién exprimido para aportar frescor y evitar que el aguacate se oxide.\n4. Pica finamente la cebolla morada, el tomate (sin pepitas) y las hojas de cilantro fresco, e incorpóralos al bol.\n5. Si te gusta el picante, añade el chile jalapeño cortado en trozos milimétricos.\n6. Sazona con una pizca de sal al gusto y mezcla todos los ingredientes suavemente de abajo hacia arriba.\n7. Sirve al momento acompañado de un buen cuenco de totopos de maíz crujientes.','Guacamole Casero',2,15),(3,'El clásico español con o sin cebolla.','https://i.ibb.co/3yk6Mz7B/tortilla.jpg','1. Pela las patatas y córtalas en láminas finas e irregulares. Haz lo mismo con la cebolla picándola en juliana fina.\n2. Calienta abundante aceite de oliva en una sartén grande a fuego medio e introduce las patatas y la cebolla. El objetivo es pocharlas (cocinarlas despacio) durante unos 20 minutos hasta que estén muy tiernas, no fritas crujientes.\n3. Escurre bien las patatas y las cebollas con un colador para eliminar todo el exceso de aceite.\n4. En un bol amplio, bate los huevos con un punto generoso de sal hasta que queden espumosos.\n5. Añade las patatas y la cebolla calientes al bol con el huevo batido, mezcla bien y deja reposar la mezcla durante 5 o 10 minutos para que la patata absorba el huevo.\n6. Pon una sartén antiadherente a fuego alto con unas gotas del aceite reservado y vierte la mezcla.\n7. Cocina durante un par de minutos moviendo la sartén en círculos para que no se pegue. Pon un plato llano más grande sobre la sartén, dale la vuelta con un movimiento rápido y seguro, y deslízala de nuevo en la sartén.\n8. Remata los bordes con una espátula y cocina por el otro lado de 1 a 3 minutos, dependiendo de si te gusta muy cuajada o jugosa.','Tortilla de Patatas',3,25),(4,'Postre esponjoso ideal para meriendas.','https://i.ibb.co/ymNc7z0y/bizchocho.jpg','1. Precalienta el horno a 180°C y engrasa un molde con un poco de mantequilla y harina espolvoreada.\n2. En un bol grande, bate los huevos junto con el azúcar utilizando unas varillas eléctricas hasta que la mezcla blanquee y duplique su volumen.\n3. Añade el yogur natural, el aceite de girasol, el zumo de limón y la ralladura de la corteza (evitando la parte blanca para que no amargue). Mezcla todo bien.\n4. Tamiza la harina junto con la levadura química utilizando un colador fino sobre la masa húmeda.\n5. Integra los ingredientes secos de forma envolvente con una espátula, realizando movimientos suaves para que no se pierda el aire atrapado.\n6. Vierte la masa en el molde y hornea a 180°C durante aproximadamente 40-45 minutos.\n7. Evita abrir la puerta del horno durante los primeros 30 minutos. Para comprobar si está listo, pincha el centro con un palillo; si sale limpio, ya puedes retirarlo.\n8. Deja templar 10 minutos antes de desmoldar y colócalo sobre una rejilla hasta que se enfríe por completo.','Bizcocho de Chocolate',3,50),(5,'Plato tradicional de mariscos.','https://i.ibb.co/gFRkTPYW/gambas.jpg','1. Pela las gambas por completo retirando las cabezas y las pieles (puedes guardarlas para hacer un caldo en otra ocasión).\n2. Corta los dientes de ajo en láminas finas y corta la guindilla cayena en un par de aros.\n3. En una cazuela de barro (preferiblemente) o una sartén pequeña, vierte un buen chorro de aceite de oliva virgen extra hasta cubrir generosamente el fondo.\n4. Añade los ajos y la guindilla al aceite en frío y ponlo a fuego medio-bajo para que el aceite absorba el sabor del ajo lentamente sin quemarlo.\n5. Cuando los ajos comiencen a bailar y a tomar un color dorado suave, sube el fuego al máximo e introduce las gambas bien secas.\n6. Sazona con una pizca de sal marina y cocina las gambas durante apenas un minuto por cada lado hasta que cambien de color a rosado y queden opacas.\n7. Retira inmediatamente del fuego para que no se queden secas, espolvorea un poco de perejil fresco picado y sirve directamente mientras el aceite aún burbujea.','Gambas al Ajillo',5,20),(6,'Ensalada fresca con un toque crujiente.','https://i.ibb.co/whpNLmgH/ensalada.jpg','1. Lava muy bien las hojas de lechuga o brotes tiernos, sécalas completamente (para que el aliño se adhiera bien) y colócalas como cama en una ensaladera grande.\n2. Corta los tomates maduros en gajos medianos y el pepino en rodajas finas.\n3. Corta la cebolla morada en juliana muy fina e incorpórala junto al tomate y el pepino al bol.\n4. Trocea el queso feta en dados uniformes y añádelo con cuidado junto con las aceitunas negras (preferiblemente de Aragón o Kalamata).\n5. Prepara la vinagreta en un frasco pequeño aparte mezclando el aceite de oliva virgen extra, el vinagre de vino blanco, la sal y una cucharadita de orégano seco.\n6. Agrita bien el frasco para emulsionar el aliño.\n7. Vierte la vinagreta sobre la ensalada justo en el momento de ir a servirla en la mesa y mezcla suavemente para no romper el queso feta.','Ensalada de Frutos Secos',1,10),(7,'Un postre espectacular que no necesita horno, combinando capas de chocolate negro, con leche y blanco.','https://i.ibb.co/LDBBtBC9/tarta-de-tres-chocolates.jpg','1. Derretir el chocolate negro con la nata y la leche a fuego lento, añadir el sobre de cuajada y verter en el molde base. Dejar enfriar hasta que cuaje. 2. Repetir exactamente el mismo proceso con el chocolate con leche y verterlo muy suavemente ayudándote de una cuchara sobre la capa negra para que no se mezclen. 3. Hacer el mismo procedimiento con el chocolate blanco para la capa final. Refrigerar en la nevera un mínimo de 4 horas antes de desmoldar.','Tarta de Tres Chocolates',3,45),(8,'Rebanadas de pan empapadas en leche aromatizada, rebozadas en huevo y fritas. El dulce estrella de la Semana Santa.','https://i.ibb.co/fVmNmpRw/torrijas.jpg','1. Calentar la leche en un cazo con el azúcar, una rama de canela y la piel de un limón hasta que rompa a hervir. Retirar del fuego y dejar infusionar hasta que esté templada. 2. Cortar el pan en rodajas gruesas (de unos 2 cm) y colocarlas en una fuente profunda; verter la leche por encima hasta que queden bien empapadas. 3. Pasar cada rodaja con cuidado por huevo batido y freírlas en una sartén con abundante aceite caliente hasta que estén doradas por ambos lados. Escurrir en papel absorbente y espolvorear con una mezcla de azúcar y canela en polvo.','Torrijas Tradicionales de Leche',2,30),(9,'Un bizcocho de chocolate denso, húmedo por dentro y con el toque crujiente de los frutos secos.','https://i.ibb.co/Pszh1v0d/brownie-de-chocolate-con-nueces.jpg','1. Derretir el chocolate troceado junto con la mantequilla al baño María o en el microondas a intervalos cortos de 30 segundos para evitar que se queme. 2. En un bol aparte, batir enérgicamente los huevos con el azúcar hasta que doblen su volumen, e incorporar el chocolate derretido templado. 3. Añadir la harina tamizada poco a poco con movimientos envolventes y, por último, agregar las nueces picadas. 4. Verter la masa en un molde rectangular forrado con papel de horno y hornear a 180°C durante 25 minutos, buscando que el interior quede ligeramente húmedo.','Brownie de Chocolate con Nueces',2,35),(10,'La famosa tarta de queso de San Sebastián: tostada por fuera, pero con un corazón cremoso que casi se desborda.','https://i.ibb.co/N6DpF9vG/tarta-de-queso-al-horno.jpg','1. En un bol grande, añadir el queso crema, los huevos enteros, el azúcar blanco, la nata líquida para montar y una cucharada rasa de harina de trigo. Batir todo con varillas eléctricas hasta lograr una crema fina y sin grumos. 2. Humedecer un trozo grande de papel de horno, arrugarlo para que sea moldeable y forrar con él las paredes y base de un molde desmontable. 3. Verter la mezcla líquida y hornear a 210°C durante unos 40 minutos. Debe quedar muy tostada por fuera y con un centro ligeramente tembloroso al mover el molde.','Tarta de Queso al Horno',3,50),(11,'Desayuno moderno, saludable, vistoso y muy rico en grasas buenas.','https://i.ibb.co/yBVCXdp4/tostadas-con-aguacate-y-huevo.png','1. Tostar las rebanadas de pan (preferiblemente de hogaza o integral) en una tostadora o sartén hasta que queden bien crujientes y doradas. 2. Cortar el aguacate por la mitad, retirar el hueso, extraer la pulpa con una cuchara y machacarla en un plato con la ayuda de un tenedor junto a un chorrito de jugo de lima, sal y un hilo de aceite de oliva. 3. Cocinar un huevo poché introduciéndolo con cuidado en una olla con agua a punto de hervir y un chorrito de vinagre durante exactamente 3 minutos. 4. Untar el aguacate sobre el pan tostado, coronar con el huevo poché caliente y espolvorear pimienta negra recién molida.','Tostada de Aguacate y Huevo Poché',2,15),(12,'El rey de los Brunch. Huevos escalfados sobre pan tostado, bacon y bañados en salsa holandesa.','https://i.ibb.co/ccqGXncV/huevos-benedictinos.jpg','1. Tostar los panes de tipo muffin inglés partidos por la mitad y, de forma paralela, dorar las lonchas de bacon o jamón en una sartén antiadherente sin añadir grasa extra. 2. Escalfar los huevos sumergiéndolos individualmente en agua caliente con un chorro de vinagre blanco durante 3 minutos hasta que la clara blanquee y cubra la yema. 3. Para la salsa holandesa, batir las yemas de huevo al baño María e ir vertiendo mantequilla clarificada derretida poco a poco en forma de hilo continuo hasta lograr una emulsión espesa, sazonando con sal y unas gotas de limón. 4. Montar el plato colocando el pan, el bacon crujiente, el huevo escalfado y bañando generosamente con la salsa holandesa tibia.','Huevos Benedictinos',4,25),(13,'Una sartén de patatas fritas caseras cubiertas con huevos fritos con la yema líquida y virutas de jamón ibérico.','https://i.ibb.co/HDFL4Pzj/juevos-rotos-con-jamon.png','1. Pelar las patatas y cortarlas en rodajas finas de estilo panadera o en bastones tradicionales. Lavarlas para quitar el almidón y secarlas muy bien con un paño limpio. 2. Freír las patatas en una sartén con abundante aceite de oliva a fuego medio hasta que estén tiernas, subiendo el fuego al final para que se doren externamente. Retirar y sazonar con sal. 3. En el mismo aceite bien caliente, freír los huevos de uno en uno con una espumadera, procurando que la puntilla quede crujiente y la yema completamente líquida. 4. Emplatar colocando una cama generosa de patatas, los huevos fritos encima y las virutas de jamón ibérico. Romper los huevos con la ayuda de dos cuchillos justo antes de servir para que la yema empape las patatas.','Huevos Rotos con Jamón',2,25),(14,'Variante de la paella cocinada con tinta de calamar, sirviéndose tradicionalmente con salsa alioli casera.','https://i.ibb.co/k2NmX0FP/arroz-negro-con-alioli.jpg','1. Picar finamente la cebolla, el pimiento verde y los dientes de ajo. Sofreír todo lentamente en una paella con un chorro de aceite de oliva hasta que la verdura esté transparente. 2. Limpiar los calamares, cortarlos en dados e incorporarlos al sofrito, cocinándolos hasta que pierdan el agua y empiecen a dorarse. 3. Añadir el arroz redondo y removerlo durante un minuto para nacararlo junto a la tinta de calamar diluida en un poco de agua. 4. Verter el caldo de pescado (fumet) hirviendo de forma homogénea y cocinar a fuego fuerte durante 8 minutos, bajando luego a fuego lento durante 10 minutos más. Dejar reposar tapado y servir con una generosa cucharada de alioli casero.','Arroz Negro con Alioli',4,40),(15,'Anillas de calamar rebozadas en una masa ligera y fritos hasta quedar dorados. Ideal con un chorrito de limón.','https://i.ibb.co/n8R5gNxD/calamares-a-la-romana.jpg','1. Limpiar por completo los calamares retirando los interiores y la piel exterior. Cortar el cuerpo en anillas uniformes de aproximadamente un centímetro de grosor y secarlas minuciosamente con papel absorbente de cocina. 2. Disponer las anillas en un plato con harina de trigo y sacudirlas ligeramente en un colador para eliminar todo el exceso de polvo. 3. Pasar los calamares enharinados por un cuenco con huevo batido y una pizca de sal, asegurándose de que queden completamente impregnados. 4. Freír en tandas pequeñas en una sartén con abundante aceite de oliva muy caliente durante solo 1 minuto por lado para evitar que se endurezcan. Servir calientes con rodajas de limón fresco.','Calamares a la Romana',2,20),(16,'Guarnición o tapa tradicional facilísima de hacer, sabrosa y baja en calorías.','https://i.ibb.co/fV9r5B34/champi-ones-al-ajillo.jpg','1. Limpiar la superficie de los champiñones frescos con un paño húmedo o cepillo para retirar los restos de tierra (evitando sumergirlos en agua), cortar la base del tallo y laminarlos en piezas medianas. 2. En una sartén amplia con una buena cantidad de aceite de oliva virgen extra, dorar los dientes de ajo cortados en láminas junto con las rodajas de guindilla al gusto. 3. Justo antes de que el ajo cambie excesivamente de color, incorporar los champiñones a la sartén. 4. Subir la potencia al máximo y saltear a fuego vivo durante unos 8 o 10 minutos para que se evaporen sus jugos rápidamente. Espolvorear abundante perejil fresco picado y sal en escamas al retirar del fuego.','Champiñones al Ajillo',1,15),(17,'La sopa fría de tomate por excelencia. Vitamina pura para refrescar los días soleados.','https://i.ibb.co/tP4jDk8R/gazpacho-andaluz.jpg','1. Lavar a conciencia los tomates maduros (tipo pera), el pimiento verde y el pepino. Pelar el pepino parcialmente y retirar el germen interior del diente de ajo para suavizar el sabor. 2. Trocear todos los vegetales y ponerlos en el vaso de una batidora potente o procesador de alimentos junto a un trozo de pan duro ablandado en agua. 3. Triturar a la máxima velocidad durante varios minutos hasta conseguir una textura fina y líquida. 4. Sin apagar la batidora, verter el aceite de oliva virgen extra en forma de hilo continuo para conseguir que la mezcla emulsione, añadiendo el vinagre de Jerez y la sal al gusto. Pasar por un colador chino para retirar pieles o pepitas restantes y dejar enfriar en la nevera.','Gazpacho Andaluz',1,15),(18,'Una crema suave, de textura sedosa e ideal para una cena ligera.','https://i.ibb.co/N2Fc9sj2/crema-de-calabacin-y-quesitos.jpg','1. Lavar los calabacines, retirar los extremos y trocearlos en rodajas medianas sin quitarles la piel para mantener un color verde vivo. Pelar y trocear la patata y la cebolla blanca. 2. Introducir todos los vegetales en una olla, verter un chorrito de aceite de oliva, cubrir con agua o caldo de verduras limpio y añadir una cucharadita de sal. 3. Tapar la olla y cocinar a fuego medio durante 20-25 minutos comprobando que la patata esté completamente blanda. 4. Retirar un poco del caldo sobrante para controlar la densidad, incorporar los quesitos en porciones y procesar todo con una batidora de mano hasta conseguir una crema sedosa, homogénea y lisa.','Crema de Calabacín y Quesitos',2,25),(19,'Uno de los tacos mexicanos más famosos. Carne de cerdo adobada combinada con el toque dulce de la piña.','https://i.ibb.co/j9nwMy70/tacos-al-pastor.jpg','1. Preparar el adobo licuando chiles guajillo hidratados, pasta de achiote, vinagre blanco, ajo, cebolla, comino y trozos de piña. Marinar la carne de cerdo cortada en filetes finos con esta mezcla durante un mínimo de 4 horas en refrigeración. 2. Calentar una plancha de hierro o sartén a fuego muy alto con un poco de aceite y cocinar la carne marinada hasta que los bordes queden crujientes y ligeramente chamuscados; picarla finamente tras cocinarla. 3. En la misma plancha, asar rodajas de piña natural hasta que caramelicen sus azúcares y trocearlas. 4. Calentar tortillas de maíz finas, armar los tacos con la carne picada, los trozos de piña, cebolla blanca picada cruda y hojas de cilantro fresco.','Tacos al Pastor',3,35),(20,'Trocitos de pollo jugosos salteados con una salsa japonesa dulce y brillante.','https://i.ibb.co/W4yXMQN0/pollo-terayaki.jpg','1. Limpiar las pechugas de pollo retirando los restos de grasa y cortarlas en dados uniformes del tamaño de un bocado. Sazonar ligeramente con sal y pimienta. 2. En una sartén amplia o wok con un chorro de aceite vegetal, dorar los trozos de pollo a fuego muy fuerte hasta que se sellen todas sus caras exteriores. 3. Bajar la intensidad del fuego a nivel medio y verter la salsa teriyaki dulce (mezcla de salsa de soja, mirin, sake y azúcar). 4. Cocinar removiendo constantemente de forma envolvente hasta que la salsa reduzca, espese notablemente y cree una laca brillante adherida uniformemente al pollo. Servir caliente espolvoreado con semillas de sésamo sobre una base de arroz blanco.','Pollo Teriyaki',2,20),(21,'Fideos asiáticos salteados a fuego vivo en un wok con verduras crujientes.','https://i.ibb.co/Wp2J08yb/tallarines-fritos-con-verduras.jpg','1. Hervir los fideos o noodles asiáticos de trigo en una olla con agua siguiendo estrictamente los minutos indicados por el fabricante; escurrir, enfriar con agua fría y añadir unas gotas de aceite de sésamo para que no se peguen. 2. Cortar pimiento rojo, pimiento verde, cebolla, zanahoria y calabacín en bastones muy finos estilo juliana. 3. Calentar un wok a fuego extremo con un hilo de aceite e incorporar las verduras, salteándolas continuamente durante 4 minutos para mantener una textura crujiente (al dente). 4. Incorporar los noodles al wok con las verduras, añadir un buen chorro de salsa de soja y una pizca de azúcar, y saltear todo junto a fuego vivo dos minutos para integrar sabores.','Noodles Fritos con Verduras',2,20),(22,'El famoso sándwich de tres pisos de los hoteles, tostado y cortado en triángulos.','https://i.ibb.co/QFX6fs8D/sandwich-club.jpg','1. Tostar tres rebanadas de pan de molde en una plancha untándolas con un poco de mantequilla hasta que queden firmes y crujientes. 2. Cocinar un filete fino de pechuga de pollo sazonado a la plancha y, al mismo tiempo, dorar las lonchas de bacon en su propia grasa hasta que queden quebradizas. 3. Untar una capa fina de mayonesa casera en una de las caras de las tres rebanadas de pan. 4. Montar el piso inferior colocando hojas de lechuga, rodajas finas de tomate y el pollo a la plancha. Colocar la segunda rebanada de pan encima, añadir una loncha de queso y el bacon crujiente, y cerrar con la tercera pieza de pan. Cortar el sándwich en cuatro triángulos sujetándolos con palillos.','Club Sandwich',1,15),(23,'El clásico arroz frito al estilo de los restaurantes chinos, suelto y muy colorido.','https://i.ibb.co/VcTRQqdK/arroz-tres-delicias.jpg','1. Cocer arroz de grano largo en abundante agua con sal durante unos 13 minutos. Escurrirlo por completo, extenderlo en una bandeja y dejar que se enfríe completamente (es mejor si se usa arroz del día anterior). 2. Batir los huevos y preparar una tortilla francesa muy fina en una sartén amplia, retirarla y cortarla en cuadrados pequeños. 3. En un wok con aceite caliente, saltear brevemente los guisantes tiernos, la zanahoria cocida cortada en dados pequeños y los taquitos de jamón cocido. 4. Añadir el arroz frío al wok, subir el fuego al máximo y saltear removiendo rápidamente; incorporar la tortilla y un hilo de salsa de soja ligera antes de retirar del fuego.','Arroz Tres Delicias',2,25),(24,'Una bandeja perfecta para compartir. Totopos crujientes cubiertos de carne picada sazonada y una melosa salsa de queso fundido.','https://i.ibb.co/N29nxySZ/nachos-con-queso-y-carne.jpg','1. Picar la cebolla y el tomate en cubos pequeños. En una sartén con aceite, sofreír la cebolla, incorporar la carne picada sazonada con comino, ajo y chile, y añadir el tomate dejando cocinar hasta obtener una carne jugosa pero sin líquido suelto. 2. En una fuente amplia o bandeja apta para el horno, esparcir los totopos de maíz formando una base uniforme y compacta. 3. Distribuir la carne picada cocinada de manera homogénea sobre los totopos para que cubra la mayoría de las piezas. 4. Esparcir una cantidad abundante de queso cheddar rallado por encima y llevar al horno en modo grill o al microondas durante un par de minutos hasta que el queso esté completamente fundido e hile.','Nachos Con Queso y Carne',2,15),(25,'Crujientes alitas horneadas o fritas, impregnadas en una salsa barbacoa dulce y ahumada.','https://i.ibb.co/bRDvVXh1/alitas-de-pollo.jpg','1. Limpiar las alitas de pollo partiéndolas por la articulación y desechando las puntas. Secarlas bien y sazonarlas uniformemente en un bol con sal, pimienta negra, ajo en polvo y pimentón dulce. 2. Disponer las alitas de forma ordenada en una bandeja provista de papel de hornear y asarlas en el horno precalentado a 200°C durante unos 30 minutos, dándoles la vuelta a mitad del proceso para dorar ambas caras. 3. Sacar la bandeja con cuidado, trasladar las alitas a un bol limpio y bañarlas generosamente en salsa barbacoa mezclada con una cucharadita de miel. 4. Volver a colocar las alitas impregnadas en la bandeja y hornear durante 10 minutos más a 220°C hasta que la salsa burbujee y se aprecie caramelizada.','Alitas de Pollo Barbacoa',2,40),(26,'La reina de las pizzas. Simple, fina, con el queso fundido y un toque fresco de albahaca.','https://i.ibb.co/Cs8VKqnd/pizza-margarita.jpg','1. Estirar la masa de pizza casera sobre una superficie enharinada con la ayuda de las manos o un rodillo hasta lograr el grosor deseado, trasladándola a una bandeja para horno. 2. Extender una capa fina y uniforme de tomate triturado natural sobre la base utilizando el dorso de una cuchara, dejando libres los bordes exteriores, y sazonar con sal y orégano seco. 3. Cortar la mozzarella fresca en rodajas o desmenuzarla con los dedos, distribuyéndola de forma equilibrada por la superficie de la pizza. 4. Regar con un hilo de aceite de oliva virgen extra y hornear a la máxima temperatura disponible en tu horno (250°C) durante 10-12 minutos hasta que la masa esté crujiente y el queso burbujee. Coronar con hojas de albahaca fresca al salir.','Pizza Margherita Casera',3,25),(27,'Esponjoso y alto, el típico bizcocho cuyas medidas se toman con el vaso del yogur.','https://i.ibb.co/998csNp5/bizcocho-de-yogur.jpg','1. Precalentar el horno a 180°C con calor arriba y abajo y engrasar un molde de corona o rectangular con un poco de mantequilla y harina. 2. En un bol espacioso, batir los huevos enteros junto con el azúcar blanco utilizando varillas hasta lograr una consistencia blanquecina y espumosa. 3. Añadir el yogur natural (cuyo vaso servirá como herramienta de medida para el resto de pasos), el aceite de girasol y la ralladura de limón, mezclando suavemente. 4. Tamizar la harina junto con el sobre de levadura química en polvo e incorporarla al bol con la ayuda de una espátula mediante movimientos envolventes. Verter en el molde y hornear durante 40-45 minutos sin abrir la puerta.','Bizcocho de Yogur Clásico',2,45),(28,'Postre cremoso de arroz aromatizado con canela y cítricos.','https://i.ibb.co/YJvttRm/arroz-con-leche.jpg','1. En una olla de base ancha, verter la leche entera junto con la ramita de canela y las pieles superficiales de un limón y una naranja (evitando la parte blanca amarga). Llevar a ebullición suave. 2. Cuando la leche empiece a hervir, incorporar el arroz redondo previamente enjuagado bajo el grifo y bajar la potencia del fuego al mínimo. 3. Cocinar el arroz de manera pausada durante unos 40 o 45 minutos, removiendo la mezcla cada pocos minutos con una cuchara de madera para liberar el almidón y ganar cremosidad. 4. Añadir el azúcar blanco cuando queden solo 5 minutos de cocción, mezclar bien, retirar del fuego, extraer los cítricos y la canela, y dejar enfriar antes de espolvorear canela molida.','Arroz Con Leche',2,50),(29,'El postre tradicional de la abuela con un suave baño de caramelo líquido.','https://i.ibb.co/hxmN171Q/flan-de-huevo.jpg','1. Colocar tres cucharadas de azúcar y una de agua en una sartén a fuego medio-alto para preparar un caramelo dorado; verterlo inmediatamente en el fondo de un molde grande para flan o flaneras individuales. 2. En un bol profundo, batir los huevos enteros junto con el azúcar y la leche entera a temperatura ambiente utilizando unas varillas manuales de forma suave para evitar introducir burbujas de aire en la mezcla. 3. Filtrar la mezcla pasándola por un colador fino directamente sobre el molde caramelizado. 4. Colocar el molde dentro de una bandeja honda con agua caliente que cubra la mitad del recipiente (baño María) y hornear a 160°C durante 55-60 minutos hasta que al pincharlo se note cuajado.','Flan de Huevo Casero',3,60),(30,'Desayuno esponjoso perfecto para los domingos, ideal con miel o sirope.','https://i.ibb.co/1trKZhMj/pankakes.jpg','1. En un cuenco grande, mezclar y tamizar los componentes secos de la receta: la harina de trigo, el azúcar blanco, una pizca de sal y la levadura química en polvo. 2. En otro recipiente diferente, batir el huevo entero junto con la leche y la mantequilla previamente derretida y templada. 3. Unir ambas preparaciones vertiendo los líquidos sobre los secos, batiendo con unas varillas de mano únicamente lo justo para eliminar los grumos gruesos de la masa. 4. Calentar una sartén antiadherente pequeña a fuego medio con una pizca de mantequilla; verter un cucharón de masa, esperar a que salgan burbujas en la superficie, darle la vuelta y dorar el otro lado por un minuto.','Tortitas Americanas',1,20),(31,'Filetes de pescado blanco limpios, rebozados y fritos de forma esponjosa.','https://i.ibb.co/gbycTbND/merluza-a-la-romana.jpg','1. Limpiar las rodajas o lomos de merluza fresca retirando escamas o espinas visibles, secar a fondo cada pieza con papel de cocina y sazonar con sal fina por ambos lados. 2. Disponer harina de trigo en un plato llano y rebozar la merluza en ella, dándole ligeros toques con las manos para asegurar que caiga toda la harina sobrante y quede una película fina. 3. Batir los huevos en un cuenco amplio y sumergir el pescado enharinado para que se impregne bien del huevo batido por completo. 4. Freír la merluza en una sartén con abundante aceite de oliva caliente a fuego medio-alto durante 2 o 3 minutos por lado hasta que el rebozado luzca hinchado, dorado y esponjoso.','Merluza a la Romana',2,20),(32,'Plato saludable, rico en Omega-3 y con un horneado limpio y rápido.','https://i.ibb.co/mr2f7WZv/salmon-con-patatas.jpg','1. Pelar las patatas y las cebollas cortándolas en rodajas finas de medio centímetro, simulando un corte de patatas panaderas. 2. Colocar estos vegetales en una fuente apta para el horno creando una cama uniforme, sazonar con sal, regar con un buen chorro de aceite de oliva y verter medio vaso de vino blanco. Hornear a 180°C durante 15 minutos de forma previa. 3. Retirar la bandeja del horno temporalmente, colocar los lomos de salmón fresco sazonados con sal y pimienta encima de las patatas medio cocinadas y añadir rodajas de limón sobre el pescado. 4. Volver a introducir en el horno a 190°C durante un tiempo estimado de 10 o 12 minutos cuidando que el pescado quede jugoso.','Salmón al Horno con Patatas',2,30),(33,'Bolitas de carne tiernas sumergidas en una salsa casera reducida de cebolla.','https://i.ibb.co/zWH2WntB/albondigas.jpg','1. Mezclar en un bol grande la carne picada con el huevo, el diente de ajo y el perejil finamente picados, y una cucharada de pan rallado empapado en leche. Amasar bien, formar esferas uniformes, pasarlas por harina y dorarlas ligeramente en una sartén con aceite caliente; reservar las albóndigas en un plato. 2. En la misma sartén aprovechando los jugos de la carne, añadir dos cebollas grandes picadas finamente y pochar a fuego lento hasta que se caramelicen y oscurezcan. 3. Añadir una cucharadita de harina al sofrito, verter un vaso de vino blanco dejando evaporar el alcohol, añadir caldo de carne y triturar la salsa si se prefiere una textura fina. 4. Reintroducir las albóndigas en la salsa y cocinar tapado durante 15 minutos.','Albóndigas en Salsa de Cebolla',3,45),(34,'La reina del fast food hecha en casa con ingredientes de calidad.','https://i.ibb.co/4B5gtpy/hamburguesa-con-patatas.jpg','1. Dividir la carne picada sazonada en porciones, darles forma circular compacta presionando ligeramente y sazonar con sal y pimienta. Cocinar en una plancha caliente al punto de cocción preferido, colocando una loncha de queso encima un minuto antes de retirar para que funda. 2. Pelar y cortar las patatas en bastones alargados, lavarlas para quitar el almidón y freírlas en abundante aceite caliente hasta que consigan un exterior crujiente. 3. Tostar levemente las caras internas del pan de hamburguesa en una sartén con un toque de mantequilla para impermeabilizarlo. 4. Ensamblar colocando la base de pan, rodajas de tomate maduro, hojas limpias de lechuga, la carne con el queso fundido y las salsas de tu preferencia.','Hamburguesa Completa con Patatas',2,25),(35,'Una forma rápida y llena de sabor de cocinar el pollo jugoso.','https://i.ibb.co/pBCSCGYP/pechuga-de-pollo-al-ajillo.jpg','1. Cortar las pechugas de pollo deshuesadas en trozos medianos o tiras gruesas del tamaño de un bocado regular; sazonar con sal y pimienta. 2. En una sartén amplia con un fondo generoso de aceite de oliva virgen extra, incorporar una buena cantidad de dientes de ajo cortados en láminas delgadas junto con una guindilla seca si se desea un punto picante. 3. Dorar los ajos a fuego medio vigilando estrechamente que no se quemen, retirarlos de la sartén con una espumadera y reservar en un plato. 4. Subir el fuego al máximo, añadir el pollo a la sartén cocinándolo hasta que esté dorado y crujiente por fuera; reincorporar los ajos fritos, verter un chorrito de vino blanco reduciendo el líquido un minuto y finalizar con perejil fresco.','Pechuga de Pollo al Ajillo',1,20),(36,'Crema fría de tomate, densa y perfecta para los meses de calor.','https://i.ibb.co/MkgMnw5d/salmorejo.jpg','1. Lavar los tomates maduros de tipo pera, retirarles la zona del pedúnculo y trocearlos. Introducirlos en el vaso de la batidora junto con el diente de ajo pelado (al que se le puede quitar el germen interno). 2. Procesar a alta velocidad durante unos minutos hasta obtener un puré de tomate líquido y uniforme. 3. Incorporar el pan del día anterior desmigado en trozos dentro del puré de tomate y dejar reposar cinco minutos para que la miga se ablande por completo; volver a triturar intensamente. 4. Con el motor en marcha a velocidad constante, verter el aceite de oliva virgen extra poco a poco para lograr que la mezcla emulsione y gane su característica textura espesa y cremosa, corrigiendo el punto de sal y vinagre al final.','Salmorejo Cordobés',2,20),(37,'Crujientes por fuera y con una bechamel que se deshace en la boca.','https://i.ibb.co/VWpJzBCY/croquetas.jpg','1. En una sartén con un chorrito de aceite, saltear brevemente el jamón ibérico picado en cubos muy pequeños para que libere su aroma. Reservar. 2. En un cazo, derretir mantequilla o calentar aceite de oliva, añadir la harina de trigo removiendo de forma continua durante un par de minutos a fuego medio para cocinarla. 3. Ir vertiendo la leche entera caliente de manera gradual y paulatina sin dejar de batir con unas varillas manuales para evitar grumos, cocinando la bechamel hasta que espese e incorporando el jamón al final. Dejar enfriar la masa tapada con film. 4. Tomar porciones de masa fría, modelar las croquetas, pasarlas por harina, huevo batido, pan rallado fino y freír en aceite bien caliente.','Croquetas de Jamón Ibérico',4,60),(38,'Un plato reconfortante, cargado de hierro y muy saludable.','https://i.ibb.co/Wpqk1277/lentejas.png','1. En una olla grande de base profunda, introducir las lentejas secas previamente lavadas y escurridas (no requieren remojo previo si son de tipo pardina). 2. Añadir todos los vegetales cortados en crudo: la patata chascada, la zanahoria cortada en rodajas finas, la cebolla picada, el pimiento verde troceado y los dientes de ajo enteros con un corte. 3. Agregar una hoja de laurel seca, una cucharadita de pimentón dulce de buena calidad, un chorrito de aceite de oliva virgen extra y una pizca de sal fina. 4. Cubrir todos los ingredientes con agua fría asegurando que el nivel supere unos tres dedos los alimentos. Llevar a ebullición, tapar y cocinar a fuego lento durante 40-50 minutos.','Lentejas Estofadas con Verduras',2,50),(39,'Arroz seco e impregnado con todo el sabor del mar Mediterráneo.','https://i.ibb.co/zTRGnnxK/paella.jpg','1. En una paella espaciosa con aceite de oliva, dorar a fuego medio los langostinos, las gambas enteras y las cigalas durante un par de minutos por lado; retirar el marisco y reservar en un plato. 2. En el mismo aceite impregnado, cocinar las anillas de calamar troceadas y añadir un sofrito lento a base de cebolla fina, ajo picado, pimiento rojo y tomate maduro triturado hasta que reduzca por completo. 3. Incorporar el arroz redondo distribuyéndolo por la paella y sofreírlo durante un minuto entero para nacarar el grano. 4. Verter el caldo de pescado (fumet) hirviendo aromatizado con hebras de azafrán, recolocar los mariscos arriba y cocinar a fuego fuerte 8 minutos, bajando el fuego 10 minutos más hasta secar.','Paella de Marisco',4,45),(40,'Capas de pasta intercaladas con una jugosa salsa boloñesa y bechamel.','https://i.ibb.co/B5rFHqgR/lasagna.png','1. Preparar el relleno boloñesa sofriendo cebolla, ajo y zanahoria picados, añadiendo la carne picada sazonada hasta dorar e incorporando tomate frito para dejar reducir la salsa de forma pausada. 2. Elaborar una bechamel ligera derritiendo mantequilla en un cazo, añadiendo la misma proporción de harina y vertiendo leche entera caliente poco a poco con varillas hasta lograr una salsa fina y cremosa. 3. Hidratar las placas de pasta para lasaña en agua caliente según las instrucciones de su caja. 4. En una fuente refractaria engrasada, montar el plato alternando de manera ordenada capas de placas de pasta, carne boloñesa y toques de bechamel. Finalizar cubriendo con la bechamel restante, espolvorear queso rallado para gratinar y hornear a 200°C durante 20 minutos.','Lasaña de Carne Picada',3,50),(41,'Un clásico casero que nunca falla, ideal para el día a día.','https://i.ibb.co/N29rMfh3/macarrones.jpg','1. Llenar una olla grande con abundante agua, añadir una cucharada de sal y llevar a ebullición alta; introducir los macarrones y cocinarlos siguiendo el tiempo estricto del empaque para obtener una pasta al dente. Escurrir y reservar. 2. En una sartén amplia, calentar un chorrito de aceite de oliva y pochar la cebolla cortada en dados finos hasta que esté blanda y transparente. 3. Añadir el chorizo cortado en rodajas delgadas o cubitos pequeños al sofrito de cebolla, removiendo durante unos minutos para que suelte su grasa natural y aporte color al aceite. 4. Incorporar la salsa de tomate frito a la sartén mezclando bien los sabores; verter los macarrones escurridos sobre la salsa caliente y remover de forma envolvente un minuto.','Macarrones con Tomate y Chorizo',1,25);
/*!40000 ALTER TABLE `recetas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recetas_ingredientes`
--

DROP TABLE IF EXISTS `recetas_ingredientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recetas_ingredientes` (
  `receta_id` bigint NOT NULL,
  `ingrediente_id` bigint NOT NULL,
  KEY `FKjtpausitdu3m1rqjvym2ooqfb` (`ingrediente_id`),
  KEY `FKt1aceirr9btp3yigo2hn0i9s8` (`receta_id`),
  CONSTRAINT `FKjtpausitdu3m1rqjvym2ooqfb` FOREIGN KEY (`ingrediente_id`) REFERENCES `ingredientes` (`id`),
  CONSTRAINT `FKt1aceirr9btp3yigo2hn0i9s8` FOREIGN KEY (`receta_id`) REFERENCES `recetas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recetas_ingredientes`
--

LOCK TABLES `recetas_ingredientes` WRITE;
/*!40000 ALTER TABLE `recetas_ingredientes` DISABLE KEYS */;
INSERT INTO `recetas_ingredientes` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(7,57),(7,58),(7,59),(7,55),(7,14),(7,56),(7,21),(8,14),(8,60),(8,39),(8,2),(8,12),(8,21),(9,13),(9,21),(9,71),(9,2),(9,57),(9,17),(10,51),(10,55),(10,21),(10,2),(10,13),(11,39),(11,6),(11,2),(11,12),(12,40),(12,88),(12,2),(12,71),(13,42),(13,2),(13,33),(13,12),(14,38),(14,30),(14,67),(14,68),(14,70),(15,38),(15,13),(15,2),(15,12),(16,45),(16,16),(16,23),(16,12),(17,8),(17,86),(17,46),(17,16),(17,12),(17,63),(18,44),(18,42),(18,7),(18,54),(19,32),(19,73),(19,49),(19,7),(19,10),(20,31),(20,65),(20,12),(21,86),(21,47),(21,7),(21,44),(21,64),(22,40),(22,31),(22,88),(22,52),(22,19),(22,8),(23,30),(23,2),(23,48),(23,34),(23,47),(23,64),(24,72),(24,52),(24,7),(24,8),(25,82),(25,66),(26,74),(26,8),(26,53),(26,80),(4,50),(27,50),(4,21),(27,21),(4,13),(27,13),(4,2),(27,2),(4,62),(27,62),(28,30),(28,14),(28,21),(28,60),(29,2),(29,14),(29,21),(30,13),(30,21),(30,62),(30,14),(30,2),(30,71),(31,36),(31,13),(31,2),(31,12),(32,37),(32,42),(32,7),(32,12),(33,2),(33,81),(33,13),(33,7),(33,69),(33,79),(34,41),(34,19),(34,8),(34,42),(34,12),(35,31),(35,16),(35,12),(35,79),(36,8),(36,39),(36,16),(36,12),(36,63),(37,13),(37,71),(37,14),(37,33),(37,2),(37,81),(38,83),(38,42),(38,47),(38,7),(38,86),(38,16),(38,77),(38,78),(39,30),(39,84),(39,8),(39,87),(39,16),(39,68),(39,12),(40,75),(40,8),(40,7),(40,13),(40,14),(40,71),(40,85),(41,76),(41,7),(41,35),(41,8),(41,12),(2,22),(3,22),(5,12),(5,22),(5,23),(6,12),(6,22),(2,22),(3,22),(5,12),(5,22),(5,23),(6,12),(6,22),(2,22),(3,22),(5,12),(5,22),(5,23),(6,12),(6,22),(3,12),(3,42),(3,2),(3,7),(2,6),(2,8),(2,7),(2,10),(2,9),(6,19),(6,156),(6,157);
/*!40000 ALTER TABLE `recetas_ingredientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `fecha_registro` datetime(6) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password_hash` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKkfsp0s1tflm1cwlj8idhqsad0` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'sql@test.com','2026-04-19 15:50:01.000000','Prueba SQL','1234'),(2,'luis@luis.luis','2026-04-19 14:39:45.213605','Luis','aaaaaa'),(3,'a@a.a','2026-04-25 15:27:44.065784','a','no');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_alergias`
--

DROP TABLE IF EXISTS `usuarios_alergias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_alergias` (
  `usuario_id` bigint NOT NULL,
  `alergia_id` bigint NOT NULL,
  KEY `FKr4pp4967n8nsnp9edisd1kfv5` (`alergia_id`),
  KEY `FKdm33ixwp0pinanwtkhosgkqlf` (`usuario_id`),
  CONSTRAINT `FKdm33ixwp0pinanwtkhosgkqlf` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKr4pp4967n8nsnp9edisd1kfv5` FOREIGN KEY (`alergia_id`) REFERENCES `alergias` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_alergias`
--

LOCK TABLES `usuarios_alergias` WRITE;
/*!40000 ALTER TABLE `usuarios_alergias` DISABLE KEYS */;
INSERT INTO `usuarios_alergias` VALUES (3,2),(3,19),(1,3),(2,3),(2,15);
/*!40000 ALTER TABLE `usuarios_alergias` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-20  7:10:10
