

// const searchField=document.getElementById("searchField");
// const contentWrapper=document.getElementById("contentWrapper");

// import {get} from "./api/api";
// import {render, html} from "./lib";


function check() {
    if (window.scrollY === 0) {
        header.classList.add("top");
        sortBtn.value="Sort";
    } else {
        header.classList.remove("top");
        sortBtn.value=String.fromCharCode(parseInt("021F5",16));
    }
}
const header = document.querySelector("header");
const sortBtn=document.querySelector("#openSortModalBtn");
const searchbar=document.querySelector("#searchForm");

window.addEventListener("scroll", check);
window.addEventListener("load", check);



// export async function search(ev, item){
//     ev.preventDefault();
//
//     const contents=await get("/" + item + "/api/search/" + searchField.textContent);
//
//     render(cardHolder(contents, item), contentWrapper);
// }
//
// const cardHolder=(contents, item)=>
// html`
// <div class="album py-5 bg-light" id="catalogCont">
//     <div class="container">
//
//         <div class="row">
//
//             ${contents.length==0
//             ? html`<p>Could not find anything here!</p>`
//             : contents.map(card, item)}
//         </div>
//     </div>
// </div>
// `
//
// const card=(card, type)=>
// html`
// <a href="home.html" class="card-link-wrap">
//     <div class="col-md-4">
//         <div class="card mb-4 box-shadow productCard">
//             <img class="card-img-top"
//                     alt="Thumbnail [100%x225]" style="height: 225px; width: 100%; display: block;"
//                     src=${card.pictures[0]}
//             >
//             <div class="card-body">
//                 <p class="card-text" text=${card.title}></p>
//                 <div class="d-flex justify-content-between align-items-center">
//                     <div class="btn-group">
//                         <a type="button"
//                             class="btn btn-sm btn-outline-secondary cardBtn detailsBtn stretched-link" href=${"/catalog/" + type + "/" + card.id}>View</a>
//                         <a type="button"
//                             class="btn btn-sm btn-outline-secondary cardBtn editBtn" href=${"/catalog/" + type + "/edit/" + card.id}>Edit</a>
//                         <a type="button"
//                             class="btn btn-sm btn-outline-secondary cardBtn deleteBtn" href=${"/catalog/" + type + "/delete/" + card.id}>Delete</a>
//                     </div>
//                 </div>
//             </div>
//         </div>
//     </div>
// </a>
// `

