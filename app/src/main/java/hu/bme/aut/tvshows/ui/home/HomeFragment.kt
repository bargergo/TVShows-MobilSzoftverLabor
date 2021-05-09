package hu.bme.aut.tvshows.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import hu.bme.aut.tvshows.MainActivity
import hu.bme.aut.tvshows.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.textHome.text = "This is home Fragment"

        firebaseAnalytics = Firebase.analytics

        firebaseAnalytics.run {
            val bundle = Bundle().apply {
                putString(FirebaseAnalytics.Param.SCREEN_NAME, HomeFragment::class.java.simpleName)
                putString(FirebaseAnalytics.Param.SCREEN_CLASS, HomeFragment::class.java.name)
            }
            logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
        }

        return view
    }
}