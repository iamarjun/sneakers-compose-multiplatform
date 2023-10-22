package usecases

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import model.Sneaker

class CartManager {
    private val quantity by lazy { HashMap<String, Int>() }
    private val price by lazy { HashMap<String, Int>() }
    private val _totalCartPrice by lazy { MutableStateFlow(0) }
    val totalCartPrice
        get() = _totalCartPrice.asStateFlow()

    suspend fun saveSneakerToCart(sneaker: Sneaker) {
        quantity[sneaker.id] = (quantity[sneaker.id] ?: 0) + 1
        if (!price.containsKey(sneaker.id))
            price[sneaker.id] = sneaker.retailPrice

        calculateTotalCart(sneaker)
    }

    suspend fun deleteSneakerFromCart(sneaker: Sneaker) {
        if (quantity.containsKey(sneaker.id) && quantity[sneaker.id]!! > 0)
            quantity[sneaker.id] = (quantity[sneaker.id] ?: 0) - 1

        calculateTotalCart(sneaker)
    }

    private suspend fun calculateTotalCart(sneaker: Sneaker) {
        coroutineScope {
            val total = quantity.keys.sumOf { quantity[it]!! * price[it]!! }
            _totalCartPrice.emit(total)
        }
    }
}