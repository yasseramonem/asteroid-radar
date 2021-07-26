package com.udacity.asteroidradar.main


import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class AsteroidAdapter(val onClickListener: OnClickListener):
    ListAdapter<Asteroid, AsteroidAdapter.AsteroidViewHolder>(DiffCallback){

    class AsteroidViewHolder(var binding: FragmentMainBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


companion object DiffCallback: DiffUtil.ItemCallback<Asteroid>(){
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem.id == newItem.id
    }

}
    class OnClickListener(val clickListener: (asteroid: Asteroid) -> Unit) {
        fun onClick(asteroid: Asteroid) = clickListener(asteroid)
    }


}