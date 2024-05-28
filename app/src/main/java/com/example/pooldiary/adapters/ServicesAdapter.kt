package com.example.pooldiary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pooldiary.databinding.ServiceRowBinding
import com.example.pooldiary.models.Service
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

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
            nameService.text = service.user_name
//            addressService.text = service.note
            tvLastVisit.text = "Last visit: " + service.datetime.toString()
            tvDaysAgo.text = timeDifference(service.datetime, LocalDateTime.now())
            tvItemNote.text = service.note
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

// todo unhardcode text
fun timeDifference(fromDateTime: LocalDateTime, toDateTime: LocalDateTime): String {
    val secondsBetween = ChronoUnit.SECONDS.between(fromDateTime, toDateTime)
    val minutesBetween = ChronoUnit.MINUTES.between(fromDateTime, toDateTime)
    val hoursBetween = ChronoUnit.HOURS.between(fromDateTime, toDateTime)
    val daysBetween = ChronoUnit.DAYS.between(fromDateTime, toDateTime)
    val weeksBetween = daysBetween / 7
    val monthsBetween = ChronoUnit.MONTHS.between(fromDateTime, toDateTime)

    return when {
        secondsBetween < 60 -> "$secondsBetween seconds ago"
        minutesBetween < 60 -> "$minutesBetween minutes ago"
        hoursBetween < 24 -> "$hoursBetween hours ago"
        daysBetween <= 1 -> "$daysBetween day ago"
        daysBetween <= 13 -> "$daysBetween days ago"
        daysBetween <= 30 -> {
            if (weeksBetween == 1L) "1 week ago" else "$weeksBetween weeks ago"
        }
        else -> {
            if (monthsBetween == 1L) "1 month ago" else "$monthsBetween months ago"
        }
    }
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