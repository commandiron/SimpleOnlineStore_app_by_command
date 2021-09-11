package com.demirli.a51simpleonlinestore.data

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.demirli.a51simpleonlinestore.model.Cart
import com.demirli.a51simpleonlinestore.model.CartDetail
import com.demirli.a51simpleonlinestore.model.Product

@Database(entities = arrayOf(Product::class, Cart::class, CartDetail::class), version = 1)
abstract class ShopDb: RoomDatabase() {
    abstract fun shopDao(): ShopDao

    companion object{

        var INSTANCE: ShopDb? = null

        fun getInstance(context: Context): ShopDb{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ShopDb::class.java,
                    "shop_db")
                    .addCallback(roomDbCallback)
                    .build()

            }
            return INSTANCE as ShopDb
        }

        private val roomDbCallback = object: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateAsyncTask(INSTANCE).execute()
            }
        }
        class PopulateAsyncTask(private val db: ShopDb?): AsyncTask<Void, Void, Void>() {
            private val dao: ShopDao? by lazy { db?.shopDao() }
            override fun doInBackground(vararg params: Void?): Void? {

                var product = Product(
                    product_name = "iPad 7. Nesil 10.2\" 32 GB Wifi Tablet  MW742TU/A",
                    product_thumbnail_url = "https://cdn.cimri.io/image/1000x1000/appleipadgbwifi_190733720.jpg",
                    product_short_description= "Short Description",
                    product_long_description = "Long Description",
                    product_price = 2558.18f,
                    quantity = 1

                )

                dao?.addProduct(product)

                product = Product(
                    product_name = "iPad 6. Nesil",
                    product_thumbnail_url = "https://productimages.hepsiburada.net/s/33/1500/10389868314674.jpg",
                    product_short_description= "Short Description",
                    product_long_description = "Long Description",
                    product_price = 2458.18f,
                    quantity = 1
                )

                dao?.addProduct(product)

                product = Product(
                    product_name = "iPad 5. Nesil",
                    product_thumbnail_url = "https://productimages.hepsiburada.net/s/33/1500/10389868314674.jpg",
                    product_short_description= "Short Description",
                    product_long_description = "Long Description",
                    product_price = 2358.18f,
                    quantity = 1
                )

                dao?.addProduct(product)

                product = Product(
                    product_name = "iPhone SE 64 GB",
                    product_thumbnail_url = "https://cdn.istanbulbilisim.com/pd/0/apple-iphone-se-64-gb-2020-siyah-apple-turkiye-garantili-cep_301359_1590835582_3996.jpg",
                    product_short_description= "Short Description",
                    product_long_description = "Long Description",
                    product_price = 5199.91f,
                    quantity = 1
                )

                dao?.addProduct(product)

                product = Product(
                    product_name = "Awox 32\" 82 Ekran A203200 Uydu Alıcılı HD LED TV",
                    product_thumbnail_url = "https://cdn.akakce.com/awox/awox-3282-32-inc-hd-32-82-ekran-led-televizyon-z.jpg",
                    product_short_description= "Short Description",
                    product_long_description = "Long Description",
                    product_price = 878.00f,
                    quantity = 1
                )

                dao?.addProduct(product)

                return null
            }
        }

    }
}