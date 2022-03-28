package mrsh.com.besh.sakkiz.yolqoydalari

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.core.os.bundleOf
import androidx.core.view.size
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TAB_LABEL_VISIBILITY_LABELED
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_test.view.*
import mrsh.com.besh.sakkiz.yolqoydalari.adapter.MyFragmentPagerAdapter
import mrsh.com.besh.sakkiz.yolqoydalari.adapter.User
import mrsh.com.besh.sakkiz.yolqoydalari.databinding.TabItemSelectedBinding
import mrsh.com.besh.sakkiz.yolqoydalari.databinding.TabItemUnselectodBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var root: View
    lateinit var arrayList: ArrayList<User>
    lateinit var myFragmentPageAdapter: MyFragmentPagerAdapter


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
    ): View {
        root = inflater.inflate(R.layout.fragment_home, container, false)

        loadData()

        val size = root.tabLayout2.tabCount

        for (i in 0 until size) {
            if (i == 0) {
                val tabAt = root.tabLayout2.getTabAt(i)
                val inflate = TabItemSelectedBinding.inflate(layoutInflater)
                inflate.tvName.text = tabAt!!.text
                tabAt.view.removeAllViews()
                tabAt.customView = inflate.root

            } else {
                val tabAt = root.tabLayout2.getTabAt(i)
                val inflate = TabItemUnselectodBinding.inflate(layoutInflater)
                inflate.tvName.text = tabAt!!.text
                tabAt.view.removeAllViews()
                tabAt.customView = inflate.root

            }
        }

        root.tabLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val bindingSelected = TabItemSelectedBinding.inflate(layoutInflater)
                bindingSelected.tvName.text = tab!!.text
                tab.view.removeAllViews()
                tab.customView = bindingSelected.root
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val bindingUnselected = TabItemUnselectodBinding.inflate(layoutInflater)
                bindingUnselected.tvName.text = tab!!.text
                tab.view.removeAllViews()
                tab.customView = bindingUnselected.root
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val bindingSelected = TabItemSelectedBinding.inflate(layoutInflater)
                bindingSelected.tvName.text = tab!!.text
                tab.view.removeAllViews()
                tab.customView = bindingSelected.root
            }

        })


        root.addQoyda.setOnClickListener {
            val intent = Intent(requireActivity(), Qoydalar::class.java)
            startActivity(intent)
        }


        return root
    }

    private fun loadData() {
        arrayList = ArrayList()

        arrayList.add(User("Ogohlantiruvchi", "Matn", false))
        arrayList.add(User("Imtiyozli", "Matn", false))
        arrayList.add(User("Taqiqlovchi", "Matn", false))
        arrayList.add(User("Buyuruvchi", "Matn", false))



        myFragmentPageAdapter = MyFragmentPagerAdapter(arrayList, childFragmentManager)
        root.viewPager.adapter = myFragmentPageAdapter


        root.tabLayout2.setupWithViewPager(root.viewPager)


    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}