package com.example.nasaassignment.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nasaassignment.R
import com.example.nasaassignment.data.local.SharedPrefManager
import com.example.nasaassignment.databinding.FragmentSplashBinding
import com.example.nasaassignment.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSplashBinding.inflate(inflater, container, false)
        initViews()
        return binding.root

    }

    private fun initViews() {

        binding.lottieAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                Log.v("Animation", "Started")
            }

            override fun onAnimationEnd(animation: Animator?) {

                if (!isOnBoardingSeen()) {
                    findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
                } else {

                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                    startActivity(intent)
                    requireActivity().finish()

                }

            }

            override fun onAnimationCancel(animation: Animator?) {
                Log.v("Animation", "Canceled")
            }

            override fun onAnimationRepeat(animation: Animator?) {
                Log.v("Animation", "Repeated")
            }
        })
    }

    private fun isOnBoardingSeen(): Boolean {
        return SharedPrefManager(requireContext()).getOnboardingSeen()
    }

}
