$("document").ready(async function start() {
    const productsJsonArray = getProducts();

});

async function getProducts() {
    const response = await fetch("/candy-shop/products", {method: "GET"});
    return response.json();
}