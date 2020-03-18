package org.fasttrackit.onlineshop.transfer.cart;

import java.util.List;

public class AddProductsToCartRequest {

    private long costumerId;
    private List<Long> productIds;

    public long getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(long costumerId) {
        this.costumerId = costumerId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    @Override
    public String toString() {
        return "AddProductsToCartRequest{" +
                "costumerId=" + costumerId +
                ", productIds=" + productIds +
                '}';
    }
}
