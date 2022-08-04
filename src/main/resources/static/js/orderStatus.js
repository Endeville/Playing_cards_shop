const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content;
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content;
const headers={
    'Content-Type': 'application/json',
    'Accepts': 'application/json',
    [csrfHeaderName]: csrfHeaderValue
}

document.querySelectorAll(".statusInput").forEach(i=>i.addEventListener("change", changeStatus));

async function changeStatus(ev){
    ev.preventDefault();

    const id=ev.currentTarget.parentElement.id;
    const status=ev.currentTarget.id;

    const options={
        method: 'PATCH',
        headers,
        body: JSON.stringify({
            id,
            status
        })
    };

    await fetch("http://localhost:8000/api/orders/updateStatus", options);
}