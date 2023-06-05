let mostrador = document.getElementById("mostrador");
let seleccion = document.getElementById("seleccion");
let imgSeleccionada = document.getElementById("img");
let modeloSeleccionado = document.getElementById("modelo");
let descripSeleccionada = document.getElementById("descripcion");
let precioSeleccionado = document.getElementById("precio");

// Función que carga la info del item seleccionado

function cargar (item) {

    let enlace = item.dataset.enlace;

    quitarBordes();
    mostrador.style.width= "60%";
    seleccion.style.width= "40%";
    seleccion.style.opacity= "1";
    item.style.border = "2px solid #7b6dff";

    imgSeleccionada.src = item.getElementsByTagName("img")[0].src;

    modeloSeleccionado.innerHTML = item.getElementsByTagName("p")[0].innerHTML;

    descripSeleccionada.innerHTML = item.getElementsByTagName("h1")[0].innerHTML;

    precioSeleccionado.innerHTML = item.getElementsByTagName("span")[0].innerHTML;

    let enlaceElemento = document.querySelector(".info a");
    enlaceElemento.href = enlace;

}

function quitarBordes() {
    var items = document.getElementsByClassName("item");
    for(i=0; i < items.length; i++) {
        items[i].style.border = "none";
    }
}
function cerrar() {
    mostrador.style.width = "100%";
    seleccion.style.width = "0%";
    seleccion.style.opacity = "0%";
    quitarBordes();
}