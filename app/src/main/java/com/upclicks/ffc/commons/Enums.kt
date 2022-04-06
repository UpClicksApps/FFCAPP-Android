package com.upclicks.ffc.commons

enum class MessageTypes(val value: Int) {
    Normal(0),
    Location(1),
    Product(2),
    Service(3),
}

enum class VendorType(val value: Int) {
    Products(0),
    Services(1),
    ProductsAndServices(2),
}

enum class SpecificationType(val value: Int) {
    Text(0),
    Color(1),
    Image(2),
}


enum class Gender(val value: Int) {
    Female(0),
    Male(1),
    PreferNotSay(2),
}
enum class VideosSearchType(val value: Int) {
    Videos(0),
    People(1),
    Product(2),
    Shop(3)
}


enum class PostsSearchType(val value: Int) {
    Posts(0),
    Hashtags(1),
    People(2),
    Shop(3),
    Member(4)
}


enum class ProductRef(val ref: Int) {
    Normal(0),
    Search(10),
    RelatedProduct(20),
    Chat(30),
    Video(40),
    ProductActivity(50),
    Wishlist(60),
}

enum class NotificationNames(val value: String) {
    OrderCancelled ("OrderCancelled"),
    OrderConfirmed ("OrderConfirmed"),
    OrderRefused ("OrderRefused"),
    OrderAccepted ("OrderAccepted"),
    Public ("Public"),
}

enum class FcmNotificationType(final val value: Int) {
    OrderRequested  (10),
    OrderConfirmed  (20),
    OrderCancelled (30),
    OrderRefused (40),
    OrderFinished  (50),
    Public(500),
    SignOut(1000),
}
enum class OrderStatus(val value: Int) {
    Requested(0),
    Confirmed(1),
    Finished(2),
    Cancelled(100),
    Refused(200),
    Returned(300),
}
