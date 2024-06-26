package biblioteca.biblioteca.entidades;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class listas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_lista;

    private String nombre_lista;

    @CreationTimestamp
    private Timestamp creado_desde;

    @UpdateTimestamp
    private Timestamp actualizado_desde;
    private Integer creado_por;
    private Integer actualizado_por;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private usuarios usuario;

    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<libros_listas> librosListas;


}