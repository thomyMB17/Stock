package bg.inventory.stock.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "stock", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"idProd", "idBod"})
})
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    private int cantidad;
    @NotNull
    private Long idProd;
    @NotNull
    private Long idBod;
}
