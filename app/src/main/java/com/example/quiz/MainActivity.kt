package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    lateinit var question : TextView
    lateinit var answerA : TextView
    lateinit var answerB : TextView
    lateinit var answerC : TextView
    lateinit var answerD : TextView

    lateinit var buttonA : Button
    lateinit var buttonB : Button
    lateinit var buttonC : Button
    lateinit var buttonD : Button

    lateinit var quiz : Quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()

        // reading the json from the raw folder

        //step 1: open the raw resource as an InputStream
        // R.raw.questions --> R is the Resources class, raw is folder,
            // question is the file
        val inputStream = resources.openRawResource(R.raw.question)
        //step 2: use a buffered reader on the inputsream ro read the text into a string
        val jsonText = inputStream.bufferedReader().use {
            // the last line of the use function is returned
            it.readText()
        }
        Log.d(TAG, "onCreate: $jsonText")

        // use gson to convert the jsonText into a List<Question>
        // check the section called "Parsing between a Collection, list, or Array
        // log the list of questions and see if your Question objects all appear correct

        val qType = object : TypeToken<List<Question>>() { }.type
        val convertList = Gson().fromJson<List<Question>>(jsonText, qType)
        Log.d(TAG, "onCreate: $convertList")

        quiz = Quiz(convertList)

        // any quiz-related actions -- scorekeeping, checking if answers are right or wrong, keeping track o which question we're on if there are more questions remaining
        // all duties of quiz class

        // main is in charge of the UI and passing information to and from the quiz class
    }

    fun wireWidgets() {
        question = findViewById(R.id.main_question_textView)
        answerA = findViewById(R.id.main_answerA_textView)
        answerB = findViewById(R.id.main_answerB_textView)
        answerC = findViewById(R.id.main_answerC_textView)
        answerD = findViewById(R.id.main_answerD_textView)

        buttonA = findViewById(R.id.main_buttonA_button)
        buttonA.text = "A"
        buttonB = findViewById(R.id.main_buttonB_button)
        buttonB.text = "B"
        buttonC = findViewById(R.id.main_buttonC_button)
        buttonC.text = "C"
        buttonD = findViewById(R.id.main_buttonD_button)
        buttonD.text = "D"

    }
}
