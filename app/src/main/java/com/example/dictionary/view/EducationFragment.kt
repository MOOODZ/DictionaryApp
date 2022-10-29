package com.example.dictionary.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.adapter.TextAdapter
import com.example.dictionary.apiManager.ApiService
import com.example.dictionary.apiManager.getRetrofitInstance
import com.example.dictionary.apiManager.networkModel.Art
import com.example.dictionary.apiManager.networkModel.Words
import com.example.dictionary.databinding.FragmentEducationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EducationFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentEducationBinding
    private lateinit var textList: List<Art>
    private lateinit var adapter: TextAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_education, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentEducationBinding.inflate(layoutInflater)

        getData()


        /*textList = arrayListOf(

            Text(
                "تحصیل در آلمان",
                "وجود دانشگاه\u200Cهای برتر آلمان و امکان تحصیل رایگان در آلمان سبب می\u200Cشود دانشجویان زیادی از سراسر جهان، جذب تحصیل در آلمان \u200Cشوند. تحصیل در اکثر دانشگاه\u200Cهای دولتی آلمان رایگان است و فقط هزینه ترمی حدود ۲۵۰ یورو دریافت می\u200Cشود. اگر می\u200Cخواهید اقامت دائم آلمان را به\u200Cدست آورید، می\u200Cتوانید پس از پایان تحصیل، شغلی پیدا کنید و ویزای تحصیلی خود را به ویزای کار آلمان تبدیل کنید. نرخ بیکاری در آلمان در آوریل ۲۰۲۲ حدود ۳ درصد اعلام شده است و به راحتی می\u200Cتوانید به صورت حضوری یا از طریق سایت\u200Cهای کاریابی آلمان، شغل موردنظر خود را پیدا کنید."
            ),
            Text(
                "بررسی شرایط آلمان",
                "کیفیت بالا در آموزش: دانشگاه\u200Cهای آلمان با ارائه آموزش و تحقیقات عالی، در رتبه\u200Cبندی جهانی در میان بهترین دانشگاه\u200Cهای جهان قرار دارند.\n" +
                        "تنوع در رشته\u200Cهای تحصیلی ارائه\u200Cشده: طیف وسیعی از رشته\u200Cهای ارائه شده در دانشگاه\u200Cهای آلمان، به شما این امکان را می\u200Cدهد که هر رشته\u200Cای را که می\u200Cخواهید، برای تحصیل در آلمان انتخاب کنید. \n" +
                        "آمادگی شروع کار پس از تحصیل: بسیاری از دانشگاه\u200Cهای آلمان با شرکت\u200Cها همکاری می\u200Cکنند و برنامه\u200Cهای مطالعاتی تئوری و عملی را باهم ترکیب می\u200Cکنند. این استراتژی، شروع کار شما را بسیار تسهیل می\u200Cکند.\n" +
                        "استفاده از تمام پتانسیل و استعدادهای خود: در آلمان، می\u200Cتوانید توانایی\u200Cهای فکری و مهارت\u200Cهای شخصی خود را آزادانه توسعه داده و به تمام پتانسیل خود برسید.\n" +
                        "کشور امن: در مقایسه با سایر کشورها، آلمان یک کشور امن است که روز یا شب، می\u200Cتوانید در آنجا آزادانه رفت\u200Cو\u200Cآمد کنید. به\u200Cعلاوه، ثبات اقتصادی و سیاسی آلمان، این کشور را به مکانی ایده\u200Cآل برای تحصیل تبدیل می\u200Cکند.\n"

            ), Text(
                "تحصیل کارشناسی ارشد در آلمان",
                "کارشناسی\u200C ارشد در آلمان به طور معمول ۲ سال طول می\u200Cکشد و هر سال تحصیلی معمولاً از ابتدای ماه اکتبر تا پایان سپتامبر سال بعد طول می\u200Cکشد. تحصیل کارشناسی ارشد در آلمان به زبان\u200Cهای انگلیسی و آلمانی امکان\u200Cپذیر است.\n" +
                        "مدارک مورد نیاز برای تحصیل در مقطع کارشناسی\u200Cارشد در آلمان\n" +
                        "•\tترجمه گواهی پایان دوره دیپلم و ریزنمرات آن\n" +
                        "•\tترجمه گواهی پایان دوره پیش دانشگاهی و ریزنمرات آن\n" +
                        "•\tترجمه مدرک فوق دیپلم و ریزنمرات آن در صورت وجود\n" +
                        "•\tترجمه مدرک لیسانس و ریزنمرات آن\n" +
                        "•\tکپی پاسپورت\n" +
                        "•\tگواهی زبان آلمانی حداقل سطح B2 (در صورت تحصیل به زبان آلمانی)\n" +
                        "•\tمدرک تافل یا آیلتس (در صورت تحصیل به زبان انگلیسی)\n" +
                        "•\tرزومه\u200C تحصیلی\n" +
                        "•\t انگیزه\u200Cنامه \n" +
                        "تحصیل در مقطع تحصیلی کارشناسی ارشد در دانشگاه\u200Cهای آلمان معمولاً رایگان است، اما برای تحصیل در دانشگاه\u200Cهای خصوصی آلمان باید، حداکثر ۳۰،۰۰۰ یورو  هزینه پرداخت کنید.\n"


            )
        )*/


        /* Another method to setup the RecyclerView
        Recy.setHasFixedSize(true)
        myRecy = view.findViewById(R.id.recyclerMain)

        myRecy.layoutManager = LinearLayoutManager(context)
        adapter = TextAdapter(textList)
        myRecy.adapter = adapter*/

        /*recyclerView = view.findViewById(R.id.recyclerMain)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = TextAdapter(textList)
        recyclerView.adapter = adapter*/


    }

    private fun getData() {

        val retrofitData = getRetrofitInstance().create(ApiService::class.java)
            .getWords("1")

        retrofitData.enqueue(object : Callback<List<Words>> {
            override fun onResponse(call: Call<List<Words>>, response: Response<List<Words>>) {
                Log.i("havij",response.body().toString())
                if (response.body()!!.isEmpty()) {

                    recyclerView.visibility = View.GONE
                } else {

                    recyclerView = view!!.findViewById(R.id.recyclerMain)
                    recyclerView.setHasFixedSize(true)
                    recyclerView.layoutManager =
                        LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    adapter = TextAdapter(textList)
                    recyclerView.adapter = adapter

                }
            }

            override fun onFailure(call: Call<List<Words>>, t: Throwable) {
               Log.i("havijFailure",t.message.toString())
            }

        })
    }


}
