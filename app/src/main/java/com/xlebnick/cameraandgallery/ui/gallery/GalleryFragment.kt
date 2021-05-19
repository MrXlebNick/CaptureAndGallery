package com.xlebnick.cameraandgallery.ui.gallery

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.xlebnick.cameraandgallery.databinding.GalleryFragmentBinding
import com.xlebnick.cameraandgallery.databinding.GalleryItemBinding
import com.xlebnick.cameraandgallery.ui.base.BaseFragment

class GalleryFragment : BaseFragment<GalleryFragmentBinding>() {

    private val viewModel: GalleryViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GalleryFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = GalleryAdapter(requireContext())
        adapter.submitList(viewModel.getGalleryContent())
        binding?.gallery?.adapter = adapter
    }
}

class GalleryAdapter(
    private val context: Context
): ListAdapter<GalleryItem, GalleryAdapter.ViewHolder>(GalleryItemDiffCallback()) {

    private var dataSet: MutableList<GalleryItem> = mutableListOf()

    override fun submitList(list: List<GalleryItem>?) {
        super.submitList(list)
        dataSet.clear()
        list?.let { dataSet.addAll(it) }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GalleryItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder constructor(
        private val binding: GalleryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GalleryItem, context: Context) {
            Glide.with(context)
                .load(Uri.parse(item.uri))
                .transform(CenterCrop())
                .into(binding.image)

            binding.text.text = item.notes
        }
    }
}

class GalleryItemDiffCallback : DiffUtil.ItemCallback<GalleryItem>() {
    override fun areItemsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean {
        return oldItem == newItem
    }
}

data class GalleryItem(val uri: String, val notes: String)
