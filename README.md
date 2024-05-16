ECOSISTEMA

-Se clona el repositorio con el enlace.

-Se comprueba que están todos los archivos


SISTEMA DE RAMAS
1.	Se sincroniza el trabajo desde la rama master:
git fetch --all

3.	Si es necesario se pullean los cambios a master:
git pull

4.	Nos cambiamos a la rama que se nos ha asignado el trabajo:
git checkout issue#1

5.	Trabajamos en la rama hasta que acabemos con el trabajo a desempeñar. Cada cambio significativo debe ir asociado a un commit:
git add .

git commit –m “Cambios en la plantilla libros. Añadido método borrado”


6.	Una vez acabemos las implementaciones subimos nuestra rama:
git push origin issue#1

8.	Cuando tengamos subido nuestra rama necesitamos fusionarla con la principal desde master:
git merge –no-ff –m “Fusiono issue#1 con develop” issue#1

git push origin master


10.	Se pueden dar dos casos:
a.	No hay conflictos: simplemente se sube y listo

b.	Hay conflictos: se tiene que ir a InteliJ en la parte de commit.
Hay que resolver los conflictos y finalizar el commit.
Revisamos en la parte de las ramas que todo este correcto.
