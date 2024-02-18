$("document").ready(async function () {
    const productsJsonArray = await getProducts();
    const productsList = document.getElementById("productsList");

    for (let i = 0; i < productsJsonArray.length; i++) {
        console.log(productsJsonArray[i].prod_name);
        productsList.innerHTML += `<li><h4>${productsJsonArray[i].prod_name}</h4>
<button id="${productsJsonArray[i].prod_id}" onclick="onPurchaseButtonMouseClicked(${productsJsonArray[i].prod_id})">Purchase</button></li>`
    }
});

async function getProducts() {
    const response = await fetch("/candy-shop/products", {method: "GET"});
    return await response.json();
}

async function onPurchaseButtonMouseClicked(id) {

}