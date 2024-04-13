package com.example.pooldiary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pooldiary.databinding.ServiceRowBinding
import com.example.pooldiary.models.Service
class ServicesAdapter(
    private var allServices: List<Service>,
    private val clickListener: (Service) -> Unit
) : RecyclerView.Adapter<ServicesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ServiceRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener
                clickListener(allServices[position])
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val service = allServices[position]
        with(holder.binding) {
              nameService.text = service.pool_status
              addressService.text = service.note
        }
    }


    override fun getItemCount(): Int = allServices.size
    fun setData(newServices: List<Service>) {
        val diffResult = DiffUtil.calculateDiff(ServiceDiffCallback(this.allServices, newServices))
        this.allServices = newServices
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(val binding: ServiceRowBinding) : RecyclerView.ViewHolder(binding.root)
}

class ServiceDiffCallback(private val oldList: List<Service>, private val newList: List<Service>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}