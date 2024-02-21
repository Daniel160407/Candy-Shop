$("document").ready(async function () {
    const productsJsonArray = await getProductsRequest();
    const productsList = document.getElementById("productsList");

    for (let i = 0; i < productsJsonArray.length; i++) {
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

    const productInfoDiv = document.getElementById("product-info");
    const buttons = productInfoDiv.querySelectorAll("button");

    buttons.forEach(button => button.remove());

    const newButton = document.createElement("button");
    newButton.innerText = "Buy Now";
    newButton.onclick = () => onBuyButtonMouseClicked(productInfoJson.name);
    newButton.id = "buyButton";
    productInfoDiv.appendChild(newButton);

    productInfoDiv.style.display = "block";
}


async function onBuyButtonMouseClicked(name) {
    const amount = document.getElementById("amount").value;

    if (amount !== "") {
        const jsonData = await buyProductRequest(name, amount);

        if (typeof jsonData == "number") {
            alert("Your purchase has failed!");
        } else {
            document.getElementById("remainingAmount").innerText = `Available: ${jsonData.remainingAmount}`;
            document.getElementById("amount").value = "";
            document.getElementById("product-info").style.display = "none";
        }
    }
}

function exitPurchaseWindow() {
    document.getElementById("amount").value = "";
    document.getElementById("product-info").style.display = "none";
}