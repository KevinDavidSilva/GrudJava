CREATE TABLE Libros(
	LibroID int Primary key identity(1,1),
	Titulo varchar(200) not null,
	Autor varchar(100) not null,
	Genero varchar(50) not null,
	Publicacion date not null,
	Disponible bit not null default 1
);

CREATE TABLE Prestamo(
	PrestamoID int primary key identity(1,1),
	LibroID int not null,
	UsuarioNombre varchar(100) not null,
	UsuarioCorreo varchar(100) not null,
	FechaPrestamo date not null,
	FechaDevolucion date,
	FOREIGN KEY (LibroID) REFERENCES Libros(LibroID)
);