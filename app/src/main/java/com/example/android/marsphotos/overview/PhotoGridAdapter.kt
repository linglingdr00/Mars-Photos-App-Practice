package com.example.android.marsphotos.overview


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.databinding.GridViewItemBinding
import com.example.android.marsphotos.network.MarsPhoto

class PhotoGridAdapter: ListAdapter<MarsPhoto,
        PhotoGridAdapter.MarsPhotoViewHolder>(DiffCallback) {

    companion object DiffCallback: DiffUtil.ItemCallback<MarsPhoto>() {
        // 判定兩個 objects 是否為同一個 item
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            // 判斷新的 MarsPhoto object 是否和舊的 MarsPhoto object 相同
            return oldItem == newItem
        }

        // 檢查兩個 items 是否擁有相同的 data
        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            // 比較 oldItem 和 newItem 的 URLs 是否相同
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }

    }

    // 使用 GridViewItemBinding 變數將 MarsPhoto binding 至 layout
    class MarsPhotoViewHolder(private var binding: GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(MarsPhoto: MarsPhoto) {
                // 將 binding.property 設為該 object
                binding.photo = MarsPhoto
                // 執行更新作業
                binding.executePendingBindings()
            }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridAdapter.MarsPhotoViewHolder {
        /* 使用父項(parent) ViewGroup context 中的 LayoutInflater 加載 GridViewItemBinding，
           return 新的 MarsPhotoViewHolder */
        return MarsPhotoViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.MarsPhotoViewHolder, position: Int) {
        // 取得與目前 RecyclerView 位置相關的 MarsPhoto object
        val marsPhoto = getItem(position)
        // 傳遞至 MarsPhotoViewHolder 中的 bind() 方法
        holder.bind(marsPhoto)
    }
}