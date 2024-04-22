package com.juanchavez.myapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.juanchavez.myapplication.R
import com.juanchavez.myapplication.application.PerrosRFApp
import com.juanchavez.myapplication.data.PerroRepository
import com.juanchavez.myapplication.data.remote.model.PerroDetailDto
import com.juanchavez.myapplication.databinding.FragmentPerroDetailBinding
import com.juanchavez.myapplication.utils.Constants
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val PERRO_ID = "param1"

class PerroDetailFragment : Fragment() {

    private var _binding: FragmentPerroDetailBinding? = null

    private val binding get() = _binding!!

    private var perro_id: String? = null

    private lateinit var repository: PerroRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            perro_id = it.getString(PERRO_ID)

            Log.d(Constants.LOGTAG, getString(R.string.id_recibido_string, perro_id))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPerroDetailBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Programar la conexión
        repository = (requireActivity().application as PerrosRFApp).repository

        lifecycleScope.launch {
            perro_id?.let { id ->

                //val call: Call<GameDetailDto> = repository.getGameDetail(id)

                //Con Apiary:
                val call: Call<PerroDetailDto> = repository.getPerroDetail(id)

                call.enqueue(object: Callback<PerroDetailDto> {
                    override fun onResponse(p0: Call<PerroDetailDto>, response: Response<PerroDetailDto>) {

                        val years = response.body()?.years.toString()

                        val height = response.body()?.height.toString()

                        val size = response.body()?.size.toString()

                        binding.apply {
                            pbLoading.visibility = View.INVISIBLE

                            tvNombrePerro.text = response.body()?.name

                            tvDesc.text = response.body()?.description

                            tvYears.text = getString(R.string.years_binding_string, years)

                            tvHeight.text = getString(R.string.height_int_string, height)

                            tvSize.text = getString(R.string.size_dog_string, size)

                            Glide.with(requireActivity())
                                .load(response.body()?.image)
                                .into(ivImage)

                        }

                    }

                    override fun onFailure(p0: Call<PerroDetailDto>, p1: Throwable) {
                        //Manejar el error sin conexión
                        binding.pbLoading.visibility = View.INVISIBLE
                    }

                })
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(perroId: String) =
            PerroDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(PERRO_ID, perroId)
                }
            }


    }
}
