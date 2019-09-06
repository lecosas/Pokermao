package br.com.fiap.view.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.fiap.R
import kotlinx.android.synthetic.main.include_loading.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListPokemonsActivity : AppCompatActivity() {
    val listaPokemonsViewModel: ListPokemonsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pokemons)
        listaPokemonsViewModel.getPokemons()
        listaPokemonsViewModel.isLoading.observe(this, Observer {
            if(it == true) {
                containerLoading.visibility = View.VISIBLE
            } else {
                containerLoading.visibility = View.GONE
            }
        })
        listaPokemonsViewModel.messageError.observe(this, Observer {
            if(it != "") {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
        listaPokemonsViewModel.pokemons.observe(this, Observer {
            Log.i("Pokemons", it[0].name)
        })
    }
}
