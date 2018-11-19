/*
delete from Envios
delete from Asignaciones
delete from Distancias
delete from Almacenes
*/



/*
Procedimientos y funciones ejercicio Almacenes
*/

/*
	NOMBRE:func_Mostrar_Pedidos_Sin_Asignar
	DESCRIPCION:FUNCION LA CUAL NOS DEVOLVERA TODOS LOS PEDIDOS SIN ASIGNAR.
	ENTRADAS:NO HAY
	SALIDAS:ENVIOS SIN ASIGNACION
*/
GO
CREATE FUNCTION func_Mostrar_Pedidos_Sin_Asignar()
RETURNS TABLE
AS
RETURN
(
	
	SELECT ID,NumeroContenedores,FechaCreacion,FechaAsignacion 
		FROM Envios
		WHERE FechaAsignacion IS NULL
)
GO

/*
	NOMBRE:LISTADOENVIOS()
	DESCRIPCION:FUNCION LA CUAL NOS DEVOLVERA UN LISTADO COMPLETO DE LOS ENVIOS
	ENTRADAS:NO HAY
	SALIDAS: TODOS LOS ENVIOS
*/
GO
CREATE FUNCTION ListadoEnvios()
RETURNS TABLE
AS
RETURN
(
	SELECT *
		FROM Envios
)


GO


/*
	NOMBRE:BUSCARENVIOSSINASIGNAR
	DESCRIPCION:FUNCION LA CUAL NOS BUSCARA UN PEDIDO EN CONCRETO SIN ASIGNAR
	ENTRADAS: IDENVIO
	SALIDAS: EL ENVIO
*/
CREATE FUNCTION BuscarEnvioSinAsignar(@idEnvio int)
RETURNS TABLE
AS
RETURN
(
	SELECT *
		FROM Envios where ID = @idEnvio AND FechaAsignacion IS NULL
)


GO

/*
Nombre: MostrarAlmacenesConCapacidadLibre
Descripcion: Funcion la cual nos mostrara la capacidad libre de cada almacen
Entradas: No hay
Salidas:No hay
*/

CREATE FUNCTION MostrarAlmacenesConCapacidadLibre()
RETURNS TABLE
AS
RETURN
(
	SELECT A.ID, A.Capacidad, Sum(E.NumeroContenedores) AS Ocupado, A.Capacidad - Sum(E.NumeroContenedores) AS disponible From Almacenes AS A 
	Inner Join Asignaciones As Ag ON A.ID = Ag.IDAlmacen
	Inner Join Envios AS E ON Ag.IDEnvio = E.ID
	Group By A.ID, A.Capacidad
)


GO


/*
Nombre: MostrarAlmacenesConCapacidadLibre
Descripcion: Funcion la cual nos mostrara la capacidad libre de cada almacen
Entradas: No hay
Salidas:No hay
*/

create FUNCTION MostrarAlmacenConCapacidadLibre(@idAlmacen int)
RETURNS TABLE
AS
RETURN
(
	SELECT A.ID, A.Capacidad, Sum(E.NumeroContenedores) AS Ocupado, A.Capacidad - Sum(E.NumeroContenedores) AS disponible From Almacenes AS A 
	Inner Join Asignaciones As Ag ON A.ID = Ag.IDAlmacen
	Inner Join Envios AS E ON Ag.IDEnvio = E.ID
	where A.ID = @idAlmacen
	Group By A.ID, A.Capacidad
		

	
)


GO

/*
Nombre: MostrarAlmacenesConCapacidadLibre
Descripcion: Funcion la cual nos mostrara la capacidad libre de cada almacen
Entradas: No hay
Salidas:No hay
*/

GO
CREATE PROCEDURE ComprobarAlmacnes (@IdAlmacen int,@envioContenedores int)
AS
	
	declare @capacidad int
	declare @ret int
	set @capacidad = (SELECT A.Capacidad - Sum(E.NumeroContenedores) AS disponible From Almacenes AS A 
						Inner Join Asignaciones As Ag ON A.ID = Ag.IDAlmacen
						Inner Join Envios AS E ON Ag.IDEnvio = E.ID
						where A.ID = @IdAlmacen
						Group By A.ID, A.Capacidad)

	if ((@capacidad - @envioContenedores ) > 0)
		set @ret = 1
	else
		set @ret = 0

	return @ret


GO




/*
INSERTS DE DATOS
*/

GO
INSERT INTO Almacenes (ID,Denominacion,Direccion,Capacidad)
     VALUES (1,'Nave de Manuela','C/Hierro, 27 Sevilla',400)
	 ,(2,'PaquePluf','C/Econom�a, 30 Sevilla',250)
	 ,(10,'PaquePluf','C/Serranito, 22 Huelva',450)
	 ,(20,'Storing','C/Torremolinos, 41 M�laga',1500)
	 ,(30,'PaquetEnteres','C/Caballa, 75 C�diz',500)
GO
INSERT INTO Distancias (IDAlmacen1,IDAlmacen2,Distancia)
     VALUES (1,2,7)
	 ,(1,10,100)
	 ,(1,20,250)
	 ,(1,30,140)
	 ,(2,10,105)
	 ,(2,20,256)
	 ,(2,30,143)
	 ,(10,20,340)
	 ,(10,30,235)
	 ,(20,30,196)
SET dateformat 'ymd'
GO

INSERT INTO Envios (ID,NumeroContenedores,FechaCreacion,FechaAsignacion,AlmacenPreferido)
     VALUES	(0,16,'20181102','20181106',1)
		,(1,17,'20180502','20181106',1)
		,(2,25,'20180624','20181106',1)
		,(3,117,'20180602','20181106',1)
		,(4,11,'20180507','20181106',1)
		,(5,130,'20180924',NULL,1)
		,(6,46,'20181012',NULL,1)
		,(7,18,'20180714',NULL,1)
		,(8,20,'20180921',NULL,1)
		,(9,34,'20180602',NULL,1)
		,(10,16,'20181102',NULL,1)
		,(11,50,'20180502',NULL,2)
		,(12,25,'20180624',NULL,2)
		,(13,59,'20180802',NULL,2)
		,(14,22,'20180507',NULL,1)
		,(15,31,'20180924',NULL,1)
		,(16,143,'20181012','20181106',2)
		,(17,86,'20180714','20181106',1)
		,(18,120,'20180921',NULL,1)
		,(19,91,'20180602',NULL,1)
		,(20,86,'20181102','20181106',10)
		,(21,150,'20180502',NULL,10)
		,(22,205,'20180624',NULL,10)
		,(23,57,'20180602',NULL,10)
		,(24,41,'20180507','20181106',10)
		,(25,130,'20180924',NULL,20)
		,(26,146,'20181012',NULL,20)
		,(27,18,'20180714',NULL,20)
		,(28,220,'20180921','20181106',20)
		,(29,34,'20180602',NULL,20)
		,(30,16,'20181102',NULL,10)
		,(31,17,'20180502','20181106',30)
		,(32,25,'20180624',NULL,30)
		,(33,17,'20180602','20181106',30)
		,(34,91,'20180507',NULL,20)
		,(35,30,'20180924',NULL,20)
		,(36,246,'20181012','20181106',20)
		,(37,148,'20180714',NULL,30)
		,(38,204,'20180921','20181106',30)
		,(39,74,'20180602',NULL,20)
GO

INSERT INTO Asignaciones (IDEnvio,IDAlmacen)
     VALUES (0,1),(1,1),(2,1),(3,1),(4,10),(16,2),(17,2)
	 ,(20,10),(24,10),(28,20),(31,30),(33,30),(36,20),(38,30)
GO


-- Comprobar la capacidad libre de cada almac�n

SELECT A.ID, A.Capacidad, Sum(E.NumeroContenedores) AS Ocupado, A.Capacidad - Sum(E.NumeroContenedores) AS disponible From Almacenes AS A 
	Inner Join Asignaciones As Ag ON A.ID = Ag.IDAlmacen
	Inner Join Envios AS E ON Ag.IDEnvio = E.ID
	Group By A.ID, A.Capacidad


	select * from Almacenes
