package hu.bme.aut.tvshows.ui.createtvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.R
import javax.inject.Inject

@AndroidEntryPoint
class CreateTvShowFragment : Fragment(), CreateTvShowContract.View {

    @Inject
    lateinit var presenter: CreateTvShowContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_createtvshow, container, false)
        val textView: TextView = root.findViewById(R.id.text_createtvshow)
        textView.text = "This is create TV Show Fragment"
        val btnClickMe: Button = root.findViewById(R.id.btnClickMe)
        btnClickMe.setOnClickListener {
            presenter.onCreateTvShow("Halo, Halo")
        }
        return root
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}