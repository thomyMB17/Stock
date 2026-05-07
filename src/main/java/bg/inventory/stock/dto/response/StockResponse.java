package bg.inventory.stock.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockResponse {

    private Long id;
    private int cantidad;
    private Long idProd;
    private Long idBod;
}
