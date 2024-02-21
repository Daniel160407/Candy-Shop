async function getProductsRequest() {
    const response = await fetch("/candy-shop/store", {method: "GET"});
    return await response.json();
}

async function getProductInfoRequest(id) {
    const response = await fetch(`/candy-shop/store/products?id=${id}`, {method: "GET"});
    return await response.json();
}

async function buyProductRequest(name, amount) {
    const response = await fetch(`/candy-shop/store/products?name=${name}&amount=${amount}`, {method: "POST"});
    if (response.ok) {
        return await response.json();
    } else {
        return response.status;
    }
}

async function addProductsRequest(name, amount, password) {
    return await fetch(`/candy-shop/store/products?name=${name}&amount=${amount}&password=${password}`, {method: "PUT"});
}