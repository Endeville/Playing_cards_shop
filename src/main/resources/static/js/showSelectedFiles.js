const fileInput=document.getElementById("formFile");
const filenamesCont=document.querySelector("#image-container>tbody");

fileInput.addEventListener("change", (ev)=>{
    ev.preventDefault();

    let children="";

    for (let i = 0; i <fileInput.files.length ; i++) {
        children+="<tr><td>"+fileInput.files.item(i).name+"</td></tr>";
    }

    filenamesCont.innerHTML=children;
})