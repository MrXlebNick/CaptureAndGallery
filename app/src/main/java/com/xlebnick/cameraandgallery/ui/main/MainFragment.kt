package com.xlebnick.cameraandgallery.ui.main

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.marginEnd
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.xlebnick.cameraandgallery.R
import com.xlebnick.cameraandgallery.camerautils.CameraHelper
import com.xlebnick.cameraandgallery.databinding.MainFragmentBinding
import com.xlebnick.cameraandgallery.ui.base.BaseFragment
import com.xlebnick.cameraandgallery.utils.PermissionHelper
import javax.inject.Inject

class MainFragment : BaseFragment<MainFragmentBinding>() {

    @Inject
    lateinit var cameraHelper: CameraHelper

    @Inject
    lateinit var permissionHelper: PermissionHelper

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding!!.root // safe to !! because the initiation happens in the same body
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        maybeShowGalleryButton()

        cameraHelper.bindToLifecycleOwner(viewLifecycleOwner)

        // Request camera permissions
        if (permissionHelper.allPermissionsGranted()) {
            cameraHelper.startCamera(binding?.viewFinder?.surfaceProvider)
        } else {
            requestPermissions(
                PermissionHelper.REQUIRED_PERMISSIONS,
                PermissionHelper.REQUEST_CODE_PERMISSIONS
            )
        }

        // Set up the listener for take photo button
        binding?.cameraCaptureButton?.setOnClickListener {
            // take photo with provided onSuccess callback
            cameraHelper.takePhoto { uri ->
                showNotesDialog(uri)
                maybeShowGalleryButton()
            }
        }

        binding?.galleryButton?.setOnClickListener {
            navControllerHelper.navigateTo(MainFragmentDirections.actionMainFragmentToGalleryFragment())
        }
    }

    private fun showNotesDialog(uri: Uri) {
        val editText = EditText(context).apply { hint = context.getString(R.string.your_notes) }
        AlertDialog.Builder(requireActivity())
            .setTitle(R.string.add_notes)
            .setView(editText)
            .setPositiveButton(R.string.save) { dialog, _ ->
                viewModel.saveNotes(uri, editText.text.toString())
                dialog.dismiss()
            }
            .setNegativeButton(R.string.close) { _, _ -> }
            .show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        if (permissionHelper.onCameraPermissionGranted(requestCode)) {
            cameraHelper.startCamera(binding?.viewFinder?.surfaceProvider)
        } else {
            Toast.makeText(
                requireContext(),
                R.string.permissions_not_granted,
                Toast.LENGTH_SHORT
            ).show()
            requireActivity().finish()
        }
    }

    private fun maybeShowGalleryButton() {
        binding?.galleryButton?.visibility =
            if (viewModel.shouldShowGalleryButton()) View.VISIBLE else View.GONE
    }
}