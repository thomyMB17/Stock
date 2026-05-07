package bg.inventory.stock.exception;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(Long idProd, int disponible, int solicitado) {
        super("Stock insuficiente para producto " + idProd +
                ". Disponible: " + disponible + ", solicitado: " + solicitado);
    }
}
