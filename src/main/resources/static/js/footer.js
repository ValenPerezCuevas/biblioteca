
$(document).ready(function() {

    let frases = [
            {
                "frase": "No hay mayor tormenta que la que crea la propia mente.",
                "autor": "Shakespeare",
                "obra": "El rey Lear"
            },
            {
                "frase": "El amor es una ilusión, una historia que una construye en su mente, consciente todo el tiempo de que no es verdad.",
                "autor": "Salman Rushdie",
                "obra": "Los versos satánicos"
            },
            {
                "frase": "Dos cosas son infinitas: el universo y la estupidez humana; y yo no estoy seguro sobre el universo.",
                "autor": "Albert Einstein",
                "obra": "Mis creencias"
            },
            {
                "frase": "El amor es la fuerza más poderosa del universo, y cuando dos almas se encuentran, se convierte en un verdadero milagro.",
                "autor": "Nicholas Sparks",
                "obra": "El cuaderno de Noah"
            },
            {
                "frase": "Nunca olvides lo que eres, porque el mundo no lo hará.",
                "autor": "George R.R. Martin",
                "obra": "Juego de tronos"
            },
            {
                "frase": "No hay amigo tan leal como un libro.",
                "autor": "Ernest Hemingway"
            },
            {
                "frase": "La vida no se mide por el número de veces que respiramos, sino por los momentos que nos dejan sin aliento.",
                "autor": "Maya Angelou",
                "obra": "Levántate"
            },
            {
                "frase": "La verdad es una cosa terrible y hermosa, y por lo tanto debe ser tratada con gran precaución.",
                "autor": "J.K. Rowling",
                "obra": "Harry Potter y la piedra filosofal"
            },
            {
                "frase": "La única forma de hacer un gran trabajo es amar lo que haces.",
                "autor": "Steve Jobs"
            },
            {
                "frase": "La verdadera medida de un hombre no está en cómo se comporta en momentos de comodidad y conveniencia, sino en cómo se mantiene en tiempos de controversia y desafío.",
                "autor": "Martin Luther King Jr."
            },
            {
                "frase": "La felicidad solo puede encontrarse si primero se pierde.",
                "autor": "Anton Chekhov",
                "obra": "El jardín de los cerezos"
            },
            {
                "frase": "No hay ninguna cosa en este mundo que sea capaz de producir tanto dolor como el amor y el odio.",
                "autor": "Gabriel García Márquez",
                "obra": "El amor en los tiempos del cólera"
            },
            {
                "frase": "No puedo cambiar la dirección del viento, pero puedo ajustar mis velas para llegar siempre a mi destino.",
                "autor": "Jimmy Dean"
            },
            {
                "frase": "La imaginación es más importante que el conocimiento.",
                "autor": "Albert Einstein"
            },
            {
                "frase": "Si no puedes volar, entonces corre; si no puedes correr, entonces camina; si no puedes caminar, entonces gatea; pero hagas lo que hagas, debes seguir adelante.",
                "autor": "Martin Luther King Jr."
            },
            {
                "frase": "La mente que se abre a una nueva idea nunca volverá a su tamaño original.",
                "autor": "Albert Einstein"
            },
            {
                "frase": "La vida es lo que pasa mientras estás ocupado haciendo otros planes.",
                "autor": "John Lennon"
            },
            {
                "frase": "La mejor venganza es vivir bien.",
                "autor": "George Herbert"
            },
            {
                "frase": "Nunca digas adiós, porque decir adiós significa irse, y olvidar significa morir.",
                "autor": "Peter Pan",
                "obra": "Peter Pan"
            },
            {
                "frase": "No somos seres humanos teniendo una experiencia espiritual, somos seres espirituales teniendo una experiencia humana.",
                "autor": "Pierre Teilhard de Chardin"
            },
            {
                "frase": "La única manera de hacer un amigo es ser uno.",
                "autor": "Ralph Waldo Emerson"
            },
            {
                "frase": "La única cosa que se interpone entre tú y tu sueño es la voluntad de intentarlo y la creencia de que en realidad es posible.",
                "autor": "Joel Brown"
            },
            {
                "frase": "Los dos días más importantes de tu vida son el día en que naces y el día en que descubres por qué.",
                "autor": "Mark Twain"
            },
            {
                "frase": "La libertad no tiene ningún valor si no incluye la libertad de cometer errores.",
                "autor": "Mahatma Gandhi"
            },
            {
                "frase": "La mejor forma de predecir el futuro es inventarlo.",
                "autor": "Alan Kay"
            },
            {
                "frase": "La vida es un 10% lo que te pasa y un 90% cómo reaccionas a ello.",
                "autor": "Charles R. Swindoll"
            },
            {
                "frase": "Los grandes espíritus siempre han encontrado violenta oposición de parte de mentes mediocres.",
                "autor": "Albert Einstein"
            },
            {
                "frase": "Las grandes mentes discuten ideas; las mentes promedio discuten eventos; las mentes pequeñas discuten personas.",
                "autor": "Eleanor Roosevelt"
            },
            {
                "frase": "Las oportunidades no son producto del azar, sino del esfuerzo.",
                "autor": "Steve Jobs"
            },
            {
                "frase": "Las palabras son, en mi no tan humilde opinión, nuestra más inagotable fuente de magia, capaces de herir y de curar.",
                "autor": "Albus Dumbledore",
                "obra": "Harry Potter y las Reliquias de la Muerte"
            },
            {
                "frase": "Nada en este mundo puede sustituir a la persistencia.",
                "autor": "Calvin Coolidge"
            },
            {
                "frase": "Lo único que se interpone entre tú y tu sueño es la voluntad de intentarlo y la creencia de que es posible.",
                "autor": "Joel Brown"
            },
            {
                "frase": "La educación es el arma más poderosa que puedes usar para cambiar el mundo.",
                "autor": "Nelson Mandela"
            },
            {
                "frase": "La vida es como una bicicleta. Para mantener el equilibrio, debes seguir adelante.",
                "autor": "Albert Einstein"
            },
            {
                "frase": "La lógica te llevará desde A hasta B. La imaginación te llevará a todas partes.",
                "autor": "Albert Einstein"
            },
            {
                "frase": "La vida es una novela interminable, pero es la novela en la que solo tenemos una vida.",
                "autor": "Graham Greene"
            },
            {
                "frase": "La magia es simplemente el arte de cambiar la realidad a voluntad.",
                "autor": "Terry Pratchett",
                "obra": "Un sombrero de cielo"
            },
            {
                "frase": "No importa lo que te ocurra, los dragones siempre son difíciles de domesticar.",
                "autor": "Cressida Cowell",
                "obra": "Cómo entrenar a tu dragón"
            },
            {
                "frase": "Nunca subestimes el poder de los libros.",
                "autor": "J.K. Rowling",
                "obra": "Harry Potter y la piedra filosofal"
            },
            {
                "frase": "La imaginación es el único arma en la guerra contra la realidad.",
                "autor": "Lewis Carroll",
                "obra": "Alicia en el país de las maravillas"
            },
            {
                "frase": "La magia es algo que se lleva dentro.",
                "autor": "Tamora Pierce",
                "obra": "Alanna: The First Adventure"
            },
            {
                "frase": "No todos los cuentos tienen un final feliz, pero eso no significa que no sean importantes.",
                "autor": "Patrick Rothfuss",
                "obra": "El nombre del viento"
            },
            {
                "frase": "La luz siempre está más cerca de lo que piensas.",
                "autor": "Laini Taylor",
                "obra": "Hija de humo y hueso"
            },
            {
                "frase": "A veces, la realidad es solo una ilusión.",
                "autor": "Michael Ende",
                "obra": "La historia interminable"
            },
            {
                "frase": "El destino es el que baraja las cartas, pero nosotros somos los que jugamos.",
                "autor": "Arthur Golden",
                "obra": "Memorias de una geisha"
            },
            {
                "frase": "En un lugar de la Mancha, de cuyo nombre no quiero acordarme...",
                "autor": "Miguel de Cervantes",
                "obra": "Don Quijote de la Mancha"
            },
            {
                "frase": "Yo sé quién soy, y sé que soy lo que debo ser.",
                "autor": "Miguel de Cervantes",
                "obra": "Don Quijote de la Mancha"
            },
            {
                "frase": "La verdad adelgaza y no quiebra, y siempre anda sobre la mentira como el aceite sobre el agua.",
                "autor": "Miguel de Cervantes",
                "obra": "Don Quijote de la Mancha"
            },
            {
                "frase": "Poderoso caballero es don Dinero.",
                "autor": "Francisco de Quevedo",
                "obra": "El Buscón"
            },
            {
                "frase": "Amor y deseo son dos cosas diferentes; que no todo lo que se ama se desea, ni todo lo que se desea se ama.",
                "autor": "Don Quijote",
                "obra": "Don Quijote de la Mancha"
            },
            {
                "frase": "La peor ceguera es la del corazón.",
                "autor": "Calderón de la Barca",
                "obra": "La vida es sueño"
            },
            {
                "frase": "Dichoso aquel a quien el cielo dio un pedazo de pan sin que le quede obligación de agradecérselo a otro que al mismo cielo.",
                "autor": "Francisco de Quevedo",
                "obra": "El Buscón"
            },
            {
                "frase": "El alma que hablar puede con los ojos, también puede besar con la mirada.",
                "autor": "Gustavo Adolfo Bécquer",
                "obra": "Rimas y leyendas"
            },
            {
                "frase": "Porque es tan bravo el amor que jamás le podrá vencer la fortuna, y siempre triunfa sobre sí misma.",
                "autor": "Lope de Vega",
                "obra": "La dama boba"
            },
            {
                "frase": "Quien todo lo quiere fácil, será siempre un necio.",
                "autor": "Miguel de Cervantes",
                "obra": "Don Quijote de la Mancha"
            },
            {
                "frase": "El verdadero conocimiento es saber la extensión de la propia ignorancia.",
                "autor": "Confucio"
            },
            {
                "frase": "El conocimiento es poder.",
                "autor": "Francis Bacon"
            },
            {
                "frase": "La educación es el pasaporte hacia el futuro, el mañana pertenece a aquellos que se preparan para él en el día de hoy.",
                "autor": "Malcolm X"
            },
            {
                "frase": "El conocimiento es la única cosa que nadie puede quitarte.",
                "autor": "B.B. King"
            },
            {
                "frase": "El mayor enemigo del conocimiento no es la ignorancia, sino la ilusión de conocimiento.",
                "autor": "Stephen Hawking"
            },
            {
                "frase": "El conocimiento es como un jardín: si no se cultiva, no se puede cosechar.",
                "autor": "Proverbio africano"
            },
            {
                "frase": "El conocimiento es la única inversión que nunca falla.",
                "autor": "Benjamin Franklin"
            },
            {
                "frase": "El conocimiento es el único tesoro que crece cuando se comparte.",
                "autor": "Sócrates"
            },
            {
                "frase": "La curiosidad es la chispa que enciende el fuego del conocimiento.",
                "autor": "Francis Bacon"
            },
            {
                "frase": "El conocimiento es un tesoro, pero la práctica es la llave para alcanzarlo.",
                "autor": "Thomas Fuller"
            },
            {
                "frase": "El futuro pertenece a aquellos que creen en la belleza de sus sueños.",
                "autor": "Eleanor Roosevelt"
            },
            {
                "frase": "La vida es 10% lo que nos sucede y 90% cómo reaccionamos ante ello.",
                "autor": "Charles R. Swindoll"
            },
            {
                "frase": "La única manera de hacer un gran trabajo es amar lo que haces.",
                "autor": "Steve Jobs"
            },
            {
                "frase": "No importa cuántas veces fracases, debes seguir adelante y nunca rendirte.",
                "autor": "Richard Branson"
            },
            {
                "frase": "El éxito no es definitivo, el fracaso no es fatal: es el coraje para continuar lo que cuenta.",
                "autor": "Winston Churchill"
            },
            {
                "frase": "La persistencia es el camino al éxito.",
                "autor": "Charles Chaplin"
            },
            {
                "frase": "La actitud es una pequeña cosa que hace una gran diferencia.",
                "autor": "Winston Churchill"
            },
            {
                "frase": "No te preocupes por los fracasos, preocúpate por las oportunidades que pierdes cuando ni siquiera lo intentas.",
                "autor": "Jack Canfield"
            },
            {
                "frase": "La mejor manera de predecir el futuro es crearlo.",
                "autor": "Peter Drucker"
            },
            {
                "frase": "El viaje de mil millas comienza con un solo paso.",
                "autor": "Lao Tsé"
            }
        ]
    ;

    let fraseIndex = 0;
    let fraseElement = $('#frase');

    function cambiarFrase() {
        fraseElement.fadeOut(function() {
            let fraseActual = frases[fraseIndex];
            let obraTexto = fraseActual.obra ? fraseActual.obra : "Obra desconocida";
            let estilo = $('<style>#frase { color: #8a6041; }</style>');
            $('head').append(estilo); // Añadir el estilo al <head>
            fraseElement.html('<q>' + fraseActual.frase + '</q><br><span> - ' + fraseActual.autor + ', ' + obraTexto + '</span>');
            fraseIndex = (fraseIndex + 1) % frases.length;
            fraseElement.fadeIn();
        });
    }

    cambiarFrase();
    setInterval(cambiarFrase, 5000);
});
