package com.udacity.asteroidradar.main


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.databinding.ListItemAsteroidBinding

class AsteroidAdapter(private val asteroidClickListener: AsteroidClickListener):
    ListAdapter<Asteroid, AsteroidAdapter.AsteroidViewHolder>(DiffCallback){

    class AsteroidViewHolder(var binding: ListItemAsteroidBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(asteroid: Asteroid) {
            binding.asteroid = asteroid
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        return AsteroidViewHolder(ListItemAsteroidBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val asteroid = getItem(position)
        holder.bind(asteroid)
        holder.itemView.setOnClickListener {
            asteroidClickListener.onClick(asteroid)
        }
    }


companion object DiffCallback: DiffUtil.ItemCallback<Asteroid>(){
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem.id == newItem.id
    }

}
    class AsteroidClickListener(val asteroidClickListener: (asteroid: Asteroid) -> Unit) {
        fun onClick(asteroid: Asteroid) = asteroidClickListener(asteroid)
    }


}