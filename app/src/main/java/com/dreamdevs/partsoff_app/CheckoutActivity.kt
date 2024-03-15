package com.dreamdevs.partsoff_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dreamdevs.partsoff_app.databinding.ActivityCheckoutBinding
import com.dreamdevs.partsoff_app.partsOffApi.RetrofitClient
import com.dreamdevs.partsoff_app.partsOffModels.checkoutModels.CheckoutRequest
import com.dreamdevs.partsoff_app.partsOffModels.checkoutModels.OrderItem
import com.dreamdevs.partsoff_app.storage.SharedPrefManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding

    private var totalqty = 0
    private var shipping = 0.0
    private var subTotalPrice= 0.0
    private var totalPrice = 0.0
    private var selectedProvince = 0

    private val allCheckouts: MutableList<MutableList<OrderItem>> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializeProvinceSpinner()

        val bundle: Bundle? = intent.extras
        totalqty = bundle!!.getInt("totalQty")
        shipping = bundle.getDouble("shipping")
        subTotalPrice = bundle.getDouble("subTotalPrice")
        totalPrice = bundle.getDouble("totalPrice")

        val cartItems: MutableList<OrderItem> = mutableListOf()
        var index = 0
        var itemKey = "item_id_$index"
        while (intent.hasExtra(itemKey)){
            val itemId = intent.getIntExtra(itemKey, 0)
            val itemQty = intent.getIntExtra("item_qty_$index", 0)
            cartItems.add(OrderItem(itemId, itemQty))
            index++
            itemKey = "item_id_$index"
        }
        val basta : MutableList<OrderItem> = cartItems

        displayTotal()

        val buttonConfirmOrder: Button = findViewById(R.id.buttonConfirmOrder)
        buttonConfirmOrder.setOnClickListener {
            Toast.makeText(this, "Order Confirmed!", Toast.LENGTH_LONG).show()
            finish()
        }

        binding.backButton.setOnClickListener {
            startActivity(Intent(this@CheckoutActivity, MainActivity::class.java))
        }

        binding.buttonConfirmOrder.setOnClickListener {
            val userEmail = SharedPrefManager.getInstance(this).user.email

            val cartItems: MutableList<OrderItem> = mutableListOf()
            // Populate cartItems with items from the cart view
            // Example: cartItems.add(OrderItem(itemId, itemQty))

            allCheckouts.add(cartItems)

            performCheckout(
                binding.editTextFirstName.text.toString(),
                binding.editTextLastName.text.toString(),
                userEmail,
                selectedProvince,
                binding.editTextAddress.text.toString(),
                binding.editTextCity.text.toString(),
                binding.editTextBarangay.text.toString(),
                binding.editZipAddress.text.toString(),
                binding.editTextPhone.text.toString(),
                subTotalPrice,
                totalPrice,
                basta
            )

            startActivity(Intent(this@CheckoutActivity, MainActivity::class.java))

        }
    }

    private fun displayTotal() {
        binding.totalQtyTextView.text = "Total Quantity : $totalqty"
        binding.ShippingTextView.text = "Shipping Fee: ₱$shipping"
        binding.subtotalPriceTextView.text = "Subtotal Price: ₱$subTotalPrice"
        binding.totalPriceTextView.text = "₱$totalPrice"
    }

    private fun performCheckout(
        firstName: String,
        lastName: String,
        email: String,
        province: Int,
        address: String,
        city: String,
        barangay: String,
        zip: String,
        mobile: String,
        subtotal: Double,
        grandTotal: Double,
        orderItems: List<OrderItem>
    ) {

        val checkoutRequest = CheckoutRequest(
            address,
            barangay,
            city,
            email,
            firstName,
            grandTotal,
            lastName,
            mobile,
            orderItems,
            province,
            subtotal,
            zip
        )

        val call = RetrofitClient.authService.processCheckout(checkoutRequest)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@CheckoutActivity, "Order saved successfully", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this@CheckoutActivity, "Order saving failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    class ProvinceArrayAdapter(context: Context, provinces: List<Province>) :
        ArrayAdapter<Province>(context, 0, provinces) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
            createViewFromResource(
                position,
                convertView,
                parent,
                android.R.layout.simple_spinner_dropdown_item
            )

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View =
            createViewFromResource(
                position,
                convertView,
                parent,
                android.R.layout.simple_spinner_dropdown_item
            )

        private fun createViewFromResource(
            position: Int,
            convertView: View?,
            parent: ViewGroup,
            resource: Int
        ): View {
            val province = getItem(position)
            val view = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)
            val textView = view.findViewById<TextView>(android.R.id.text1)
            textView.text = province?.name
            return view
        }
    }

    data class Province(val code: Int, val name: String)

    private fun initializeProvinceSpinner() {

        val provincesList = listOf(
            Province(1, "Abra"),
            Province(2, "Albay"),
            Province(3, "Antique"),
            Province(4, "Apayao"),
            Province(5, "Aurora"),
            Province(6, "Benguet"),
            Province(7, "Bulacan"),
            Province(8, "Cagayan"),
            Province(9, "Camarines Norte"),
            Province(10, "Camarines Sur"),
            Province(11, "Catanduanes"),
            Province(12, "Cavite"),
            Province(13, "Cebu"),
            Province(14, "Compostela Valley"),
            Province(15, "Davao del Norte"),
            Province(16, "Davao del Sur"),
            Province(17, "Davao Oriental"),
            Province(18, "Guimaras"),
            Province(19, "Ifugao"),
            Province(20, "Ilocos Norte"),
            Province(21, "Ilocos Sur"),
            Province(22, "Iloilo"),
            Province(23, "Isabela"),
            Province(24, "Kalinga"),
            Province(25, "Laguna"),
            Province(26, "Lanao del Norte"),
            Province(27, "Lanao del Sur"),
            Province(28, "Leyte"),
            Province(29, "La Union"),
            Province(30, "Masbate"),
            Province(31, "Mindoro Occidental"),
            Province(32, "Mindoro Oriental"),
            Province(33, "Mountain Province"),
            Province(34, "National Capital Region"),
            Province(35, "Negros Occidental"),
            Province(36, "Negros Oriental"),
            Province(37, "Northern Samar"),
            Province(38, "Nueva Ecija"),
            Province(39, "Nueva Viscaya"),
            Province(40, "Pampanga"),
            Province(41, "Pangasinan"),
            Province(42, "Quezon"),
            Province(43, "Quirino"),
            Province(44, "Rizal"),
            Province(45, "Romblon"),
            Province(46, "Sarangani"),
            Province(47, "Siquijor"),
            Province(48, "Sorsogon"),
            Province(49, "South Cotabato"),
            Province(50, "Southern Leyte"),
            Province(51, "Sulu"),
            Province(52, "Surigao del Norte"),
            Province(53, "Surigao del Sur"),
            Province(54, "Tarlac"),
            Province(55, "Tawi-Tawi"),
            Province(56, "Zamboanga del Norte"),
            Province(57, "Zamboanga del Sur"),
            Province(58, "Zambales"),
            Province(59, "Zamboanga Sibugay")
        )
        val adapter = ProvinceArrayAdapter(this, provincesList)
        binding.provinceSpinner.adapter = adapter

        binding.provinceSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    val province = adapter.getItem(position)
                    if (province != null) {
                        selectedProvince = province.code
                    }
                    Toast.makeText(
                        this@CheckoutActivity,
                        "Selected: ${province?.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Optional: Do something when nothing is selected
                }
            }

    }
}