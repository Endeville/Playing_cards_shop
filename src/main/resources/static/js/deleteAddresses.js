const deleteAddressBtns=document.querySelectorAll(".delete-address");
const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content;
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content;

for (const deleteAddressBtn of deleteAddressBtns) {
    deleteAddressBtn.addEventListener("click", (ev)=>{
        ev.preventDefault();
        const id=Number(ev.currentTarget.id.substring(7));
        deleteAddress(ev, id);
    })
}

async function deleteAddress(ev, id){
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

    const target=ev.currentTarget;

    const res=await fetch("http://localhost:8000/api/addresses/delete", options);

    if(res.status!==409){
        target.parentElement.style.display='none';
    }else{
        target.parentElement.textContent=target.parentElement.textContent.concat(" - Cannot delete this address");
        target.style.display='none';
    }
}