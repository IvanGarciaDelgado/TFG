function actualizarPrecioTotal(input) {
    var cantidad = parseFloat(input.value);
    var precio = parseFloat(input.parentNode.previousElementSibling.querySelector('h5').textContent.replace(',', '.'));
    var precioTotal = cantidad * precio;

    input.parentNode.nextElementSibling.querySelector('h5').textContent = precioTotal.toFixed(2);
}


