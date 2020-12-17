package com.apolis.propertymanagement.ui.auth.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.apolis.propertymanagement.R
import com.apolis.propertymanagement.ui.auth.LoginActivity
import kotlinx.android.synthetic.main.fragment_tenant.*

import kotlinx.android.synthetic.main.fragment_tenant.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TenantFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TenantFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_tenant, container, false)
        init(view)
        return view
    }

    private fun init(view: View){
        view.user_email_edit.setOnClickListener(){
            if(password_edit.text!=confirm_password_edit.text)
            {
                Toast.makeText(activity,"password is not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //TO DO


            activity?.startActivity(Intent(activity, LoginActivity::class.java))
        }
        view.already_register_button.setOnClickListener(){
            activity?.startActivity(Intent(activity, LoginActivity::class.java))
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TenantFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TenantFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}