package bg.inventory.stock.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateStockRequest {
    private int cantidad;
    private Long idProd;
    private Long idBod;
}
