package ar.edu.unahur.obj2.vendedores

class CentroDistribucion(val ciudad: Ciudad) {
    val vendedores = mutableListOf<Vendedor>()
    fun agregarVendedor(cual: Vendedor) {
        if (vendedores.contains(cual)) {
            throw Exception("ERROR: Vendedor ya registrado")
        } else {
            vendedores.add(cual)
        }
    }

}
