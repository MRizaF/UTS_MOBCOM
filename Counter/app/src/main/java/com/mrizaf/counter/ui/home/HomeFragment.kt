package com.mrizaf.counter.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mrizaf.counter.R


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_counter)
        homeViewModel.counter.observe(viewLifecycleOwner, Observer {
            textView.text = it.toString()
        })

        val buttonPlus: Button = root.findViewById(R.id.button_plus)
        buttonPlus.setOnClickListener {
            homeViewModel.counterPlus()
        }

        val buttonMinus: Button = root.findViewById(R.id.button_minus)
        buttonMinus.setOnClickListener {
            homeViewModel.counterMinus()
        }

        setHasOptionsMenu(true)

        return root
    }

    // Showing the Share Menu Item Dynamically
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_restart -> counterReset()
            R.id.action_edit -> counterEdit()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun counterReset() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        builder.setMessage("Do you really want to reset this counter?")

        builder.setPositiveButton("Yes") { _, _ -> homeViewModel.counterReset() }
        builder.setNegativeButton("No", null)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun counterEdit() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        builder.setMessage("Edit this counter")

        val viewInflated: View = LayoutInflater.from(context)
            .inflate(R.layout.dialog_edit, view as ViewGroup?, false)

        val inputTitle: EditText = viewInflated.findViewById(R.id.editTextTitle)
        val inputValue: EditText = viewInflated.findViewById(R.id.editTextValue)

        builder.setView(viewInflated)

        builder.setPositiveButton("Apply") { _, _ -> homeViewModel.counterEdit(inputTitle.text.toString(), inputValue.text.toString()) }
        builder.setNegativeButton("No", null)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}