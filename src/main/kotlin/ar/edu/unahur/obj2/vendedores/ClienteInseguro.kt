package ar.edu.unahur.obj2.vendedores

abstract class ClienteInseguro {
    fun mePuedeAtender(quien : Vendedor) = quien.esVersatil() and quien.esFirme()
}