package com.example.pooldiary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pooldiary.R
import com.example.pooldiary.databinding.ChemistryItemBinding
import com.example.pooldiary.models.Chemistry
import com.example.pooldiary.models.submodels.ChemistryPriceList
import com.google.gson.Gson
import kotlin.math.roundToInt


class ChemistryAdapter(
    private var allChemistry: List<Chemistry>,
    private val clickListener: (Chemistry) -> Unit
) : RecyclerView.Adapter<ChemistryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ChemistryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                clickListener(allChemistry[position])
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chem = allChemistry[position]
        val price_weights = Gson().fromJson(chem.weights_prices, ChemistryPriceList::class.java)

        with(holder.binding) {
            lblChemName.text = chem.chemistry_name

            price_weights.weights.forEachIndexed { index, item ->
                val radioButton = RadioButton(chemistrRadiogroup.context).apply {

                    val fullname = item.toString() + price_weights.type
                    text = fullname
                    id = View.generateViewId()

                    imageView2.load("https://poolman.od.ua/wp-content/uploads/2024/02/lonhchlor_t90_5kg_1-300x300.jpg") {
                        placeholder(R.drawable.chemistry)
                        error(R.drawable.chemistry)
                    }

                    setOnClickListener {
                        val priceText = price_weights.prices[index].roundToInt().toString() + " грн"
                        tvPrice.text = priceText
                    }
                }
                if (index == 0) {
                    radioButton.isChecked = true
                }
                chemistrRadiogroup.addView(radioButton)
            }
            val priceText = price_weights.prices[0].roundToInt().toString() + " грн"
            tvPrice.text = priceText
        }
    }


    override fun getItemCount(): Int = allChemistry.size
    fun setData(newChemistrys: List<Chemistry>) {
        val diffResult =
            DiffUtil.calculateDiff(ChemistryDiffCallback(this.allChemistry, newChemistrys))
        this.allChemistry = newChemistrys
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(val binding: ChemistryItemBinding) : RecyclerView.ViewHolder(binding.root)
}

class ChemistryDiffCallback(
    private val oldList: List<Chemistry>,
    private val newList: List<Chemistry>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}