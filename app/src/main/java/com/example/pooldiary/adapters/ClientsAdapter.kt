package com.example.pooldiary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pooldiary.databinding.ClientRowBinding
import com.example.pooldiary.models.User
class ClientsAdapter(
    private var allClients: List<User>,
    private val clickListener: (User) -> Unit
) : RecyclerView.Adapter<ClientsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ClientRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener
                clickListener(allClients[position])
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val client = allClients[position]
        with(holder.binding) {
            name.text = client.name
            address.text = client.address
        }
    }


    override fun getItemCount(): Int = allClients.size
    fun setData(newUsers: List<User>) {
        val diffResult = DiffUtil.calculateDiff(UserDiffCallback(this.allClients, newUsers))
        this.allClients = newUsers
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(val binding: ClientRowBinding) : RecyclerView.ViewHolder(binding.root)
}

class UserDiffCallback(private val oldList: List<User>, private val newList: List<User>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}