package hu.bme.aut.tvshows.ui.tvshowdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.R
import hu.bme.aut.tvshows.databinding.FragmentTvshowdetailBinding
import hu.bme.aut.tvshows.model.Cast
import hu.bme.aut.tvshows.model.Season
import hu.bme.aut.tvshows.model.ShowDetails
import hu.bme.aut.tvshows.model.ShowSearchResult
import hu.bme.aut.tvshows.ui.searchtvshows.TvShowListAdapter
import hu.bme.aut.tvshows.util.stripHtml
import javax.inject.Inject

@AndroidEntryPoint
class TvShowDetailFragment: Fragment(), TvShowDetailContract.View {

    @Inject
    lateinit var presenter: TvShowDetailContract.Presenter

    lateinit var adapter: CastListAdapter
    var castResults: MutableList<Cast> = mutableListOf()

    private var _binding: FragmentTvshowdetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvshowdetailBinding.inflate(inflater, container, false)
        val view = binding.root
        val recyclerView = binding.recyclerview
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(view.getContext()))
        recyclerView.isNestedScrollingEnabled = false
        adapter = CastListAdapter(requireContext(), castResults)
        recyclerView.adapter = adapter
        val tvShowId = arguments?.getInt("tvShowId")
        binding.tvTitle.text = "Got TV Show Id: $tvShowId"
        tvShowId?.let {
            presenter.getDetails(it)
        }
        return view
    }

    override fun onResultsReady(showDetail: ShowDetails, cast: List<Cast>, seasons: List<Season>) {
        val year: String = showDetail.premiered?.year?.toString() ?: "N/A"
        binding.tvTitle.text = "${showDetail.name} ($year)"
        binding.tvGenres.text = if (showDetail.genres.size > 0)
            showDetail.genres.joinToString(", ")
        else
            "N/A"
        binding.tvSummary.text = showDetail.summary?.stripHtml() ?: "N/A"
        castResults.addAll(cast)
        adapter.notifyDataSetChanged()
        Log.d("Tvshowdetail", cast.toString())
        binding.tvSeasons.text = seasons.map { "Season ${it.number}" }.joinToString(",\n")

        val image = showDetail.image
        image?.let {
            val options: RequestOptions = RequestOptions()
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.loading_animation)
            Glide.with(requireContext()).load(it.original).apply(options).into(binding.ivCover)
        }
    }
}