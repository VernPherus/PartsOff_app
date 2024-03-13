package com.dreamdevs.partsoff_app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
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

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCheckoutBinding

    private lateinit var qty : String
    private lateinit var shipping : String
    private lateinit var subTotalPrice : String
    private lateinit var totalPrice : String


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializeProvinceSpinner()

        val bundle: Bundle? = intent.extras
        qty = bundle!!.getString("qty").toString()
        shipping = bundle.getString("shipping").toString()
        subTotalPrice = bundle.getString("subTotalPrice").toString()
        totalPrice = bundle.getString("totalPrice").toString()

        displayTotal()

        val buttonConfirmOrder: Button = findViewById(R.id.buttonConfirmOrder)
        buttonConfirmOrder.setOnClickListener {
            Toast.makeText(this, "Order Confirmed!", Toast.LENGTH_LONG).show()
            finish()
        }

        binding.backButton.setOnClickListener {
            startActivity(Intent(this@CheckoutActivity, MainActivity::class.java))
        }

    }


    @SuppressLint("SetTextI18n")
    private fun displayTotal(){
        binding.totalQtyTextView.text = "Total Quantity : $qty"
        binding.ShippingTextView.text = "Shipping Fee: ₱$shipping"
        binding.subtotalPriceTextView.text = "Subtotal Price: ₱$subTotalPrice"
        binding.totalPriceTextView.text = "₱$totalPrice"
    }

    data class Province(val code: String, val name: String)

    private fun initializeProvinceSpinner() {

        val provincesList = listOf(
        Province("ILO", "Ilocos"),
        Province("LAU", "La Union"),
        Province("PAN", "Pangasinan"),
        Province("MAS", "Masbate"),
        Province("ZMB", "Zambales"),
        Province("QZN", "Quezon "),
        Province("RIZ", "Rizal"),
        Province("NUV", "Nueva Vizcaya"),
        Province("NUE", "Nueva Ecija"),
        Province("ISA", "Isabela"),
        Province("QUI", "Quirino"),
        Province("CAV", "Cavite"),
        Province("LUN", "Laguna"),
        Province("BUL", "Bulacan"),
        Province("PAM", "Pampanga"),
        Province("ALB", "Albay"),
        Province("CAI", "Camarines"),
        Province("CAS", "Camarines Sur"),
        Province("QUE", "Quirino"),
        Province("KAL", "Kalinga"),
        Province("IFU", "Ifugao"),
        Province("ILN", "Ilocos Norte"),
        Province("ILS", "Ilocos Sur"),
        Province("MOS", "Mountain Province"),
        Province("ABR", "Abra"),
        Province("CAT", "Catanduanes"),
    )
        val adapter = ProvinceArrayAdapter(this, provincesList)
        binding.provinceSpinner.adapter = adapter

        binding.provinceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val province = adapter.getItem(position)
                Toast.makeText(this@CheckoutActivity, "Selected: ${province?.name}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Optional: Do something when nothing is selected
            }
        }
    }
        class ProvinceArrayAdapter(context: Context, provinces: List<Province>) :
            ArrayAdapter<Province>(context, 0, provinces) {

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
                createViewFromResource(position, convertView, parent, android.R.layout.simple_spinner_dropdown_item)

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View =
                createViewFromResource(position, convertView, parent, android.R.layout.simple_spinner_dropdown_item)

            private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup, resource: Int): View {
                val province = getItem(position)
                val view = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)
                val textView = view.findViewById<TextView>(android.R.id.text1)
                textView.text = province?.name
                return view
            }
        }
    }






