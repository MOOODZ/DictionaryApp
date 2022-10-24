package com.example.dictionary.view

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.addTextChangedListener
import com.example.dictionary.R
import com.example.dictionary.adapter.ViewPagerAdapter
import com.example.dictionary.databinding.ActivityMainBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val REC = 102
    var textChanged: ((text: String) -> Unit)? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





        val adapter = ViewPagerAdapter(this)
        binding.viewPagerMain.adapter = adapter
        //binding.vpMain.orientation = ViewPager2.ORIENTATION_VERTICAL
        binding.viewPagerMain.offscreenPageLimit = 1


        initMenu()

        initTabs()

        binding.edTextMain.addTextChangedListener {
            textChanged?.invoke(binding.edTextMain.text.toString())
        }

        binding.btnVoiceSearch.setOnClickListener {
            speechInput()
        }


    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REC && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

            var firstResult: CharSequence
            firstResult = result.toString()
            firstResult = firstResult.replace("[", "")
            firstResult = firstResult.replace("]", "")
            binding.edTextMain.setText(firstResult)
        }


    }

    private fun initMenu(){
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayoutMain,
            binding.toolbarMain,
            R.string.open,
            R.string.close
        )
        binding.drawerLayoutMain.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        binding.navigationViewMain.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    Toast.makeText(this, "You clicked on Home!", Toast.LENGTH_SHORT).show()

                }
                R.id.translate -> {
                    Toast.makeText(this, "You clicked on translate!", Toast.LENGTH_SHORT).show()

                }
                R.id.share -> {
                    Toast.makeText(this, "You clicked on share!", Toast.LENGTH_SHORT).show()

                }

            }
            true
        }

    }

    private fun initTabs(){

        binding.tabLayoutMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.position == 3) {

                    showKeyboard(binding.edTextMain, this@MainActivity)
                    collapseToolbar()

                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        val mediator = TabLayoutMediator(
            binding.tabLayoutMain, binding.viewPagerMain
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "فرهنگ لغت"
                }
                1 -> {
                    tab.text = "تحصیل در آلمان"
                }
                2 -> {
                    tab.text = "مهاجرت به آلمان"
                }
                3 -> {
                    tab.text = "آموزش زبان آلمانی"


                }
            }
        }
        mediator.attach()

    }

    private fun speechInput() {
        if (!SpeechRecognizer.isRecognitionAvailable(this)) {
            Toast.makeText(this, "Speech recognition is not available", Toast.LENGTH_SHORT)
                .show()
        } else {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "de")
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Sagen Sie etwas!")
            startActivityForResult(intent, REC)
        }
    }

    private fun showKeyboard(editText: EditText, context: Context) {
        binding.edTextMain.requestFocus()
        val i : InputMethodManager =
            context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        i.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)


    }

    private fun collapseToolbar() {
        val params = binding.appBarMain.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as AppBarLayout.Behavior?
        if (behavior != null) {
            val valueAnimator = ValueAnimator.ofInt()
            valueAnimator.interpolator = DecelerateInterpolator()
            valueAnimator.addUpdateListener { animation ->
                behavior.topAndBottomOffset = (animation.animatedValue as Int)
                binding.appBarMain.requestLayout()
            }
            valueAnimator.setIntValues(0, -900)
            valueAnimator.duration = 400
            valueAnimator.start()
        }
    }
}
