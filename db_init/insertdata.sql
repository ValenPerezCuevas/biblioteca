-- Seleccionar la base de datos biblioteca
USE biblioteca;

-- Insertar datos de ejemplo en la tabla roles
INSERT INTO roles VALUES
(1,'Administrador'),
(2,'Usuario');

-- Ejemplos de libros de ficción general
INSERT INTO libros (titulo, genero, autor, ano_publicacion, editorial) VALUES
("Cien años de soledad", "Ficción", "Gabriel García Márquez", 1967, "Ediciones conmemorativas de la Real Academia Española"),
("Don Quijote de la Mancha", "Ficción", "Miguel de Cervantes", 1605, "Editorial Castalia"),
("La sombra del viento", "Ficción", "Carlos Ruiz Zafón", 2001, "Editorial Planeta"),
("Rayuela", "Ficción", "Julio Cortázar", 1963, "Editorial Sudamericana"),
("El amor en los tiempos del cólera", "Ficción", "Gabriel García Márquez", 1985, "Editorial Sudamericana"),
("Pedro Páramo", "Ficción", "Juan Rulfo", 1955, "Editorial rm"),
("La casa de los espíritus", "Ficción", "Isabel Allende", 1982, "DEBOLSILLO"),
("El túnel", "Ficción", "Ernesto Sabato", 1948, "Austral"),
("Ficciones", "Ficción", "Jorge Luis Borges", 1944, "Alianza Editorial"),
("El laberinto de los espíritus", "Ficción", "Carlos Ruiz Zafón", 2016, "Editorial Planeta"),
("Los detectives salvajes", "Ficción", "Roberto Bolaño", 1998, "DEBOLSILLO"),
("2666", "Ficción", "Roberto Bolaño", 2004, "Editorial Anagrama"),
("El perfume", "Ficción", "Patrick Süskind", 1985, "Editorial Seix Barral"),
("La verdad sobre el caso Savolta", "Ficción", "Eduardo Mendoza", 1975, "Editorial Seix Barral"),
("Los renglones torcidos de Dios", "Ficción", "Torcuato Luca de Tena", 1979, "Editorial Austral"),
("El prisionero del cielo", "Ficción", "Carlos Ruiz Zafón", 2011, "Editorial Planeta"),
("La casa de papel", "Ficción", "Carlos María Domínguez", 2017, "Editorial Alfaguara"),
("La hoguera de las vanidades", "Ficción", "Tom Wolfe", 1987, "Editorial RBA"),
("Los pilares de la Tierra", "Ficción", "Ken Follett", 1989, "DEBOLSILLO"),
("El retrato de Dorian Gray", "Ficción", "Oscar Wilde", 1890, "Editorial Lippincott's Monthly Magazine"),
("El club Dumas", "Ficción", "Arturo Pérez-Reverte", 1993, "Editorial Alfaguara"),
("El coronel no tiene quien le escriba", "Ficción", "Gabriel García Márquez", 1961, "Editorial La Oveja Negra"),
("El Aleph", "Ficción", "Jorge Luis Borges", 1949, "Editorial Losada"),
("La ladrona de libros", "Ficción", "Markus Zusak", 2005, "Editorial Lumen");

-- Ejemplos de libros de fantasía y ciencia ficción
INSERT INTO libros (titulo, genero, autor, ano_publicacion, editorial) VALUES
("Harry Potter y la piedra filosofal", "Fantasía", "J.K. Rowling", 1997, "Salamandra"),
("El señor de los anillos", "Fantasía", "J.R.R. Tolkien", 1954, "Minotauro"),
("Crónicas de Narnia: El león, la bruja y el armario", "Fantasía", "C.S. Lewis", 1950, "HarperCollins"),
("Juego de tronos", "Fantasía", "George R.R. Martin", 1996, "Gilgamesh"),
("Cazadores de sombras: Ciudad de hueso", "Fantasía", "Cassandra Clare", 2007, "Planeta"),
("El nombre del viento", "Fantasía", "Patrick Rothfuss", 2007, "DAW Books"),
("El hobbit", "Fantasía", "J.R.R. Tolkien", 1937, "Allen & Unwin"),
("Crepúsculo", "Fantasía", "Stephenie Meyer", 2005, "Little, Brown and Company"),
("Los juegos del hambre", "Ciencia ficción", "Suzanne Collins", 2008, "Molino"),
("Dune", "Ciencia ficción", "Frank Herbert", 1965, "Chilton Books"),
("Fundación", "Ciencia ficción", "Isaac Asimov", 1951, "Gnome Press"),
("El fin de la eternidad", "Ciencia ficción", "Isaac Asimov", 1955, "Doubleday"),
("Neuromante", "Ciencia ficción", "William Gibson", 1984, "Ace Books"),
("El hombre en el castillo", "Ciencia ficción", "Philip K. Dick", 1962, "booket"),
("Un mundo feliz", "Ciencia ficción", "Aldous Huxley", 1932, "DEBOLSILLO"),
("La máquina del tiempo", "Ciencia ficción", "H.G. Wells", 1895, "Juventud"),
("Solaris", "Ciencia ficción", "Stanisław Lem", 1961, "booket"),
("Blade Runner: ¿Sueñan los androides con ovejas eléctricas?", "Ciencia ficción", "Philip K. Dick", 1968, "EDHASA"),
("Ready Player One", "Ciencia ficción", "Ernest Cline", 2011, "Nova");

-- Insertar 50 nuevos ejemplos de grandes libros de la literatura universal
INSERT INTO libros (titulo, genero, autor, ano_publicacion, editorial) VALUES
("Ulises", "Ficción", "James Joyce", 1922, "DEBOLSILLO"),
("Guerra y paz", "Ficción", "León Tolstói", 1869, "Alba Clásica Maior"),
("En busca del tiempo perdido", "Ficción", "Marcel Proust", 1913, "Alianza Editorial"),
("Moby-Dick", "Ficción", "Herman Melville", 1851, "Alianza Editorial"),
("Las aventuras de Huckleberry Finn", "Ficción", "Mark Twain", 1884, "Austral"),
("Madame Bovary", "Ficción", "Gustave Flaubert", 1857, "Alma"),
("En el camino", "Ficción", "Jack Kerouac", 1957, "Anagrama"),
("Don Álvaro o la fuerza del sino", "Ficción", "Ángel de Saavedra", 1835, "Edelvives"),
("El gran Gatsby", "Ficción", "F. Scott Fitzgerald", 1925, "Charles Scribner's Sons"),
("El extranjero", "Ficción", "Albert Camus", 1942, "emc"),
("David Copperfield", "Ficción", "Charles Dickens", 1850, "Alba"),
("Orgullo y prejuicio", "Ficción", "Jane Austen", 1813, "Penguin"),
("La montaña mágica", "Ficción", "Thomas Mann", 1924, "DEBOLSILLO"),
("Robinson Crusoe", "Ficción", "Daniel Defoe", 1719, "W. Taylor"),
("Rebelión en la granja", "Ficción", "George Orwell", 1945, "Calameo"),
("El retrato de una dama", "Ficción", "Henry James", 1881, "Alianza Tres"),
("Las uvas de la ira", "Ficción", "John Steinbeck", 1939, "Galaxia Guttemberg"),
("Vida y destino", "Ficción", "Vasili Grossman", 1980, "Harper & Row"),
("La naranja mecánica", "Ficción", "Anthony Burgess", 1962, "Booket"),
("Lo que el viento se llevó", "Ficción", "Margaret Mitchell", 1936, "RBA"),
("El tambor de hojalata", "Ficción", "Günter Grass", 1959, "Luchterhand"),
("Los hermanos Karamázov", "Ficción", "Fiódor Dostoyevski", 1880, "The Russian Messenger"),
("Doctor Zhivago", "Ficción", "Boris Pasternak", 1957, "Gallimard"),
("Matar un ruiseñor", "Ficción", "Harper Lee", 1960, "J.B. Lippincott & Co."),
("La feria de las vanidades", "Ficción", "William Makepeace Thackeray", 1947, "Alba"),
("Cumbres borrascosas", "Ficción", "Emily Brontë", 1847, "Thomas Cautley Newby"),
("Alicia en el país de las maravillas", "Ficción", "Lewis Carroll", 1965, "Macmillan"),
("Las brujas", "Ficción", "Roald Dahl", 1983, "Anagrama");

-- Insertar 50 ejemplos de libros de novela negra y misterio
INSERT INTO libros (titulo, genero, autor, ano_publicacion, editorial) VALUES
("El nombre de la rosa", "Novela negra", "Umberto Eco", 1980, "Lumen"),
("El cuervo", "Novela negra", "Edgar Allan Poe", 1945, "Alma Clásicos"),
("La ventana indiscreta", "Novela negra", "Cornell Woolrich", 1942, "Planeta"),
("El silencio de los corderos", "Novela negra", "Thomas Harris", 1988, "DEBOLSILLO"),
("Los hombres que no amaban a las mujeres", "Novela negra", "Stieg Larsson", 2005, "Destino"),
("Muerte en el Nilo", "Novela negra", "Agatha Christie", 1937, "Collins Crime Club"),
("El hombre equivocado", "Novela negra", "John Katzenbach", 1987, "Maxi"),
("El cartero siempre llama dos veces", "Novela negra", "James M. Cain", 1934, "Alfred A. Knopf"),
("El silencio de la ciudad blanca", "Novela negra", "Eva García Sáenz de Urturi", 2016, "Editorial Planeta"),
("La clave está en Rebeca", "Novela negra", "Ken Follett", 1980, "Planeta DeAgostini"),
("El muñeco de nieve", "Novela negra", "Jo Nesbø", 2007, "DEBOLSILLO."),
("El hombre que fue jueves", "Novela negra", "G. K. Chesterton", 1908, "Harper's Weekly"),
("El arte de la novela", "Novela negra", "Milan Kundera", 1986, "Maxi-TusQuets"),
("El veredicto", "Novela negra", "Michael Connelly", 2011, "rocabolsillo");

-- Insertar 50 ejemplos de libros de autoayuda y superación personal
INSERT INTO libros (titulo, genero, autor, ano_publicacion, editorial) VALUES
("El monje que vendió su Ferrari", "Autoayuda", "Robin Sharma", 1997, "DeBolsillo"),
("El poder del ahora", "Autoayuda", "Eckhart Tolle", 1997, "Gaia"),
("Padre rico, padre pobre", "Autoayuda", "Robert Kiyosaki", 1997, "DeBolsillo"),
("Los 7 hábitos de la gente altamente efectiva", "Autoayuda", "Stephen Covey", 1989, "Free Press"),
("Despertando al gigante interior", "Autoayuda", "Tony Robbins", 1991, "DeBolsillo"),
("El secreto", "Autoayuda", "Rhonda Byrne", 2006, "Atria Books"),
("Piense y hágase rico", "Autoayuda", "Napoleon Hill", 1937, "The Ralston Society"),
("Mujeres que corren con los lobos", "Autoayuda", "Clarissa Pinkola Estés", 1992, "Ballantine Books"),
("El código del dinero", "Autoayuda", "Raimon Samsó", 2012, "Editorial Sirio"),
("El alquimista", "Autoayuda", "Paulo Coelho", 1988, "Rocco"),
("Cómo ganar amigos e influir sobre las personas", "Autoayuda", "Dale Carnegie", 1936, "Simon & Schuster"),
("El caballero de la armadura oxidada", "Autoayuda", "Robert Fisher", 1986, "Obelisco"),
("La magia del orden", "Autoayuda", "Marie Kondo", 2011, "Ten Speed Press"),
("Quién se ha llevado mi queso", "Autoayuda", "Spencer Johnson", 1998, "G.P. Putnam's Sons");
-- Insertar 20 libros de Stephen King
INSERT INTO libros (titulo, genero, autor, ano_publicacion, editorial) VALUES
("El resplandor", "Terror", "Stephen King", 1977, "Doubleday"),
("It", "Terror", "Stephen King", 1986, "Viking Press"),
("Cementerio de animales", "Terror", "Stephen King", 1983, "DeBolsillo"),
("Carrie", "Terror", "Stephen King", 1974, "DeBolsillo"),
("Misery", "Terror", "Stephen King", 1987, "DeBolsillo"),
("La cúpula", "Ciencia ficción", "Stephen King", 2009, "Scribner"),
("El pistolero", "Fantasía", "Stephen King", 1982, "DeBolsillo"),
("El fugitivo", "Suspense", "Stephen King", 1982, "Signet Books"),
("La torre oscura mago y cristal", "Fantasía", "Stephen King", 2004, "DeBolsillo"),
("El talismán", "Fantasía", "Stephen King", 1984, "Booket"),
("El cazador de sueños", "Ciencia ficción", "Stephen King", 2001, "Scribner"),
("Doctor Sueño", "Terror", "Stephen King", 2013, "DeBolsillo"),
("22/11/63", "Ciencia ficción", "Stephen King", 2011, "Scribner"),
("Apocalipsis", "Terror", "Stephen King", 1978, "DeBolsillo"),
("La milla verde", "Fantasía", "Stephen King", 1996, "DeBolsillo"),
("Salem's Lot", "Terror", "Stephen King", 1975, "Doubleday"),
("La larga marcha", "Ciencia ficción", "Stephen King", 1979, "Bachman"),
("La mitad oscura", "Terror", "Stephen King", 1989, "DeBolsillo"),
("La danza de la muerte", "Terror", "Stephen King", 1978, "Doubleday");

INSERT INTO libros (titulo, genero, autor, ano_publicacion, editorial) VALUES
("El club de la lucha", "Novela", "Chuck Palahniuk", 1996, "DeBolsillo"),
("El juego de Ender", "Ciencia ficción", "Orson Scott Card", 1985, "Tor Books"),
("La ciudad y los perros", "Novela", "Mario Vargas Llosa", 1962, "Seix Barral"),
("Harry Potter y el prisionero de Azkaban", "Fantasía", "J.K. Rowling", 1999, "Alfaguara"),
("American Gods", "Fantasía", "Neil Gaiman", 2001, "rocabolsillo"),
("Bajo la misma estrella", "Juvenil", "John Green", 2012, "Nube de tinta"),
("Crónica de una muerte anunciada", "Novela", "Gabriel García Márquez", 1981, "DeBolsillo"),
("La elegancia del erizo", "Novela", "Muriel Barbery", 2006, "booket"),
("Jurassic Park", "Ciencia ficción", "Michael Crichton", 1990, "Alfred A. Knopf"),
("La insoportable levedad del ser", "Novela", "Milan Kundera", 1984, "TusQuets"),
("El silmarillion", "Fantasía", "J.R.R. Tolkien", 1977, "booket"),
("El guardián entre el centeno", "Novela", "J.D. Salinger", 1951, "Alianza"),
("La chica que soñaba con una cerilla y un bidón de gasolina", "Suspense", "Stieg Larsson", 2006, "Norstedts Förlag"),
("El mundo de Sofía", "Novela", "Jostein Gaarder", 1991, "Siruela"),
("Sapiens: De animales a dioses", "No ficción", "Yuval Noah Harari", 2011, "Kinneret Zmora-Bitan Dvir");



INSERT INTO libros (titulo, genero, autor, ano_publicacion, editorial) VALUES
("Código Limpio: Manual de estilo para el desarrollo ágil de software", "Programación", "Robert C. Martin", 2008, "Anaya"),
("El Programador Pragmático: Tu viaje hacia la maestría", "Programación", "Andrew Hunt, David Thomas", 1999, "Anaya"),
("Patrones de Diseño: Elementos de software orientado a objetos reutilizable", "Programación", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", 1994, "Addison-Wesley Professional"),
("Refactoring: Mejorando el diseño de código existente", "Programación", "Martin Fowler", 1999, "Addison-Wesley Professional"),
("Arquitectura Limpia: Guía del artesano para la estructura y el diseño de software", "Programación", "Robert C. Martin", 2017, "Prentice Hall"),
("Patrones de Diseño: Head First", "Programación", "Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra", 2004, "O'Reilly Media"),
("Estructura e Interpretación de Programas de Computadora", "Programación", "Harold Abelson, Gerald Jay Sussman, Julie Sussman", 1984, "MIT Press"),
("Introducción a los algoritmos", "Programación", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein", 1990, "MIT Press"),
("Perlitas de Programación", "Programación", "Jon Bentley", 1986, "Addison-Wesley Professional"),
("El Arte de la Programación Informática", "Programación", "Donald E. Knuth", 1968, "Addison-Wesley Professional"),
("Java Efectivo", "Programación", "Joshua Bloch", 2001, "Addison-Wesley Professional"),
("El Lenguaje de Programación C", "Programación", "Brian Kernighan, Dennis Ritchie", 1978, "Prentice Hall"),
("JavaScript: Las Buenas Partes", "Programación", "Douglas Crockford", 2008, "O'Reilly Media"),
("JavaScript Elocuente: Una introducción moderna a la programación", "Programación", "Marijn Haverbeke", 2011, "No Starch Press"),
("Python: Curso Intensivo de Inicio a la Programación", "Programación", "Eric Matthes", 2015, "No Starch Press"),
("Aprendiendo Python", "Programación", "Mark Lutz", 1999, "O'Reilly Media"),
("Scala para los Impacientes", "Programación", "Cay S. Horstmann", 2012, "Addison-Wesley Professional"),
("Python Efectivo: 90 formas específicas de escribir un mejor código Python", "Programación", "Brett Slatkin", 2015, "Addison-Wesley Professional"),
("Java Head First", "Programación", "Kathy Sierra, Bert Bates", 2003, "O'Reilly Media"),
("Aprendiendo Perl", "Programación", "Randal L. Schwartz, Tom Phoenix, brian d foy", 1993, "O'Reilly Media"),
("Java en Práctica de la Concurrencia", "Programación", "Brian Goetz, Tim Peierls, Joshua Bloch, Joseph Bowbeer, David Holmes, Doug Lea", 2006, "Addison-Wesley Professional"),
("El Lenguaje de Programación Rust", "Programación", "Steve Klabnik, Carol Nichols", 2018, "No Starch Press"),
("Aprendiendo PHP, MySQL y JavaScript", "Programación", "Robin Nixon", 2018, "O'Reilly Media"),
("Programación en Haskell", "Programación", "Graham Hutton", 2007, "Cambridge University Press"),
("Antipatrones SQL: Evitar las trampas de la programación de bases de datos", "Programación", "Bill Karwin", 2010, "Pragmatic Bookshelf"),
("Algoritmos", "Programación", "Robert Sedgewick, Kevin Wayne", 2011, "Addison-Wesley Professional");


INSERT INTO libros (titulo, genero, autor, ano_publicacion, editorial) VALUES
("Meditaciones", "Filosofía", "Marco Aurelio", 180, "Desconocido"),
("Así habló Zaratustra", "Filosofía", "Friedrich Nietzsche", 1883, "Alma"),
("Crítica de la razón pura", "Filosofía", "Immanuel Kant", 1781, "Desconocido"),
("Ética a Nicómaco", "Filosofía", "Aristóteles", -350, "Desconocido"),
("La República", "Filosofía", "Platón", -380, "Desconocido"),
("Cartas a Lucilio", "Filosofía", "Lucio Anneo Séneca", 65, "Desconocido"),
("Más allá del bien y del mal", "Filosofía", "Friedrich Nietzsche", 1886, "Desconocido"),
("El arte de amar", "Filosofía", "Erich Fromm", 1956, "Desconocido"),
("El elogio de la locura", "Filosofía", "Erasmo de Rotterdam", 1511, "Desconocido"),
("Diálogos", "Filosofía", "Platón", -360, "Desconocido"),
("Crepúsculo de los ídolos", "Filosofía", "Friedrich Nietzsche", 1889, "Desconocido"),
("El banquete", "Filosofía", "Platón", -385, "Desconocido"),
("Critón", "Filosofía", "Platón", -399, "Desconocido"),
("La ética protestante y el espíritu del capitalismo", "Filosofía", "Max Weber", 1905, "Desconocido"),
("El Anticristo", "Filosofía", "Friedrich Nietzsche", 1895, "Desconocido"),
("La riqueza de las naciones", "Filosofía", "Adam Smith", 1776, "Desconocido"),
("La sociedad del cansancio", "Filosofía", "Byung-Chul Han", 2010, "Desconocido"),
("Elogio de la sombra", "Filosofía", "Jun'ichirō Tanizaki", 1933, "Desconocido"),
("Ensayo sobre la ceguera", "Filosofía", "José Saramago", 1995, "Desconocido"),
("El arte de la guerra", "Filosofía", "Sun Tzu", 500, "Desconocido"),
("La rebelión de las masas", "Filosofía", "José Ortega y Gasset", 1930, "Desconocido"),
("Ser y tiempo", "Filosofía", "Martin Heidegger", 1927, "Desconocido"),
("Tratado sobre la tolerancia", "Filosofía", "Voltaire", 1763, "Desconocido"),
("Diario de un loco", "Filosofía", "Nikolái Gógol", 1835, "Desconocido"),
("Historia de la locura en la época clásica", "Filosofía", "Michel Foucault", 1961, "Desconocido"),
("La condición humana", "Filosofía", "André Malraux", 1933, "Desconocido"),
("El origen de la tragedia", "Filosofía", "Friedrich Nietzsche", 1872, "Desconocido");


INSERT INTO usuarios (id_usuario, nombre, contrasena, id_rol) VALUES
(1, 'MarkTwain', 'HuckleberryPass', 1),
(2, 'JaneAusten', 'Pride&Prejudice!', 2),
(3, 'ErnestHemingway', 'OldManAndTheSea123', 1),
(4, 'VirginiaWoolf', 'WavesOfCreativity', 2),
(5, 'WilliamShakespeare', 'ToBeOrNotToBe!', 1),
(6, 'JKRolkien', 'MagicRingBearer', 2),
(7, 'GabrielGarciaMarquez', 'HundredYearsOfSolitude', 1),
(8, 'AgathaChristie', 'MurderOnThePasswordExpress', 2),
(9, 'GeorgeOrwell', '1984BigBrother', 1),
(10, 'JRRMartin', 'GameOfPasswords', 2),
(11, 'LeoTolstoy', 'WarAndPeace123', 1),
(12, 'CharlesDickens', 'GreatExpectations2024', 2),
(13, 'FScottFitzgerald', 'GatsbyGreat100!', 1),
(14, 'EmilyDickinson', 'HopeIsTheThing', 2),
(15, 'EdgarAllanPoe', 'RavenNevermore!', 1),
(16, 'SylviaPlath', 'BellJar1963', 2),
(17, 'HermanMelville', 'MobyDickWhale', 1),
(18, 'FranzKafka', 'Metamorphosis1915', 2),
(19, 'OscarWilde', 'DorianGrayPortrait', 1),
(20, 'MaryShelley', 'Frankenstein1818', 2);

INSERT INTO listas (id_lista, id_usuario, nombre_lista, creado_desde, actualizado_desde, creado_por, actualizado_por) VALUES
(1, 11, 'Lista 1', '2024-05-10 12:00:00', '2024-05-10 12:00:00', 11, 12),
(2, NULL, 'Lista 2', '2024-05-10 12:00:00', '2024-05-10 12:00:00', NULL, NULL),
(3, 12, 'Lista 3', '2024-05-10 12:00:00', '2024-05-10 12:00:00', 13, 11),
(4, 13, 'Lista 4', '2024-05-10 12:00:00', '2024-05-10 12:00:00', 11, 12),
(5, 14, 'Lista 5', '2024-05-10 12:00:00', '2024-05-10 12:00:00', 12, 13),
(6, NULL, 'Lista 6', '2024-05-10 12:00:00', '2024-05-10 12:00:00', NULL, NULL),
(7, 15, 'Lista 7', '2024-05-10 12:00:00', '2024-05-10 12:00:00', 13, 11),
(8, 16, 'Lista 8', '2024-05-10 12:00:00', '2024-05-10 12:00:00', 11, 12),
(9, 17, 'Lista 9', '2024-05-10 12:00:00', '2024-05-10 12:00:00', 12, 13),
(10, NULL, 'Lista 10', '2024-05-10 12:00:00', '2024-05-10 12:00:00', NULL, NULL),
(11, 18, 'Lista 11', '2024-05-10 12:00:00', '2024-05-10 12:00:00', 13, 11),
(12, 19, 'Lista 12', '2024-05-10 12:00:00', '2024-05-10 12:00:00', 11, 12),
(13, 20, 'Lista 13', '2024-05-10 12:00:00', '2024-05-10 12:00:00', 12, 13);

INSERT INTO libros_lista (id_libros_lista, id_libro, id_lista, id_libros) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 1, 2),
(4, 5, 2),
(5, 8, 3),
(6, 9, 3),
(7, 4, 4),
(8, 6, 4),
(9, 3, 5),
(10, 7, 5),
(11, 10, 5);
