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
    fun vendedorEstrella() = vendedores.maxBy { vend -> vend.puntajeCertificaciones() }
    fun puedeCubrir(ciudad: Ciudad) = vendedores.any { vend -> vend.puedeTrabajarEn(ciudad) }
    fun esRobusto() = vendedores.filter { vend -> vend.esFirme() }.size >= 3
    fun repartirCertificacion(cert: Certificacion){
        vendedores.forEach { vend -> vend.agregarCertificacion(cert) }
    }
    fun vendedoresGenericos() = vendedores.filter { it.esGenerico() }.toSet()



}




