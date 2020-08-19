package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.equality.shouldBeEqualToIgnoringFields
import io.kotest.matchers.equality.shouldBeEqualToUsingFields
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.kotest.matchers.types.shouldNotBeSameInstanceAs


class CentroDistribucionTest : DescribeSpec({
    val cordoba = Provincia(2000000)
    val villaDolores = Ciudad(cordoba)
    val rioTercero = Ciudad(cordoba)
    val vendedor1 = VendedorFijo(villaDolores) // Estrella, V
    val vendedor2 = VendedorFijo(rioTercero)
    val cert1 = Certificacion(true,30)
    val cert2= Certificacion(false,20)
    vendedor1.agregarCertificacion(cert1)
    vendedor1.agregarCertificacion(cert2)
    vendedor2.agregarCertificacion(cert2)
    val centro = CentroDistribucion(villaDolores)
    val centro2 = CentroDistribucion(rioTercero)
    centro.agregarVendedor(vendedor1)
    centro.agregarVendedor(vendedor2)
    centro2.agregarVendedor(vendedor2)

    describe("Vendedor Estrella"){
        it("Debe ser el vendedor 1"){
            centro.vendedorEstrella().shouldBeSameInstanceAs(vendedor1)
        }
        it("El vendedor 2 no es estrella :("){
            centro.vendedorEstrella().shouldNotBeSameInstanceAs(vendedor2)
        }
    }

    describe("Puede Cubrir(ciudad)"){
        it("Cubre ciudad"){
            centro.puedeCubrir(villaDolores).shouldBeTrue()
        }
        it("Ciudad que no cubre"){
            centro2.puedeCubrir(villaDolores).shouldBeFalse()
        }
    }

    describe("Es robusto"){
        val firme1 = VendedorFijo(villaDolores)
        val firme2 = VendedorFijo(villaDolores)
        val firme3 = VendedorFijo(villaDolores)
        val centroRobusto = CentroDistribucion(villaDolores)
        val centroDebil = CentroDistribucion(villaDolores)
        firme1.agregarCertificacion(cert1)
        firme2.agregarCertificacion(cert1)
        firme3.agregarCertificacion(cert1)
        centroRobusto.agregarVendedor(firme1)
        centroRobusto.agregarVendedor(firme3)
        centroRobusto.agregarVendedor(firme2)
        centroDebil.agregarVendedor(firme1)
        it("Tiene 3 vendedores firmes"){
            centroRobusto.esRobusto().shouldBeTrue()
        }
        it("Tiene solo un vendedor firme"){
            centroDebil.esRobusto().shouldBeFalse()
        }
    }
    describe("Vendedores Genericos"){
        val vendedor3 = VendedorFijo(rioTercero)
        vendedor3.agregarCertificacion(cert1)
        centro.agregarVendedor(vendedor3)
        it("Vendedor 1 y 2 son genericos") {
            centro.vendedoresGenericos().shouldContainExactly(setOf(vendedor1,vendedor2))
        }
        it("Vendedor 3 no es generico"){
            centro.vendedoresGenericos().shouldNotBeSameInstanceAs(vendedor3)
        }
    }
})