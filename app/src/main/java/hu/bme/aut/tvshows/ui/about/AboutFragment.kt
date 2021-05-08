package hu.bme.aut.tvshows.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import hu.bme.aut.tvshows.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val view = binding.root

        firebaseAnalytics = Firebase.analytics

        firebaseAnalytics.run {
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, AboutFragment::class.java.simpleName);
            bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, AboutFragment::class.java.name);
            logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
        }

        return view
    }
}