package bg.inventory.stock.controller;

import bg.inventory.stock.dto.request.AjustarStockRequest;
import bg.inventory.stock.dto.request.CreateStockRequest;
import bg.inventory.stock.dto.response.StockResponse;
import bg.inventory.stock.model.Stock;
import bg.inventory.stock.service.StockService;
import jakarta.servlet.ServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/stock")
@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    // GET MAPPING's
    @GetMapping("/{id}")
    public ResponseEntity<StockResponse> getStockById(@PathVariable Long id){
        return ResponseEntity.ok(stockService.getById(id));
    }
    @GetMapping("/allStock")
    public ResponseEntity<List<StockResponse>> getAllStock(){
        return ResponseEntity.ok(stockService.getAllStock());
    }
    @GetMapping("/bod/{idBod}")
    public ResponseEntity<List<StockResponse>> getStockByIdBod(@PathVariable Long idBod){
        return ResponseEntity.ok(stockService.getByBod(idBod));
    }
    @GetMapping("/prod/{idProd}")
    public ResponseEntity<List<StockResponse>> getStockByProduct(@PathVariable Long idProd){
        return ResponseEntity.ok(stockService.getByProduct(idProd));
    }
    @GetMapping("/idBodega/{idBod}/idProducto/{idProd}")
    public ResponseEntity<StockResponse> getStockByProdBod(@PathVariable Long idBod, @PathVariable Long idProd){
        return ResponseEntity.ok(stockService.getByProdBod(idProd, idBod));
    }
    //POST MAPPING's
    @PostMapping("/create")
    public ResponseEntity<StockResponse> createStock(@RequestBody @Valid CreateStockRequest request){
        return ResponseEntity.ok(stockService.createStock(request));
    }
    // PATCH MAPPING's
    @PatchMapping("/ajustar")
    public ResponseEntity<StockResponse> updateStock(@RequestBody @Valid AjustarStockRequest request) {
        return ResponseEntity.ok(stockService.updateStock(request.getIdProd(), request.getIdBod(), request.getDelta()));
    }
}
