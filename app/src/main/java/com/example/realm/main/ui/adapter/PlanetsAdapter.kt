package com.example.realm.main.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.realm.databinding.ItemPlanetBinding
import com.example.realm.main.background.response.Result

class PlanetsAdapter(val mContext: Context, private var mCallback: Callback): RecyclerView.Adapter<PlanetsAdapter.ViewHolder>() {

    private var mPlanets = ArrayList<com.example.realm.main.background.response.Result>()



    interface Callback {
        fun onItemSelected(planets: com.example.realm.main.background.response.Result, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, mContext)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mPlanets[position]
        holder.bind(item, mCallback)
    }

    override fun getItemCount(): Int {
        return mPlanets.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(planetList: ArrayList<com.example.realm.main.background.response.Result>?){
        mPlanets = planetList!!
        notifyDataSetChanged()
    }

    class ViewHolder(val mBinding: ItemPlanetBinding, val context: Context): RecyclerView.ViewHolder(mBinding.root){

        companion object {
            fun from(parent: ViewGroup, context: Context): ViewHolder {
                val binding = ItemPlanetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding, context)
            }
        }

        fun bind(item: com.example.realm.main.background.response.Result, mCallback: Callback){
            mBinding.planetName.text = item.name.toString()
            mBinding.climate.text = item.climate.toString()
            mBinding.diameter.text = item.diameter.toString()
            mBinding.gravity.text = item.gravity.toString()
            mBinding.orbital.text = item.orbitalPeriod.toString()
            mBinding.rotation.text = item.rotationPeriod.toString()
            mBinding.surface.text = item.surfaceWater.toString()
            mBinding.terrain.text = item.terrain.toString()
            mBinding.population.text = item.population.toString()

            mBinding.cardView.setOnClickListener{
                mCallback.onItemSelected(item, adapterPosition)
            }
            mBinding.executePendingBindings()
        }
    }
}