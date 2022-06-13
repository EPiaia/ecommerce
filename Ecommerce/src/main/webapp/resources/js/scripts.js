function pesquisar() {
    var pesquisa = document.getElementById('inputBusca').value;
    if(pesquisa !== "") {
        window.location.href = "http://localhost:8080/Ecommerce/produtos.xhtml?desc=" + pesquisa;
    }
}

