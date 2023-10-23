package usecases

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import model.Sneaker

class CartManager {
    private val _sneakers by lazy { mutableStateMapOf<String, Sneaker>() }
    private val _quantity by lazy { mutableStateMapOf<String, Int>() }
    private val _price by lazy { mutableStateMapOf<String, Int>() }
    private val _totalCartPrice by lazy { MutableStateFlow(0) }

    val sneaker: SnapshotStateMap<String, Sneaker>
        get() = _sneakers
    val quantity: SnapshotStateMap<String, Int>
        get() = _quantity
    val price: SnapshotStateMap<String, Int>
        get() = _price
    val totalCartPrice
        get() = _totalCartPrice.asStateFlow()

    suspend fun saveSneakerToCart(sneaker: Sneaker) {
        _sneakers[sneaker.id] = sneaker
        _quantity[sneaker.id] = (_quantity[sneaker.id] ?: 0) + 1
        if (!_price.containsKey(sneaker.id))
            _price[sneaker.id] = sneaker.retailPrice

        calculateTotalCart()
    }

    suspend fun deleteSneakerFromCart(sneaker: Sneaker) {
        if (_quantity.containsKey(sneaker.id) && _quantity[sneaker.id]!! > 1)
            _quantity[sneaker.id] = (_quantity[sneaker.id] ?: 0) - 1
        else
            _sneakers.remove(sneaker.id)

        calculateTotalCart()
    }

    private suspend fun calculateTotalCart() {
        coroutineScope {
            val total = _sneakers.keys.sumOf { _quantity[it]!! * _price[it]!! }
            _totalCartPrice.emit(total)
        }
    }
}