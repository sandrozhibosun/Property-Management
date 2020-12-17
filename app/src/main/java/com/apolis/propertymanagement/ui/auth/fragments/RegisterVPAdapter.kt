package com.apolis.propertymanagement.ui.auth.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.apolis.propertymanagement.ui.auth.fragments.RegisterFragment
import com.apolis.propertymanagement.ui.auth.fragments.TenantFragment

class RegisterVPAdapter(fm:FragmentManager): FragmentPagerAdapter(fm){



    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{RegisterFragment.newInstance("landlord")
            }
            1->{
                RegisterFragment.newInstance("property manager")

            }
            2->{
                TenantFragment()
            }

            else->{
                RegisterFragment.newInstance("vendor")
            }
        }
    }

    override fun getCount(): Int {
        return  4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->{"landlord"

            }
            1->{"Proper-Man"

            }
            2->{"Tenant"

            }
            else->{"Vendor"

            }
        }
    }

}