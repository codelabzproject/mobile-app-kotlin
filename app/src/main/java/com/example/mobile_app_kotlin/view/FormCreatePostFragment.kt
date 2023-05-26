package com.example.mobile_app_kotlin.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.AdapterView.OnItemSelectedListener

class FormCreatePostFragment : Fragment() {

    private lateinit var spinnerOptions: Spinner

    lateinit var spinner: Spinner
    var selectedTopic:Int = 0

    val topicsMap = mapOf<Int, String>(
        1 to "Flutter",
        2 to "React",
        3 to "Java"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_create_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonCreatePost = view.findViewById<FloatingActionButton>(R.id.button_create_post)

        buttonCreatePost.setOnClickListener {

            // Faça o que desejar ao clicar no botão
            Toast.makeText(context, "Botão clicado", Toast.LENGTH_SHORT).show()
        }

        val linearLayout = view.findViewById<LinearLayout>(R.id.topicsSelection)
        linearLayout.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.topic_item_layout)
            findNavController().navigate(R.id.action_forgetPasswordFragment_to_loginFragment)
            // Configurar a lista de opções clicáveis no modal
            val recyclerView = dialog.findViewById<RecyclerView>(R.id.recycler_all_posts)
//            val adapter = YourAdapter(optionsList) // Substitua YourAdapter e optionsList com os seus valores reais
//            recyclerView.adapter = adapter
//            recyclerView.layoutManager = LinearLayoutManager(context)

            dialog.show()
        }


        spinner = view.findViewById(R.id.spinnerTopics)

        spinner.setOnItemSelectedListener(object : OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?,
                                            view: View, position: Int,
                                            id: Long) {
                    selectedTopic = topicsMap.filterValues { it == topicsMap.values.toList()[position] }.entries.first().key
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        )

        // Create an ArrayAdapter using the string array and a default spinner layout
        val ad: ArrayAdapter<String> = ArrayAdapter<String>(
            view.context,
            android.R.layout.simple_spinner_item,
            topicsMap.values.toTypedArray())

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spinner.adapter = ad

//        ArrayAdapter.createFromResource(
//            this,
//            R.array.planets_array,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // Apply the adapter to the spinner
//            spinner.adapter = adapter
//        }


    }

}