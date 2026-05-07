package bg.inventory.stock.exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(Long id) {
        super("Stock con id " + id + " no encontrado");
    }
    // Constructor alternativo para busqueda por producto y bodega
    public StockNotFoundException(Long idProd, Long idBod) {
        super("No existe stock para producto " + idProd + " en bodega " + idBod);
    }
}