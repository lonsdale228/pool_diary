package com.example.pooldiary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pooldiary.databinding.ClientRowBinding
import com.example.pooldiary.models.User

//class ClientsAdapter(private val allClients: List<User>, private val clickListener: (User) -> Unit) : RecyclerView.Adapter<ClientsAdapter.ViewHolder>() {
//
////    private var onClickListener: OnClickListener? = null
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = ClientRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int {
//        return allClients.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = allClients[position]
//
//        holder.itemView.setOnClickListener {
//            clickListener(allClients[position])
//        }
//        holder.binding.name.text = item.name
//        holder.binding.address.text = item.address
//    }
//
//    class ViewHolder(val binding: ClientRowBinding): RecyclerView.ViewHolder(binding.root){
//        val name = binding.name
//        val address = binding.address
//    }
//}

//class ClientsAdapter(private val allClients: List<User>, private val clickListener: (User) -> Unit) : RecyclerView.Adapter<ClientsAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = ClientRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        val holder = ViewHolder(binding)
//        binding.root.setOnClickListener {
//            val position = holder.adapterPosition
//            if (position != RecyclerView.NO_POSITION) {
//                clickListener(allClients[position])
//            }
//        }
//        return holder
//    }
//
//    override fun getItemCount(): Int = allClients.size
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = allClients[position]
//        holder.binding.name.text = item.name
//        holder.binding.address.text = item.address
//    }
//
//    class ViewHolder(val binding: ClientRowBinding) : RecyclerView.ViewHolder(binding.root)
//}

class ClientsAdapter(
    private val allClients: List<User>,
    private val clickListener: (User) -> Unit
) : RecyclerView.Adapter<ClientsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the custom layout for each item
        val binding = ClientRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding).apply {
            // Set up a click listener at the time of view holder creation
            binding.root.setOnClickListener {
                // Ensure the position is valid and item hasn't been removed or changed position
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener
                clickListener(allClients[position])
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind data to the views in each item
        val client = allClients[position]
        with(holder.binding) {
            name.text = client.name
            address.text = client.address
        }
    }

    override fun getItemCount(): Int = allClients.size

    // ViewHolder pattern to hold and bind views for each item
    class ViewHolder(val binding: ClientRowBinding) : RecyclerView.ViewHolder(binding.root)
}
