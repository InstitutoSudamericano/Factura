package com.Proyecto.facturas.service

import com.Proyecto.facturas.model.client
import com.Proyecto.facturas.model.invoice
import com.Proyecto.facturas.repository.clientrepository
import com.Proyecto.facturas.repository.invoicerepository
import com.Proyecto.facturas.repository.productrepository
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap

@Service
class invoiceservice(private val repository: invoicerepository) {
    fun findAll(): List<invoice> = repository.findAll()
    fun save(invoice: invoice): invoice = repository.save(invoice)
    fun deleteById(id: Long) = repository.deleteById(id)
    fun findclientWithHighinvoices(): Any {
        TODO("Not yet implemented")
    }

    fun findTopSellingProducts(): Any {
        TODO("Not yet implemented")
    }

    companion object {
        fun listTotalMoreThan(value: Double): MultiValueMap<String, String> {
            TODO("Not yet implemented")
        }
        fun filterTotal (value: Double): List<invoice>{
            return invoicerepository.filterTotal(value)
        }

        @Service
        class clientservice(private val repository: clientrepository) {
            fun findclientWithHighInvoice(): List<client> = repository.findclientWithHighInvoice()
            // Otros métodos...
        }
        @Service
        class ProductoService(private val repository: productrepository) {
            fun findTopSellingProducts(): List<Any> = repository.findTopSellingproducts()
            // Otros métodos...
        }

    }
}
