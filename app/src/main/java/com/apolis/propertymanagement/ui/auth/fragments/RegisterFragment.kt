package com.apolis.propertymanagement.ui.auth.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.apolis.propertymanagement.R
import com.apolis.propertymanagement.data.models.User
import com.apolis.propertymanagement.ui.auth.LoginActivity
import com.apolis.propertymanagement.ui.auth.RegisterActivity
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*
import java.util.zip.Inflater

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    var regisListenr:OnFragmentInteraction?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_register, container, false)
        init(view)
        return view
    }
    private fun init(view: View){

        view.user_sign_up_button.setOnClickListener(){
            if(password_edit.text.toString()!=confirm_password_edit.text.toString())
            {
                Toast.makeText(activity,"password is not match",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //TO DO
            var name=user_name_edit.text.toString()
            var password=password_edit.text.toString()
            var email=user_email_edit.text.toString()

            var user= User(type = param1,name=name,email = email,password = password)
            regisListenr?.onRegisButtonClicked(user)



        }
        view.already_register_button.setOnClickListener(){
            activity?.startActivity(Intent(activity,LoginActivity::class.java))
        }
    }
    interface OnFragmentInteraction{
        fun onRegisButtonClicked(user:User)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        regisListenr=context as RegisterActivity
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)

                }
            }
    }
}