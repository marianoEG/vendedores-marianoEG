package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)

  describe("Vendedor fijo") {
    val obera = Ciudad(misiones)
    val vendedorFijo = VendedorFijo(obera)

    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }
  }

  describe("Viajante") {
    val cordoba = Provincia(2000000)
    val villaDolores = Ciudad(cordoba)
    val buenosAires = Provincia(8000000)
    val viajante = Viajante(listOf(misiones))
    val viajante2 = Viajante(listOf(buenosAires,cordoba))

    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
    }
    describe("Es influyente"){
      it("Suma 10 millones de habitantes o mas entre sus provincicas habilitadas"){
        viajante2.esInfluyente().shouldBeTrue()
      }
      it("Menos de 10 millones de habitantes entre sus provincicas habilitadas"){
        viajante.esInfluyente().shouldBeFalse()
      }
    }
  }

  describe("Comercio corresponsal"){
    val ciudad1 = Ciudad(misiones)
    val ciudad2 = Ciudad(misiones)
    val ciudad3 = Ciudad(misiones)
    val ciudad4 = Ciudad(misiones)
    val ciudad5 = Ciudad(misiones)
    val corresponsal = ComercioCorresponsal(listOf(ciudad1,ciudad2,ciudad3,ciudad4,ciudad5))
    val corresponsal1 = ComercioCorresponsal(listOf(ciudad1))
    describe("puede trabajar en"){
      it("Tiene sucursal en ciudad"){
        corresponsal.puedeTrabajarEn(ciudad1).shouldBeTrue()
      }
      it("No tienen sucursal en ciudad"){
        corresponsal.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }
    describe("esInfluyente"){
      it("tiene sucursal en 5 ciudades"){
        corresponsal.esInfluyente().shouldBeTrue()
      }
      it("tiene sucursal en menos de 5"){
        corresponsal1.esInfluyente().shouldBeFalse()
      }
    }
  }
})
