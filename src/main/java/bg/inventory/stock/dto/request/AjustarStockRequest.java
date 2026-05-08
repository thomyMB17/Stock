package bg.inventory.stock.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AjustarStockRequest {
    @NotNull
    private Long idProd;
    @NotNull
    private Long idBod;
    @NotNull
    private Integer delta;
}
