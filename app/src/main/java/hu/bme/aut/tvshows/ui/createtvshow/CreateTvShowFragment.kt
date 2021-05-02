package hu.bme.aut.tvshows.ui.createtvshow

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.data.Season
import hu.bme.aut.tvshows.databinding.FragmentCreatetvshowBinding
import hu.bme.aut.tvshows.model.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
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

        binding.etPremiered.addTextChangedListener(
            object: TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val datePattern = Regex("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])\$")
                    val isValid = if (s == null) false else datePattern.matches(s)
                    if (!isValid)
                        binding.etPremiered.error = "Type a date like 2021-01-23"
                }

                override fun afterTextChanged(s: Editable?) {
                }

            }
        )

        binding.btnClickMe.setOnClickListener {
            if (!isValid())
                showMessage("There are some validation errors!")
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

    private fun isValid(): Boolean {
        return binding.etGenres.error == null &&  (binding.etGenres.text?.length ?: 0) > 0
                && binding.etImageUrl.error == null
                && binding.etPremiered.error == null && (binding.etPremiered.text?.length ?: 0) > 0
                && binding.etSummary.error == null && (binding.etSummary.text?.length ?: 0) > 0
                && binding.etTitle.error == null && (binding.etTitle.text?.length ?: 0) > 0
    }
}