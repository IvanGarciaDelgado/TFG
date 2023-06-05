var cards = document.querySelectorAll('.product-box');

[...cards].forEach((card)=>{
    card.addEventListener('mouseover', function(){
        card.classList.add('is-hover');
    })
    card.addEventListener('mouseleave', function(){
        card.classList.remove('is-hover');
    })
})


function filter_by_name() {
    $.ajax({
        type: "get",
        url: "/catalogo/list/filter/",
        data: {
            nombre: $('#nombre').val()
        },
        success: function (htmlRecibido) {
            $('#lista-productos').html(htmlRecibido);

            var cards = document.querySelectorAll('.product-box');

            [...cards].forEach((card)=>{
                card.addEventListener('mouseover', function(){
                    card.classList.add('is-hover');
                })
                card.addEventListener('mouseleave', function(){
                    card.classList.remove('is-hover');
                })
            })
        },
        error: function (e) {
            console.log(e);
        }
    });
}
