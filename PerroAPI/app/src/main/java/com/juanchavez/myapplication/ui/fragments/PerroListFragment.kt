package com.juanchavez.myapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanchavez.myapplication.R
import com.juanchavez.myapplication.application.PerrosRFApp
import com.juanchavez.myapplication.data.PerroRepository
import com.juanchavez.myapplication.data.remote.model.PerroDto
import com.juanchavez.myapplication.databinding.FragmentPerroListBinding
import com.juanchavez.myapplication.ui.adapter.PerroAdapter
import com.juanchavez.myapplication.utils.Constants
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PerroListFragment : Fragment() {

    private var _binding: FragmentPerroListBinding? = null

    private val binding get() = _binding!!

    private lateinit var repository: PerroRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPerroListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = (requireActivity().application as PerrosRFApp).repository

        lifecycleScope.launch {
            val call: Call<List<PerroDto>> = repository.getPerros("razas")

            call.enqueue(object : Callback<List<PerroDto>> {
                override fun onResponse(
                    p0: Call<List<PerroDto>>,
                    response: Response<List<PerroDto>>
                ) {

                    binding.pbLoading.visibility = View.GONE

                    Log.d(Constants.LOGTAG, getString(R.string.respuesta_recibida, response.body()))

                    response.body()?.let { helados ->
                        binding.rvPerros.apply {
                            layoutManager = LinearLayoutManager(requireContext())
                            adapter = PerroAdapter(helados) { helado ->
                                requireActivity().supportFragmentManager.beginTransaction()
                                    .replace(
                                        R.id.fragment_container,
                                        PerroDetailFragment.newInstance(helado.idPerro.toString())
                                    )
                                    .addToBackStack(null)
                                    .commit()
                            }
                        }

                    }

                }

                override fun onFailure(p0: Call<List<PerroDto>>, error: Throwable) {
                    //Manejo del error
                    binding.pbLoading.visibility = View.GONE

                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error_conexion_string, error.message),
                        Toast.LENGTH_SHORT
                    ).show()
                }


            })
        }
    }
}
