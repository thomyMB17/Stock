package bg.inventory.stock.service;

import bg.inventory.stock.dto.request.CreateStockRequest;
import bg.inventory.stock.dto.response.StockResponse;
import bg.inventory.stock.exception.StockInsuficienteException;
import bg.inventory.stock.exception.StockNotFoundException;
import bg.inventory.stock.model.Stock;
import bg.inventory.stock.repository.IStockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {

    private final IStockRepository stockRepository;
    // Crear stock
    public StockResponse createStock(CreateStockRequest request){
        Stock stock = Stock.builder()
                .cantidad(request.getCantidad())
                .idProd(request.getIdProd())
                .idBod(request.getIdBod())
                .build();
        Stock stockSaved = stockRepository.save(stock);
        return StockResponse.builder()
                .id(stockSaved.getId())
                .cantidad(stockSaved.getCantidad())
                .idProd(stockSaved.getIdProd())
                .idBod(stockSaved.getIdBod())
                .build();
    }
    // Todo el stock
    public List<StockResponse> getAllStock(){
        return stockRepository.findAll()
                .stream()
                .map(stock -> StockResponse.builder()
                        .id(stock.getId())
                        .cantidad(stock.getCantidad())
                        .idProd(stock.getIdProd())
                        .idBod(stock.getIdBod())
                        .build())
                .collect(Collectors.toList());
    }
    // Stock por id
    public StockResponse getById(Long id){
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new RuntimeException("Id no encontrado"));
        return StockResponse.builder()
                .id(stock.getId())
                .cantidad(stock.getCantidad())
                .idProd(stock.getIdProd())
                .idBod(stock.getIdBod())
                .build();
    }
    // Stock por producto
    public List<StockResponse> getByProduct(Long idProd){
        return stockRepository.findStocksByIdProd(idProd)
                        .stream()
                        .map(stock -> StockResponse.builder()
                                .id(stock.getId())
                                .cantidad(stock.getCantidad())
                                .idProd(stock.getIdProd())
                                .idBod(stock.getIdBod())
                                .build())
                .collect(Collectors.toList());
    }
    // Stock por bodega
    public List<StockResponse> getByBod(Long idBod){
        return stockRepository.findStocksByIdBod(idBod)
                .stream()
                .map(stock -> StockResponse.builder()
                        .id(stock.getId())
                        .cantidad(stock.getCantidad())
                        .idProd(stock.getIdProd())
                        .idBod(stock.getIdBod())
                        .build())
                .collect(Collectors.toList());
    }
    // Stock por producto y bodega
    public StockResponse getByProdBod(Long idProd, Long idBod){
        Stock stock = stockRepository.findByIdProdAndIdBod(idProd, idBod).orElseThrow(() -> new RuntimeException("Id no encontrado"));
        return StockResponse.builder()
                .id(stock.getId())
                .cantidad(stock.getCantidad())
                .idProd(stock.getIdProd())
                .idBod(stock.getIdBod())
                .build();
    }
    // actualizar Stock
    @Transactional
    public StockResponse updateStock(Long idProd, Long idBod, int delta){
        Stock stock = stockRepository.findByIdProdAndIdBod(idProd, idBod).orElseThrow(() -> new StockNotFoundException(idProd, idBod));
        int nuevaCant = stock.getCantidad() + delta;
        if (nuevaCant < 0) {
            throw new StockInsuficienteException(idProd, stock.getCantidad(), Math.abs(delta));
        }
        stock.setCantidad(nuevaCant);
        Stock stockAct = stockRepository.save(stock);
        return StockResponse.builder()
                .id(stockAct.getId())
                .cantidad(stockAct.getCantidad())
                .idProd(stockAct.getIdProd())
                .idBod(stockAct.getIdBod())
                .build();
    }
}
