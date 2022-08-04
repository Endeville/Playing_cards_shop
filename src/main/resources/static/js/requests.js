const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content;
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content;

document.querySelectorAll(".check").forEach(e=>e.addEventListener("click", check))

async function check(ev){
    ev.preventDefault();

    ev.currentTarget.parentElement.parentElement.style.display="none";

    const id=ev.currentTarget.parentElement.parentElement.id;

    const options={
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Accepts': 'application/json',
            [csrfHeaderName]: csrfHeaderValue
        },
        body: JSON.stringify({
            id
        })
    };

    await fetch("http://localhost:8000/api/requests/delete", options);
}