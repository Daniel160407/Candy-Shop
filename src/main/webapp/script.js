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
    price.innerText = `Price: ${productInfoJson.price}`;
    amount.innerText = `Available: ${productInfoJson.amount}`;

    document.getElementById("product-info").innerHTML += `<button onclick="onBuyButtonMouseClicked('${productInfoJson.name}')">Buy Now</button>`;
    document.getElementById("product-info").style.display = "block";
}

async function onBuyButtonMouseClicked(name) {
    const amount = document.getElementById("amount").value;
    const jsonData = await buyProductRequest(name, amount);

    document.getElementById("remainingAmount").innerText = `Available: ${jsonData.remainingAmount}`;
}

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
    return await response.json();
}