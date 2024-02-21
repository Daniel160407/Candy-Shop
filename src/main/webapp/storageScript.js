$("document").ready(async function () {
    $("#addProduct").submit(async function (event) {
        event.preventDefault();

        await saveProduct();
    });
});

async function saveProduct() {
    const name = document.getElementById("productName").value;
    const amount = document.getElementById("amount").value;
    const password = document.getElementById("password").value;

    const response = await addProductsRequest(name, amount, password);
    if (response.ok) {
        alert(`Your products were successfully added: ${response.status}`)
    } else {
        alert(`Couldn\`t add your products: ${response.status}`)
    }
}