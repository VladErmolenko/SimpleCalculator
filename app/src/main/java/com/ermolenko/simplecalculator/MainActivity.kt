package com.ermolenko.simplecalculator

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportActionBar?.hide()

        val btnZero: Button = findViewById(R.id.btnZero)
        val btnOne: Button = findViewById(R.id.btnOne)
        val btnTwo: Button = findViewById(R.id.btnTwo)
        val btnThree: Button = findViewById(R.id.btnThree)
        val bntFour: Button = findViewById(R.id.btnFour)
        val btnFive: Button = findViewById(R.id.btnFive)
        val btnSix: Button = findViewById(R.id.btnSix)
        val btnSeven: Button = findViewById(R.id.btnSeven)
        val btnEight: Button = findViewById(R.id.btnEight)
        val btnNine: Button = findViewById(R.id.btnNine)

        val btnDelete: Button = findViewById(R.id.btnDelete)
        val btnBackSpace: ImageButton = findViewById(R.id.btnBackSpace)
        val btnBrackets: Button = findViewById(R.id.btnBrackets)
        val btnPercent: Button = findViewById(R.id.btnPercent)
        val btnDivision: Button = findViewById(R.id.btnDivision)
        val btnMultiply: Button = findViewById(R.id.btnMultiply)
        val btnMinus: Button = findViewById(R.id.btnMinus)
        val btnPlus: Button = findViewById(R.id.btnPlus)
        val btnEquals: Button = findViewById(R.id.btnEquals)
        val btnPlusMinus: Button = findViewById(R.id.btnPlusMinus)
        val btnComma: Button = findViewById(R.id.bntComma)

        val tvInput = findViewById<TextView>(R.id.tvInput)
        val tvOutput = findViewById<TextView>(R.id.tvOutput)

        var bracketsCheck = false
        var text: String
        var expression = ""

        //val myCalculator = Calcutor()
        val myCalculator = RPNRunner()



        btnZero.setOnClickListener {
            text = tvInput.text.toString()
            tvInput.text = text + "0"

        }
        btnOne.setOnClickListener {
            text = tvInput.text.toString()
            tvInput.text = text + "1"

        }
        btnTwo.setOnClickListener {
            text = tvInput.text.toString()
            tvInput.text = text + "2"

        }
        btnThree.setOnClickListener {
            text = tvInput.text.toString()
            tvInput.text = text + "3"

        }
        bntFour.setOnClickListener {
            text = tvInput.text.toString()
            tvInput.text = text + "4"

        }
        btnFive.setOnClickListener {
            text = tvInput.text.toString()
            tvInput.text = text + "5"

        }
        btnSix.setOnClickListener {
            text = tvInput.text.toString()
            tvInput.text = text + "6"

        }
        btnSeven.setOnClickListener {
            text = tvInput.text.toString()
            tvInput.text = text + "7"

        }
        btnEight.setOnClickListener {
            text = tvInput.text.toString()
            tvInput.text = text + "8"

        }
        btnNine.setOnClickListener {
            text = tvInput.text.toString()
            tvInput.text = text + "9"

        }
        btnDelete.setOnClickListener {
            bracketsCheck = false
            text = tvInput.text.toString()
            tvInput.text = ""
            tvOutput.text = ""

        }
        btnBackSpace.setOnClickListener {
            if (tvInput.text.isEmpty()) {
                tvOutput.text=""
            } else {
                text = tvInput.text.toString()
                if (text[text.lastIndex] == '(')
                    bracketsCheck = false
                if (text[text.lastIndex] == ')')
                    bracketsCheck = true
                tvInput.text = text.dropLast(1)

            }
        }
        btnBrackets.setOnClickListener {
            if (bracketsCheck) {
                text = tvInput.text.toString()
                tvInput.text = text + ")"

                bracketsCheck = false
            } else {
                text = tvInput.text.toString()
                tvInput.text = text + "("

                bracketsCheck = true
            }
        }
        btnDivision.setOnClickListener {
            text = tvInput.text.toString()
            tvInput.text = text + "/"
            expression += "/"
        }
        btnMultiply.setOnClickListener {
            text = tvInput.text.toString()
            tvInput.text = text + "*"

        }
        btnMinus.setOnClickListener {
            text = tvInput.text.toString()
            tvInput.text = text + "-"

        }
        btnPlus.setOnClickListener {
            text = tvInput.text.toString()
            tvInput.text = text + "+"

        }
        btnPercent.setOnClickListener {
            text = tvInput.text.toString()
            tvInput.text = text + "%"

        }
        btnPlusMinus.setOnClickListener {
            text = tvInput.text.toString()
            tvInput.text = text + "-"
        }
        btnComma.setOnClickListener {
            text = tvInput.text.toString()
            tvInput.text = text + "."
        }
        btnEquals.setOnClickListener {
            text = tvInput.text.toString().replace("%", "/100")
            //tvOutput.text = text
            tvOutput.text = myCalculator.calculate(text).toString()
        }

    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        val tvInput = findViewById<TextView>(R.id.tvInput)
        val tvOutput = findViewById<TextView>(R.id.tvOutput)
        savedInstanceState.putString("tvInput", tvInput.text.toString())
        savedInstanceState.putString("tvOutput", tvOutput.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val tvInput = findViewById<TextView>(R.id.tvInput)
        val tvOutput = findViewById<TextView>(R.id.tvOutput)
        tvInput.text = savedInstanceState.getString("tvInput")
        tvOutput.text = savedInstanceState.getString("tvOutput")
    }

}