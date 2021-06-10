package example.com.beerfinder.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import example.com.beerfinder.R
import example.com.beerfinder.databinding.FragmentListBinding
import example.com.beerfinder.extension.onClick
import example.com.beerfinder.model.MarkerPresentationModel

@AndroidEntryPoint
class MarkerListFragment: Fragment(), MarkerItemClickedlambda {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MarkerListViewModel by viewModels()

    lateinit var adapter: GroupieAdapter
    lateinit var recyclerView: RecyclerView

    private object Flipper {
        const val LOADING = 0
        const val CONTENT = 1
        const val ERROR = 2
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.markerListRecyclerView

        attachUI()

        with(viewModel) {
            viewState.observe(viewLifecycleOwner, ::render)
            loadList()
        }


    }

    private fun attachUI() {
        recyclerView.setHasFixedSize(true)

        adapter = GroupieAdapter()
        recyclerView.adapter = adapter

        adapter.spanCount = 2
        adapter.itemCount

        val gridLayoutManager = GridLayoutManager(context, adapter.spanCount).apply {
            spanSizeLookup = adapter.spanSizeLookup
        }
        recyclerView.layoutManager = gridLayoutManager

    }

    fun render(viewState: MarkerListViewState) {
        when (viewState){
            is MarkerListReady -> {
                binding.loadingProgressBar.visibility = View.GONE
                binding.viewFlipper.displayedChild = Flipper.CONTENT

                adapter.clear()
                var city = ""
                val markersGroupByCity = viewState.markerListings.groupBy {
                        markerPresentationModel: MarkerPresentationModel ->

                    if (city == markerPresentationModel.city) adapter.add(MarkerItem(markerPresentationModel, this)) else {
                        city = markerPresentationModel.city
                        adapter.add(HeaderItem(markerPresentationModel,this))
                        adapter.add(MarkerItem(markerPresentationModel, this))
                    }

                }
                markersGroupByCity

            }
            is Loading -> {
                binding.viewFlipper.displayedChild = Flipper.LOADING

            }

            is Error -> {
                with(binding) {
                    viewFlipper.displayedChild = Flipper.ERROR
                    errorTextView.text = getString(R.string.network_error_title)
                    errorTextView.onClick {
                        with(viewModel){
                            viewModel.loadList()
                        }
                    }

                }

            }
        }
    }

    override fun invoke(marker: MarkerPresentationModel, headerCLicked: Boolean) {
//        navigator?.add(MapFragment.newInstance(marker.id, marker.city, headerCLicked))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}