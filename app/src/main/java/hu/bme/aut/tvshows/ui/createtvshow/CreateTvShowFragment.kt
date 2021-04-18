package hu.bme.aut.tvshows.ui.createtvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.databinding.FragmentCreatetvshowBinding
import hu.bme.aut.tvshows.model.*
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
            presenter.onCreateTvShow(
                ShowData(
                "Halo, halo",
                "Series",
                "English",
                listOf("Comedy"),
                "Finished",
                153654,
                "1989-12-01",
                "asdasd.as",
                Schedule(
                    "12:00",
                    listOf("Monday")
                ),
                Rating(8.24f),
                1321,
                Network(13, "BBC", Country("United Kingdom", "UK", "GMT0")),
                null,
                null,
                Externals(null, null, null),
                null,
                "It's a good a show",
                8545410
                )
            )
        }
        return view
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        presenter.cleanup()
        super.onDestroy()
    }
}