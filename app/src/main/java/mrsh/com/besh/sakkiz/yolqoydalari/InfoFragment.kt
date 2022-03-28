package mrsh.com.besh.sakkiz.yolqoydalari

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_info.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var root: View

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

        root = inflater.inflate(R.layout.fragment_info, container, false)

        root.telegram.setOnClickListener {

            startUri("https://t.me/MRSHDEVELOPMENT")

        }
        root.instagram.setOnClickListener {

            startUri("https://www.instagram.com/?hl=ru")
        }
        root.facebook.setOnClickListener {

            startUri("https://www.facebook.com/profile.php?id=100047599833006")
        }
        root.twitter.setOnClickListener {

            startUri("https://twitter.com/Muhamma12969721")
        }
        root.github.setOnClickListener {

            startUri("https://github.com/mrsh-developer")
        }
        root.linkedin.setOnClickListener {
            startUri("https://www.linkedin.com/in/%D1%88%D1%83%D1%85%D1%80%D0%B0%D1%82%D0%B4%D0%B6%D0%BE%D0%BD-%D1%85%D1%83%D1%81%D0%B0%D0%B8%D0%BD%D0%BE%D0%B2-7ab4561aa/")
        }

        return root
    }

    fun startUri(uri: String) {
        val uris = Uri.parse(uri)
        startActivity(Intent(Intent.ACTION_VIEW, uris))

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}