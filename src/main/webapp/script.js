$("document").ready(async function () {
    const productsJsonArray = await getProductsRequest();
    const productsList = document.getElementById("productsList");

    for (let i = 0; i < productsJsonArray.length; i++) {
        console.log(productsJsonArray[i].name);
        productsList.innerHTML += `<li><h4>${productsJsonArray[i].name}</h4>
<button id="${i + 1}" onclick="onPurchaseButtonMouseClicked(${i + 1})">Purchase</button></li>`
    }
});

async function onPurchaseButtonMouseClicked(id) {
    const productInfoJson = await getProductInfoRequest(id);

    const product = document.getElementById("productName");
    const price = document.getElementById("price");
    const amount = document.getElementById("remainingAmount");

    product.innerText = productInfoJson.name;
    product.innerText = `Price: ${productInfoJson.price}`;
    product.innerText = `Available: ${productInfoJson.amount}`;

    document.getElementById("product-info").style.display = "block";
}

async function onBuyButtonMouseClicked() {

}

async function getProductsRequest() {
    const response = await fetch("/candy-shop/store", {method: "GET"});
    console.log(1);
    return await response.json();
}

async function getProductInfoRequest(id) {
    const response = await fetch(`/candy-shop/products?id=${id}`, {method: "GET"});
    console.log(2);
    return await response.json();
}