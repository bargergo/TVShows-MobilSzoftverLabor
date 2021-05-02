package hu.bme.aut.tvshows.ui.tvshowdetail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.R
import hu.bme.aut.tvshows.databinding.FragmentTvshowdetailBinding
import hu.bme.aut.tvshows.ui.model.Cast
import hu.bme.aut.tvshows.ui.model.Season
import hu.bme.aut.tvshows.ui.model.ShowDetail
import javax.inject.Inject

@AndroidEntryPoint
class TvShowDetailFragment: Fragment(), TvShowDetailContract.View {

    @Inject
    lateinit var presenter: TvShowDetailContract.Presenter

    lateinit var castListAdapter: CastListAdapter
    var castResults: MutableList<Cast> = mutableListOf()

    lateinit var seasonListAdapter: SeasonListAdapter
    var seasonResults: MutableList<Season> = mutableListOf()

    private var _binding: FragmentTvshowdetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var model: ShowDetail
    lateinit var addOrRemoveFavourites: MenuItem

    init {
        setHasOptionsMenu(true);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvshowdetailBinding.inflate(inflater, container, false)
        val view = binding.root

        val tvShowId = arguments?.getLong("tvShowId")
        val useDbOnly = arguments?.getBoolean("useDbOnly", false) ?: false

        val rvCastList = binding.rvCastList
        rvCastList.setHasFixedSize(true)
        rvCastList.setLayoutManager(LinearLayoutManager(view.getContext()))
        rvCastList.isNestedScrollingEnabled = false
        castListAdapter = CastListAdapter(requireContext(), castResults)
        rvCastList.adapter = castListAdapter

        val rvSeasonList = binding.rvSeasonList
        rvSeasonList.setHasFixedSize(true)
        rvSeasonList.setLayoutManager(LinearLayoutManager(view.getContext()))
        rvSeasonList.isNestedScrollingEnabled = false
        seasonListAdapter = SeasonListAdapter(this, seasonResults, useDbOnly)
        rvSeasonList.adapter = seasonListAdapter

        binding.tvTitle.text = "Got TV Show Id: $tvShowId"
        tvShowId?.let {
            if (useDbOnly) {
                presenter.getDetailsFromDb(it)
            } else {
                presenter.getDetails(it)
            }
        }
        return view
    }

    override fun onResultsReady(showDetail: ShowDetail) {
        model = showDetail
        val year: String = showDetail.premier?.year?.toString() ?: "N/A"
        binding.tvTitle.text = "${showDetail.name} ($year)"
        binding.tvGenres.text = showDetail.genres
        binding.tvSummary.text = showDetail.summary
        if (castResults.isEmpty()) {
            castResults.addAll(showDetail.cast)
            castListAdapter.notifyDataSetChanged()
        }

        if (seasonResults.isEmpty()) {
            seasonResults.addAll(showDetail.seasons)
            seasonListAdapter.notifyDataSetChanged()
        }

        val image = showDetail.imageUrl
        image?.let {
            val options: RequestOptions = RequestOptions()
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.loading_animation)
            Glide.with(requireContext()).load(it).apply(options).into(binding.ivCover)
        }

        if (model.isFavourite) {
            addOrRemoveFavourites.title = getString(R.string.title_remove_from_favourites)
        } else {
            addOrRemoveFavourites.title = getString(R.string.title_add_to_favourites)
        }
    }

    override fun onShowAddedToFavourites() {
        model.isFavourite = true
        Toast.makeText(requireContext(), "Show saved", Toast.LENGTH_SHORT).show()
        addOrRemoveFavourites.title = getString(R.string.title_delete_show)
    }

    override fun onShowRemovedFromFavourites() {
        model.isFavourite = false
        Toast.makeText(requireContext(), "Show removed", Toast.LENGTH_SHORT).show()
        addOrRemoveFavourites.title = getString(R.string.title_add_to_favourites)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.showdetail_menu, menu);

        addOrRemoveFavourites = menu.findItem(R.id.add_to_favourites)
        val edit = menu.findItem(R.id.edit_show)
        val delete = menu.findItem(R.id.delete)

        addOrRemoveFavourites.setOnMenuItemClickListener {
            if (model.isFavourite) {
                presenter.removeShow(model)
            } else {
                presenter.saveShow(model)
            }
            true
        }

        edit.setOnMenuItemClickListener {
            true
        }

        delete.setOnMenuItemClickListener {
            true
        }

        super.onCreateOptionsMenu(menu, inflater)
    }
}