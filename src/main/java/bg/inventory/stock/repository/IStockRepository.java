package bg.inventory.stock.repository;

import bg.inventory.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IStockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findStocksByIdProd(Long idProd);

    List<Stock> findStocksByIdBod(Long idBod);

    Optional<Stock> findByIdProdAndIdBod(Long idProd, Long idBod);

}
