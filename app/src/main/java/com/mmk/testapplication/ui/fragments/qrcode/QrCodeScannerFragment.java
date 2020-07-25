package com.mmk.testapplication.ui.fragments.qrcode;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.mmk.testapplication.R;
import com.mmk.testapplication.databinding.FragmentQrCodeScannerBinding;
import com.mmk.testapplication.ui.activities.MainActivity;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;


public class QrCodeScannerFragment extends Fragment implements ZBarScannerView.ResultHandler, View.OnClickListener {

    private ZBarScannerView scannerView;
    private FragmentQrCodeScannerBinding binding;
    private Boolean isScanning=false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= FragmentQrCodeScannerBinding.inflate(inflater,container,false);
        binding.setIsScanning(isScanning);
        initView();
        setClicks();

        return binding.getRoot();
    }



    private void initView() {
        scannerView=binding.scannerView;
    }
    private void setClicks() {
        binding.imageViewQrCodeScanner.setOnClickListener(this);
        binding.buttonScan.setOnClickListener(this);
    }

    private void requestCameraPermission() {
        /*
        Dexter library is used to getting permissions from a user.
        We need Camera permission to read QR code
         */

        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        binding.setIsScanning(true);
                        scannerView.setResultHandler(QrCodeScannerFragment.this);
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(getContext(),
                                getString(R.string.denied_permission_qr_code),
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }


    @Override
    public void onDestroy() {
        scannerView.stopCamera();
        binding.setIsScanning(false);
        super.onDestroy();

    }

    /*
    * This function is called when qr code reads successfully
    *
    * */
    @Override
    public void handleResult(Result rawResult) {
        binding.setIsScanning(false);
        binding.textViewQrCodeResult.setText(rawResult.getContents());
    }

    @Override
    public void onClick(View v) {
        if (v==binding.buttonScan || v==binding.imageViewQrCodeScanner){
            requestCameraPermission();
            binding.textViewQrCodeResult.setText("");
        }
    }
}