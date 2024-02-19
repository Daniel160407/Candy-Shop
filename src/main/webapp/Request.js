class Request {
    async getProductsRequest() {
        const response = await fetch("/candy-shop/store", {method: "GET"});
        return await response.json();
    }

    async getProductInfoRequest(id) {
        const response = await fetch(`/candy-shop/products?id=${id}`, {method: "GET"});
        return await response.json();
    }
}

export default Request;