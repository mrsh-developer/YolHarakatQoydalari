package mrsh.com.besh.sakkiz.yolqoydalari

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_qoydalar.*
import kotlinx.android.synthetic.main.fragment_first.view.*
import kotlinx.android.synthetic.main.item_rv.*
import mrsh.com.besh.sakkiz.yolqoydalari.adapter.CustomAdapter
import mrsh.com.besh.sakkiz.yolqoydalari.db.MyDbHelper
import mrsh.com.besh.sakkiz.yolqoydalari.db.Qoyda
import java.io.File
import java.io.FileOutputStream
import java.lang.System.load
import java.security.acl.Group
import java.time.LocalDateTime


class Qoydalar : AppCompatActivity() {
    lateinit var customAdapter: CustomAdapter
    var spiner1: Spinner? = null
    var spineText: String = ""
    lateinit var myDbHelper: MyDbHelper
    var joyi: String? = null
     var a:Int? = null
    var text:String? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qoydalar)




        orqagaBelgiQoshish.setOnClickListener {
            finish()
        }

onResume()
        val arrayListOf4 = arrayListOf("Ogohlantiruvchi", "Imtiyozli", "Taqiqlovchi", "Buyuruvchi")
        spiner1 = spiner
        customAdapter = CustomAdapter(
            this,
            arrayListOf("Ogohlantiruvchi", "Imtiyozli", "Taqiqlovchi", "Buyuruvchi")
        )
        spiner1!!.adapter = customAdapter

        saqlash.setOnClickListener {
            val yolNomi = yolNomi.text.toString()
            val toString = yolToliqMalumot.text.toString()


            val spinnerMentorlar23 = spiner

            spinnerMentorlar23.onItemSelectedListener = object : AdapterView.OnItemClickListener,
                AdapterView.OnItemSelectedListener {
                override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    a = position




                    when (a) {
                        0 ->     text = "Ogohlantiruvchi"
                        1 ->     text = "Imtiyozli"

                        2 ->     text = "Taqiqlovchi"

                        3 ->     text = "Buyuruvchi"


                    }


                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    a = position




                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }



            when (a) {
                0 ->     text = "Ogohlantiruvchi"
                1 ->     text = "Imtiyozli"

                2 ->     text = "Taqiqlovchi"

                3 ->     text = "Buyuruvchi"


            }



            val filesDir = filesDir
            val listFiles1 = filesDir.listFiles()
            if (filesDir.isDirectory) {
                val listFiles = filesDir.listFiles()
                for (listFile1 in listFiles) {

//listFile1.delete()
                    joyi = listFile1.toString()
                }
            }
            myDbHelper = MyDbHelper(this)



            if (yolNomi.isNotEmpty() && toString.isNotEmpty() && text!!.isNotEmpty()
            ) {
                Toast.makeText(this, "MalumotSaqlandi", Toast.LENGTH_SHORT).show()
                myDbHelper.addQoyda(Qoyda(yolNomi, toString,  joyi, text,"0"))



                finish()

            } else
                Toast.makeText(this, "Malumot Toliq Emas Toliq Kiriting", Toast.LENGTH_SHORT).show()


        }



        rasim_qoy.setOnClickListener {
            startActivityForResult(
                Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                    addCategory(Intent.CATEGORY_OPENABLE)
                    type = "image/*"
                }, 1
            )
        }

    }

    override fun onResume() {
        super.onResume()
        val yolNomi = yolNomi.text.toString()
        val toString = yolToliqMalumot.text.toString()


        val spinnerMentorlar23 = spiner

        spinnerMentorlar23.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                a = position



                when (a) {
                    0 ->     text = "Ogohlantiruvchi"
                    1 ->     text = "Imtiyozli"

                    2 ->     text = "Taqiqlovchi"

                    3 ->     text = "Buyuruvchi"


                }

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                a = position




            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }



        when (a) {
            0 ->     text = "Ogohlantiruvchi"
            1 ->     text = "Imtiyozli"

            2 ->     text = "Taqiqlovchi"

            3 ->     text = "Buyuruvchi"


        }



    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val uri = data?.data ?: return
            rasim_qoy.setImageURI(uri)


            val inputStream = contentResolver?.openInputStream(uri)
            val localDateTime = LocalDateTime.now()
            val file = File(filesDir, "$localDateTime images.jpg")
            val outputStream = FileOutputStream(file)
            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream?.close()

        }
    }
}