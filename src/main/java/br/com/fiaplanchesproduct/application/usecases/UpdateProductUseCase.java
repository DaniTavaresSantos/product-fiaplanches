package br.com.fiaplanchesproduct.application.usecases;

import br.com.fiaplanchesproduct.application.dtos.ProductDto;
import br.com.fiaplanchesproduct.application.ports.out.ProductRepositoryPortOut;
import jakarta.persistence.EntityNotFoundException;

public class UpdateProductUseCase {

    private final ProductRepositoryPortOut productRepositoryPortOut;

    public UpdateProductUseCase(ProductRepositoryPortOut productRepositoryPortOut) {
        this.productRepositoryPortOut = productRepositoryPortOut;
    }

    public ProductDto updateProduct(ProductDto novoProdutoDTO, Long id) {
        ProductDto productDto = productRepositoryPortOut.findProductById(id).orElseThrow(
                () -> new EntityNotFoundException("Produto não encontrado")
        );
        var product = productDto.toProduct();
        var productUpdate = product.updateProduct(novoProdutoDTO.toProduct());
        return productRepositoryPortOut.saveProduct(ProductDto.toProductDto(productUpdate));
    }
}
