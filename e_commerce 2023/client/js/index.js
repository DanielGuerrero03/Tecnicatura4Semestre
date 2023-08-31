const shopContent = document.getElementById('shopContent');
const cart = [];

productos.forEach((producto) => {
    const content = document.createElement('div');
    content.innerHTML = `
    <img src="${producto.img}">
    <h3>${producto.productName}</h3>
    <p>$${producto.price}</p>
    `;
    shopContent.append(content);

    const buyButton = document.createElement('button');
    buyButton.innerText = 'Comprar';
    
    content.append(buyButton);

    buyButton.addEventListener('click', () => {
        cart.push({
            id: producto.id,
            productName: producto.productName,
            price: producto.price,
            quanty: producto.quanty,
            img: producto.img,

        })
        console.log(cart);
});
});