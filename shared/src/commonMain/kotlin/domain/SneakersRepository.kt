package domain

import kotlinx.serialization.json.Json
import model.Sneaker
import network.SneakersApiImp

class SneakersRepository(private val json: Json, private val sneakersApi: SneakersApiImp) {

    suspend fun getSneakerList(): Result<List<Sneaker>> {
        return try {
//            val list = sneakersApi.getSneakerList()
            val list = json.decodeFromString<List<Sneaker>>(response)
            Result.success(list)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

private val response = """
    [
      {
        "lowestResellPrice": {
          "stockX": 94,
          "flightClub": 109,
          "goat": 105
        },
        "imageLinks": [],
        "_id": "65354d5d02f7da546540762a",
        "shoeName": "Nike Jordan Air Ship PE SP Corporate Got 'Em",
        "brand": "Nike",
        "silhoutte": "Nike Jordan Air Ship PE SP",
        "styleID": "FJ2384-301",
        "make": "Nike Jordan Air Ship PE SP",
        "colorway": "Light Menta/Metallic Silver/Kinetic Green/Sail",
        "retailPrice": 150,
        "thumbnail": "https://images.stockx.com/images/Nike-Air-Jordan-Air-Ship-PE-SP-Corporate-Got-Em-Product.jpg?fit=fill&bg=FFFFFF&w=700&h=500&fm=webp&auto=compress&trim=color&q=90&dpr=2&updated_at=1694532865",
        "releaseDate": "2023-09-08",
        "description": "Honoring the 15th anniversary of the Midwest retailer, the Corporate x Jordan Air Ship PE SP '15th Anniversary' features vibrant teal upper in shaggy suede suede with sleek leather forefoot and heel overlays and a white-outlined Swoosh. The tongue is adorned with a woven Nike tag, the Corporate logo appears on the lateral heel and 'Got 'Em' is stitched on the rear collar. Underfoot, a grey outsole supports a sturdy rubber cupsole with white sidewalls. A nod to Cincinnati, the locale where Corporate founder Matt Tomamichel established his inaugural store, is displayed inside the shoe with 'For the City' etched on the interior collar.",
        "urlKey": "nike-air-jordan-air-ship-pe-sp-corporate-got-em",
        "resellLinks": {
          "stockX": "https://stockx.com/nike-air-jordan-air-ship-pe-sp-corporate-got-em",
          "flightClub": "https://www.flightclub.com/corporate-x-jordan-air-ship-pe-sp-light-menta-fj2384-301",
          "goat": "http://www.goat.com/sneakers/corporate-x-jordan-air-ship-pe-sp-light-menta-fj2384-301"
        },
        "goatProductId": 1264219
      },
      {
        "lowestResellPrice": {
          "stockX": 95,
          "flightClub": 101,
          "goat": 94
        },
        "imageLinks": [],
        "_id": "65354d5d02f7dadafa40762b",
        "shoeName": "Nike Jordan Air Ship PE SP A Ma Maniére White Black",
        "brand": "Nike",
        "silhoutte": "Nike Jordan Air Ship PE SP",
        "styleID": "DX4976-100",
        "make": "Nike Jordan Air Ship PE SP",
        "colorway": "Summit White/Black",
        "retailPrice": 140,
        "thumbnail": "https://images.stockx.com/images/Nike-Air-Jordan-Air-Ship-PE-SP-A-Ma-Maniere-White-Black-Product.jpg?fit=fill&bg=FFFFFF&w=700&h=500&fm=webp&auto=compress&trim=color&q=90&dpr=2&updated_at=1692214085",
        "releaseDate": "2023-08-03",
        "description": "The A Ma Maniére x Jordan Air Ship PE SP 'White Black’ is a high-top that showcases a white leather upper intersected by the signature Swoosh and padded collar in bold black. An interesting twist awaits at the rear of the padded collar, where alternating branding appears on the right shoe with 'Nike Air’ and A Ma Maniére's distinctive 'A' logo on the left. The shoe's breathable nylon tongue features a dual-branded woven tag. The sneaker exhibits pre-yellowed sidewalls and an aged black rubber outsole beneath a robust rubber cupsole, completing the vintage-inspired look.",
        "urlKey": "nike-air-jordan-air-ship-pe-sp-a-ma-maniere-white-black",
        "resellLinks": {
          "stockX": "https://stockx.com/nike-air-jordan-air-ship-pe-sp-a-ma-maniere-white-black",
          "flightClub": "https://www.flightclub.com/a-ma-maniere-x-jordan-air-ship-pe-sp-white-black-dx4976-100",
          "goat": "http://www.goat.com/sneakers/a-ma-maniere-x-jordan-air-ship-pe-sp-white-black-dx4976-100"
        },
        "goatProductId": 1251345
      },
      {
        "lowestResellPrice": {
          "stockX": 101,
          "flightClub": 89,
          "goat": 89
        },
        "imageLinks": [],
        "_id": "65354d5d02f7da498e40762c",
        "shoeName": "Air Jordan 1 Mid Tartan Swoosh",
        "brand": "Jordan",
        "silhoutte": "Air Jordan 1 Mid",
        "styleID": "DZ5329-001",
        "make": "Air Jordan 1 Mid",
        "colorway": "Black/Multi-Color-Sesame-Fire Red-White",
        "retailPrice": 135,
        "thumbnail": "https://images.stockx.com/images/Air-Air-Jordan-1-Mid-Tartan-Swoosh-Product.jpg?fit=fill&bg=FFFFFF&w=700&h=500&fm=webp&auto=compress&trim=color&q=90&dpr=2&updated_at=1668754099",
        "releaseDate": "2022-11-10",
        "description": "",
        "urlKey": "air-air-jordan-1-mid-tartan-swoosh",
        "resellLinks": {
          "stockX": "https://stockx.com/air-air-jordan-1-mid-tartan-swoosh",
          "flightClub": "https://www.flightclub.com/air-jordan-1-mid-se-tartan-swoosh-dz5329-001",
          "goat": "http://www.goat.com/sneakers/air-jordan-1-mid-se-tartan-swoosh-dz5329-001"
        },
        "goatProductId": 1061826
      },
      {
        "lowestResellPrice": {
          "stockX": 104,
          "flightClub": 92,
          "goat": 85
        },
        "imageLinks": [],
        "_id": "65354d5d02f7daaf0e40762d",
        "shoeName": "Nike Jordan Air Ship PE SP Every Game Diffused Blue",
        "brand": "Nike",
        "silhoutte": "Nike Jordan Air Ship PE SP",
        "styleID": "DZ3497-104",
        "make": "Nike Jordan Air Ship PE SP",
        "colorway": "Summit White/Diffused Blue/Chambray/Sail",
        "retailPrice": 140,
        "thumbnail": "https://images.stockx.com/images/Nike-Air-Jordan-Air-Ship-PE-SP-Every-Game-Product.jpg?fit=fill&bg=FFFFFF&w=700&h=500&fm=webp&auto=compress&trim=color&q=90&dpr=2&updated_at=1685115806",
        "releaseDate": "2023-05-10",
        "description": "The Jordan Air Ship PE SP ‘Every Game - UNC’ features a colorway that will be familiar to fans, drawing inspiration from Michael Jordan's UNC practice shorts worn under his Chicago Bulls uniform. The retro basketball shoe features a white leather upper with matching stitching and a toe box with perforations for breathability. The iconic Swoosh, collar and eyestay are covered in a light blue, shaggy suede material with 'Every Game' stitched on the back. The textured midsole has a vintage look, and the blue rubber outsole provides excellent traction on the hardwood.",
        "urlKey": "nike-air-jordan-air-ship-pe-sp-every-game",
        "resellLinks": {
          "stockX": "https://stockx.com/nike-air-jordan-air-ship-pe-sp-every-game",
          "flightClub": "https://www.flightclub.com/nigel-sylvester-x-air-ship-bike-air-dz3497-104",
          "goat": "http://www.goat.com/sneakers/nigel-sylvester-x-air-ship-bike-air-dz3497-104"
        },
        "goatProductId": 1165819
      },
      {
        "lowestResellPrice": {
          "stockX": 54,
          "flightClub": 57,
          "goat": 50
        },
        "imageLinks": [],
        "_id": "65354d5d02f7dacfea40762e",
        "shoeName": "Nike Jordan Air Ship PE SP Tech Grey",
        "brand": "Nike",
        "silhoutte": "Nike Jordan Air Ship PE SP",
        "styleID": "DZ3497-100",
        "make": "Nike Jordan Air Ship PE SP",
        "colorway": "Summit White/Gumsmoke-Tech Grey",
        "retailPrice": 140,
        "thumbnail": "https://images.stockx.com/images/Nike-Air-Jordan-Air-Ship-PE-SP-Tech-Grey-Product.jpg?fit=fill&bg=FFFFFF&w=700&h=500&fm=webp&auto=compress&trim=color&q=90&dpr=2&updated_at=1686725571",
        "releaseDate": "2023-07-01",
        "description": "Showcasing a timeless and versatile color scheme, the Jordan Air Ship PE SP 'Tech Grey' reimagines the iconic '80s basketball shoe that Michael Jordan briefly wore during his rookie NBA season. Textured grey suede detailing on the signature Swoosh and collar highlight the upper in sleek white leather. The collar also features embroidered Nike Air branding at the back, while the breathable nylon tongue is adorned with a woven Nike tag. Underfoot, a durable cupsole delivers support and features clean white sidewalls and a grippy grey rubber outsole for traction on the court.",
        "urlKey": "nike-air-jordan-air-ship-pe-sp-tech-grey",
        "resellLinks": {
          "stockX": "https://stockx.com/nike-air-jordan-air-ship-pe-sp-tech-grey",
          "flightClub": "https://www.flightclub.com/jordan-air-ship-pe-sp-tech-grey-dz3497-100",
          "goat": "http://www.goat.com/sneakers/jordan-air-ship-pe-sp-tech-grey-dz3497-100"
        },
        "goatProductId": 1214624
      }
    ]
""".trimIndent()