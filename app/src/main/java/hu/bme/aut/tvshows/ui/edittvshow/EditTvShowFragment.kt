package hu.bme.aut.tvshows.ui.edittvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.databinding.FragmentEdittvshowBinding

@AndroidEntryPoint
class EditTvShowFragment: Fragment(), EditTvShowContract.View {

    private var _binding: FragmentEdittvshowBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEdittvshowBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}