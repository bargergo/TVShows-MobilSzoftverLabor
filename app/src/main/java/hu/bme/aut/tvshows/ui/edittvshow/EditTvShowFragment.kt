package hu.bme.aut.tvshows.ui.edittvshow

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.data.ShowWithSeasonsAndEpisodesAndCast
import hu.bme.aut.tvshows.databinding.FragmentEdittvshowBinding
import hu.bme.aut.tvshows.model.Image
import hu.bme.aut.tvshows.model.ShowData
import org.threeten.bp.LocalDate
import javax.inject.Inject
import kotlin.properties.Delegates

@AndroidEntryPoint
class EditTvShowFragment: Fragment(), EditTvShowContract.View {

    @Inject
    lateinit var presenter: EditTvShowContract.Presenter

    var showId: Long by Delegates.notNull<Long>()

    lateinit var showData: ShowData

    private lateinit var firebaseAnalytics: FirebaseAnalytics

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


        showId = arguments?.getLong("showId")!!

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
            if (isValid()) {
                val image = if (binding.etImageUrl.text?.length ?: 0 > 0)
                    Image(
                        binding.etImageUrl.text.toString(),
                        binding.etImageUrl.text.toString()
                    )
                else
                    null
                presenter.updateShow(
                    showId,
                    ShowData(
                        binding.etTitle.text.toString(),
                        "Show",
                        "English",
                        binding.etGenres.text?.split(",")?.map { it.trim() } ?: emptyList(),
                        "Running",
                        30,
                        LocalDate.parse(binding.etPremiered.text.toString()),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        image,
                        binding.etSummary.text.toString()
                    )
                )
            } else {
                showMessage("There are some validation errors!")
            }
        }
        presenter.loadShowData(showId)

        firebaseAnalytics = Firebase.analytics

        firebaseAnalytics.run {
            val bundle = Bundle().apply {
                putString(FirebaseAnalytics.Param.SCREEN_NAME, EditTvShowFragment::class.java.simpleName)
                putString(FirebaseAnalytics.Param.SCREEN_CLASS, EditTvShowFragment::class.java.name)
            }


            logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
        }

        return view
    }

    override fun onShowDataLoaded(data: ShowWithSeasonsAndEpisodesAndCast) {
        binding.etTitle.setText(data.show.name)
        binding.etGenres.setText(data.show.genres)
        binding.etPremiered.setText(data.show.premier.toString())
        binding.etImageUrl.setText(data.show.imageUrl)
        binding.etSummary.setText(data.show.summary)
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

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}