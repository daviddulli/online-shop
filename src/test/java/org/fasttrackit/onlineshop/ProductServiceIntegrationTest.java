package org.fasttrackit.onlineshop;

import org.fasttrackit.onlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshop.service.ProductService;
import org.fasttrackit.onlineshop.steps.ProductTestSteps;
import org.fasttrackit.onlineshop.transfer.product.ProductResponse;
import org.fasttrackit.onlineshop.transfer.product.SaveProductRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class ProductServiceIntegrationTest {
    //field injection(injecting dependencies from IoC container; annotating the field itself)
    //field = instance variables
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTestSteps productTestSteps;

    @Test
    void testCreateProduct_whenValidRequest_thenProductIsCreated() {
        productTestSteps.createProduct();

    }


    @Test
    void createProduct_whenMissingName_thenExceptionIsThrown(){
        SaveProductRequest request = new SaveProductRequest();
        request.setQuantity(100);
        request.setPrice(300.5);

        try {
            ProductResponse product = productService.createProduct(request);
        } catch (Exception e) {
            assertThat(e, notNullValue());
            assertThat("Unexpected exception type", e instanceof TransactionSystemException);
        }

    }

    @Test
    void getProduct_whenExistingProduct_thenProductIsReturned(){
        ProductResponse product = productTestSteps.createProduct();

        ProductResponse response = productService.getProduct(product.getId());
        assertThat(response, notNullValue());
        assertThat(response.getId(), is(product.getId()));
        assertThat(response.getName(), is(product.getName()));
        assertThat(response.getPrice(), is(product.getPrice()));
        assertThat(response.getQuantity(), is(product.getQuantity()));
        assertThat(response.getDescription(), is(product.getDescription()));
        assertThat(response.getImageUrl(), is(product.getImageUrl()));

    }
    @Test
    void getProduct_whenNonExistingProduct_thenThrowResourceNotFoundException(){


        Assertions.assertThrows(ResourceNotFoundException.class, () -> productService.getProduct(99999999));

    }
    @Test
    void updateProduct_whenValidRequest_thenReturnValidProduct() {
        ProductResponse product = productTestSteps.createProduct();
        SaveProductRequest saveProductRequest = new SaveProductRequest();
        saveProductRequest.setName(product.getName() + " updated");
        saveProductRequest.setDescription(product.getDescription() + " updated");
        saveProductRequest.setPrice(product.getPrice() + 100);
        saveProductRequest.setQuantity(product.getQuantity() + 10);


        ProductResponse updatedProduct = productService.updateProduct(product.getId(), saveProductRequest);

        assertThat(updatedProduct, notNullValue());
        assertThat(updatedProduct.getId(), is(product.getId()));
        assertThat(updatedProduct.getName(), is(saveProductRequest.getName()));
        assertThat(updatedProduct.getDescription(), is(saveProductRequest.getDescription()));
        assertThat(updatedProduct.getPrice(), is(saveProductRequest.getPrice()));
        assertThat(updatedProduct.getQuantity(), is(saveProductRequest.getQuantity()));

    }

    @Test
    void deleteProduct_whenExistingProduct_thenProductDoesNotExistAnymore(){
        ProductResponse product = productTestSteps.createProduct();

        productService.deleteProduct(product.getId());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> productService.getProduct(product.getId()));


    }




}
