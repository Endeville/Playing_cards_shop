const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content;
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content;
const headers={
    'Content-Type': 'application/json',
    'Accepts': 'application/json',
    [csrfHeaderName]: csrfHeaderValue
}
const totalPrice=document.getElementById("total-price");

const minusBtns=document.querySelectorAll(".minus");
minusBtns.forEach(e=>e.addEventListener("click", minus));

async function minus(ev){
    ev.preventDefault();

    const offerId=Number(ev.currentTarget.parentElement.parentElement.querySelector(".product").id);
    const priceCont=ev.target.parentElement.parentElement.querySelector(".product-price");

    const parent=ev.target.parentElement;
    const quantitySpan=parent.querySelector("span");
    const quantity=Number(quantitySpan.textContent);

    const options={
        method: 'PUT',
        headers,
        body: JSON.stringify({
            offerId,
            quantity,
            operation: 'minus'
        })
    };

    const response=await fetch("http://localhost:8000/api/cart/update", options);
    const result=await response.json();

    quantitySpan.textContent=result.quantity;

    if(result.quantity===0){
        ev.target.disabled=true;
    }

    parent.querySelector(".plus").disabled=false;
    priceCont.textContent=result.price.toFixed(2) + "€";
    totalPrice.textContent=String((Number(getPrice(totalPrice.textContent))-result.pricePerProduct).toFixed(2)) + "€";
}

const plusBtns=document.querySelectorAll(".plus");
plusBtns.forEach(e=>e.addEventListener("click", plus));


async function plus(ev){
    ev.preventDefault();

    const offerId=Number(ev.currentTarget.parentElement.parentElement.querySelector(".product").id);
    const priceCont=ev.target.parentElement.parentElement.querySelector(".product-price");

    const parent=ev.target.parentElement;
    const quantitySpan=parent.querySelector("span");
    const quantity=Number(quantitySpan.textContent);

    const options={
        method: 'PUT',
        headers,
        body: JSON.stringify({
            offerId,
            quantity,
            operation: 'plus'
        })
    };

    const response=await fetch("http://localhost:8000/api/cart/update", options);
    const result=await response.json();

    quantitySpan.textContent=result.quantity;

    if(result.quantity===1){
        parent.querySelector(".minus").disabled=false;
    }

    if(result.quantity===result.maxQuantity){
        parent.querySelector(".plus").disabled=true;
    }

    priceCont.textContent=result.price.toFixed(2) + "€";
    totalPrice.textContent=String((Number(getPrice(totalPrice.textContent))+result.pricePerProduct).toFixed(2)) + "€";
}

function getPrice(price){
    return Number(price.substring(0, price.length-1));
}