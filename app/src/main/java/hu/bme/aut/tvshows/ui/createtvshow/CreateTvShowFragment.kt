package hu.bme.aut.tvshows.ui.createtvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.databinding.FragmentCreatetvshowBinding
import javax.inject.Inject

@AndroidEntryPoint
class CreateTvShowFragment : Fragment(), CreateTvShowContract.View {

    @Inject
    lateinit var presenter: CreateTvShowContract.Presenter

    private var _binding: FragmentCreatetvshowBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreatetvshowBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.textCreatetvshow.text = "This is create TV Show Fragment"
        binding.btnClickMe.setOnClickListener {
            presenter.onCreateTvShow("Halo, Halo")
        }
        return view
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}