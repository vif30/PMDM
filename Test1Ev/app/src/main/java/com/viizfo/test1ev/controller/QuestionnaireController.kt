package com.viizfo.test1ev.controller

import com.viizfo.test1ev.model.Question
import com.viizfo.test1ev.model.Questionnaire

class QuestionnaireController {
    private lateinit var questionnaire: Questionnaire
    private lateinit var questionnaireBckp: Questionnaire
    private var numQuestions = (3 .. 4).random()
    private var curQuestion = -1

    private val answerList = mutableListOf<Int>()
    fun setQuestionnaire(q:Questionnaire){
        questionnaire = q
        val questions = mutableListOf<Question>()
        questions.addAll(questionnaire.questions)
        questionnaireBckp = Questionnaire(questions)
    }

    fun start(){
        numQuestions = (3 .. 4).random()
        curQuestion = -1

        answerList.clear()
        repeat(numQuestions){
            answerList.add(-1)
        }
        questionnaire.questions.clear()
        questionnaire.questions.addAll(questionnaireBckp.questions)

        while(questionnaire.questions.size > numQuestions){
            questionnaire.questions.removeAt((0 until questionnaire.questions.size).random())
        }
    }

    fun nextQuestion():QuestionnaireFragmentData?{
        curQuestion++
        if(curQuestion == numQuestions) return null
        val question = questionnaire.questions[curQuestion]
        return QuestionnaireFragmentData(
            isFirst = curQuestion == 0,
            isLast = curQuestion +1 == numQuestions,
            question = question.question,
            image = question.image,
            answers = question.answers,
            numQuestions = curQuestion + 1,
            totalQuestions = numQuestions,
            savedAnswer = answerList[curQuestion]
        )
    }
    fun prevQuestion():QuestionnaireFragmentData?{
        curQuestion -= 2
        return nextQuestion()
    }

    fun getResult(): ResultFragmentData {
        var ok = 0
        answerList.forEachIndexed(){index, answer ->
           if(questionnaire.questions[index].correct_answer == answer + 1) ok++
        }
        return ResultFragmentData(
            ok,
            numQuestions
        )
    }
    fun updateAnswer(numAnswer: Int){
        answerList[curQuestion] = numAnswer
    }
}