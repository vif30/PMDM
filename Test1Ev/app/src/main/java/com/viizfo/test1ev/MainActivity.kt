package com.viizfo.test1ev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.viizfo.test1ev.controller.QuestionnaireController
import com.viizfo.test1ev.databinding.ActivityMainBinding
import com.viizfo.test1ev.model.Questionnaire
import com.viizfo.test1ev.utils.OnBtnQuestionnaireClick
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity(), OnBtnQuestionnaireClick {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var questionnaireController: QuestionnaireController

    override fun onCreate(savedInstanceState: Bundle?) {

        runBlocking {
            questionnaireController = QuestionnaireController()
            questionnaireController.setQuestionnaire(Questionnaire.loadQuestionnaire(this@MainActivity))
            delay(1000)
            setTheme(R.style.Theme_Test1Ev)
        }

        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also {binding = it}.root)
        setSupportActionBar(binding.toolbar)

        val navHosFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHosFragment.navController

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment,
            R.id.questionnaireFragment,
            R.id.resultFragment
        )
        )

        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.questionnaire_menu, menu);
        return true;
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.exitOption -> {
                finishAffinity()
                super.onOptionsItemSelected(item)
            }
            R.id.questionnaireFragment -> {
                questionnaireController.start()
                val question = questionnaireController.nextQuestion()
                question?.let {
                    val action = HomeFragmentDirections.actionHomeFragmentToQuestionnaireFragment(it)
                    navController.navigate(action)
                }?: run{

                }
                super.onOptionsItemSelected(item)
            }
            else -> {
                NavigationUI.onNavDestinationSelected(item, navController)
                        || super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onNextClick(numAnswer: Int) {
        questionnaireController.updateAnswer(numAnswer)
        val question = questionnaireController.nextQuestion()
        question?.let {
            val action = QuestionnaireFragmentDirections.actionQuestionnaireFragmentSelf(it)
            navController.navigate(action)
        }?: run{
            val result = questionnaireController.getResult()
            val action = QuestionnaireFragmentDirections.actionQuestionnaireFragmentToResultFragment(result)
            navController.navigate(action)
        }
    }
    override fun onPrevClick(numAnswer:Int) {
        questionnaireController.updateAnswer(numAnswer)
        val question = questionnaireController.prevQuestion()
        question?.let {
            val action = QuestionnaireFragmentDirections.actionQuestionnaireFragmentSelf(it)
            navController.navigate(action)
        }?: run{

        }
    }
}