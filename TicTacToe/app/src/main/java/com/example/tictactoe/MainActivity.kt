package com.example.tictactoe

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var activePlayer1 = true
    var buttons = ArrayList<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getButtons()
        newGame.isEnabled = false
    }

    fun getButtons(){
        buttons.add(button1)
        buttons.add(button2)
        buttons.add(button3)
        buttons.add(button4)
        buttons.add(button5)
        buttons.add(button6)
        buttons.add(button7)
        buttons.add(button8)
        buttons.add(button9)
    }

    fun turn (currentButton: Button){
        if(activePlayer1){
            currentButton.text = "X"
            currentButton.isClickable = false
            currentButton.setBackgroundColor(Color.parseColor("#e22431"))
            if(checkWinner()){
                Toast.makeText(this," Player 1  win the game", Toast.LENGTH_LONG).show()
                newGame.isEnabled = true
                for (i in buttons){
                    i.isClickable = false
                }
            }
            else{
                if(checkDraw()){
                    Toast.makeText(this,"Played a draw", Toast.LENGTH_LONG).show()
                    newGame.isEnabled = true
                }
            }
            activePlayer1 = false
        }
        else{
            currentButton.text = "0"
            currentButton.isClickable = false
            currentButton.setBackgroundColor(Color.parseColor("#e2cf24"))
            if(checkWinner()){
                Toast.makeText(this," Player 2  win the game", Toast.LENGTH_LONG).show()
                newGame.isEnabled = true
                for (i in buttons){
                    i.isClickable = false
                }
            }
            activePlayer1 = true
        }
    }

    fun buttonClick (view: View){
        val currentButton = view as Button
        turn(currentButton)
        if(activePlayer1){
            textView.text = "Turn of player 1"
        }
        else{
            textView.text = "Turn of player 2"
        }
    }

    fun checkWinner(): Boolean {

        val text1 = button1.text
        val text2 = button2.text
        val text3 = button3.text
        val text4 = button4.text
        val text5 = button5.text
        val text6 = button6.text
        val text7 = button7.text
        val text8 = button8.text
        val text9 = button9.text


        //rows
        if (text1.equals(text2) && text1.equals(text3) && !text2.isEmpty()){
            for (i in buttons){
                i.setBackgroundColor(Color.parseColor("#d7d7d7"))
            }
            button1.setBackgroundColor(Color.parseColor("#74a0e8"))
            button2.setBackgroundColor(Color.parseColor("#74a0e8"))
            button3.setBackgroundColor(Color.parseColor("#74a0e8"))
            return true
        }else if (text4.equals(text5) && text4.equals(text6) && !text5.isEmpty()){
            for (i in buttons){
                i.setBackgroundColor(Color.parseColor("#d7d7d7"))
            }
            button4.setBackgroundColor(Color.parseColor("#74a0e8"))
            button5.setBackgroundColor(Color.parseColor("#74a0e8"))
            button6.setBackgroundColor(Color.parseColor("#74a0e8"))
            return true
        }else if (text7.equals(text8) && text7.equals(text9) && !text8.isEmpty()){
            for (i in buttons){
                i.setBackgroundColor(Color.parseColor("#d7d7d7"))
            }
            button7.setBackgroundColor(Color.parseColor("#74a0e8"))
            button8.setBackgroundColor(Color.parseColor("#74a0e8"))
            button9.setBackgroundColor(Color.parseColor("#74a0e8"))
            return true
        }


        //columns
       else if (text1.equals(text4) && text1.equals(text7) && !text4.isEmpty()){
            for (i in buttons){
                i.setBackgroundColor(Color.parseColor("#d7d7d7"))
            }
            button1.setBackgroundColor(Color.parseColor("#74a0e8"))
            button4.setBackgroundColor(Color.parseColor("#74a0e8"))
            button7.setBackgroundColor(Color.parseColor("#74a0e8"))
            return true
        }else if (text2.equals(text5) && text2.equals(text8) && !text5.isEmpty()){
            for (i in buttons){
                i.setBackgroundColor(Color.parseColor("#d7d7d7"))
            }
            button2.setBackgroundColor(Color.parseColor("#74a0e8"))
            button5.setBackgroundColor(Color.parseColor("#74a0e8"))
            button8.setBackgroundColor(Color.parseColor("#74a0e8"))
            return true
        }else if (text3.equals(text6) && text3.equals(text9) && !text6.isEmpty()){
            for (i in buttons){
                i.setBackgroundColor(Color.parseColor("#d7d7d7"))
            }
            button3.setBackgroundColor(Color.parseColor("#74a0e8"))
            button6.setBackgroundColor(Color.parseColor("#74a0e8"))
            button9.setBackgroundColor(Color.parseColor("#74a0e8"))
            return true
        }

        //diagonal
        else if(text1.equals(text5) && text1.equals(text9) && !text5.isEmpty()){
            for (i in buttons){
                i.setBackgroundColor(Color.parseColor("#d7d7d7"))
            }
            button1.setBackgroundColor(Color.parseColor("#74a0e8"))
            button5.setBackgroundColor(Color.parseColor("#74a0e8"))
            button9.setBackgroundColor(Color.parseColor("#74a0e8"))
            return true
        }else if(text3.equals(text5) && text3.equals(text7) && !text5.isEmpty()){
            for (i in buttons){
                i.setBackgroundColor(Color.parseColor("#d7d7d7"))
            }
            button3.setBackgroundColor(Color.parseColor("#74a0e8"))
            button5.setBackgroundColor(Color.parseColor("#74a0e8"))
            button7.setBackgroundColor(Color.parseColor("#74a0e8"))
            return true
        }
        return false
    }

    fun NewGame(view: View) {
        for (i in buttons){
            i.text = ""
            i.isClickable = true
            activePlayer1 = true
            i.setBackgroundColor(Color.parseColor("#d7d7d7"))
            textView.text = "Turn of player 1"
        }
        view.isEnabled = false
    }

    fun checkDraw(): Boolean {
        val text1 = button1.text
        val text2 = button2.text
        val text3 = button3.text
        val text4 = button4.text
        val text5 = button5.text
        val text6 = button6.text
        val text7 = button7.text
        val text8 = button8.text
        val text9 = button9.text


        if (!text1.isEmpty() && !text2.isEmpty() && !text3.isEmpty() && !text4.isEmpty() && !text5.isEmpty()
            && !text6.isEmpty() && !text7.isEmpty() && !text8.isEmpty() && !text9.isEmpty()){
            return true
        }
        return false
    }
}