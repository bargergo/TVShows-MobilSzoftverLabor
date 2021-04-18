package hu.bme.aut.tvshows.ui.searchtvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.databinding.FragmentSearchtvshowsBinding
import javax.inject.Inject

@AndroidEntryPoint
class SearchTvShowsFragment : Fragment(), SearchTvShowsContract.View {

    @Inject
    lateinit var presenter: SearchTvShowsContract.Presenter

    private var _binding: FragmentSearchtvshowsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchtvshowsBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.textSearchtvshows.text = "This is search TV Shows Fragment"
        binding.btnClickMe.setOnClickListener {
            presenter.search("Garfield")
        }
        return view
    }

    override fun onSearchResults(results: String) {
        Toast.makeText(activity, results, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        presenter.cleanup()
        super.onDestroy()
    }
}