package noe.basic.mycalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private val btns_Numbers = ArrayList<Button>(10)
    private val btn_operations = ArrayList<Button>(7)
    private lateinit var txtResult :TextView
    private lateinit var currentOp :String
    private var number1ConvertedInt : Int? =null
    private var number2ConvertedInt : Int? =null
    private var number1ConvertedDouble : Double? = null
    private var number2ConvertedDouble : Double? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()
    }
    private fun isLastCharacterAnOperation():Boolean{
        var ableToAdd :Boolean = true
        for (op in btn_operations.subList(0,4)){
            if(txtResult.text.endsWith(op.text)){
                ableToAdd = false
            }
        }
        return ableToAdd
    }
    private fun calculate() : Number?{
        if(number1ConvertedInt !=0 && number2ConvertedInt != 0 && number1ConvertedDouble !=0.0 && number2ConvertedDouble != 0.0){
            when(this.currentOp){
                "+"->{
                    //ADDITION FOR INTEGERS
                    if(number1ConvertedInt != null && number2ConvertedInt != null){
                        return number1ConvertedInt!! + number2ConvertedInt!!

                    }
                    //ADDITION FOR Doubles
                    if(number1ConvertedDouble != null && number2ConvertedDouble != null){
                        return number1ConvertedDouble!! + number2ConvertedDouble!!

                    }

                    //ADDITION FOR num1Doub and num2Int
                    if(number1ConvertedDouble != null && number2ConvertedInt != null){
                        return number1ConvertedDouble!! + number2ConvertedInt!!

                    }
                    //ADDITION FOR num1Int and num2Double
                    if(number1ConvertedInt != null && number2ConvertedDouble != null){
                        return number1ConvertedInt!! + number2ConvertedDouble!!

                    }
                }
                "-" -> {
                    //SUBTRACTION FOR INTEGERS
                    if(number1ConvertedInt != null && number2ConvertedInt != null){
                        return number1ConvertedInt!! - number2ConvertedInt!!

                    }
                    //SUBTRACTION FOR Doubles
                    if(number1ConvertedDouble != null && number2ConvertedDouble != null){
                        return number1ConvertedDouble!! - number2ConvertedDouble!!

                    }

                    //SUBTRACTION FOR num1Doub and num2Int
                    if(number1ConvertedDouble != null && number2ConvertedInt != null){
                        return number1ConvertedDouble!! - number2ConvertedInt!!

                    }
                    //SUBTRACTION FOR num1Int and num2Double
                    if(number1ConvertedInt != null && number2ConvertedDouble != null){
                        return number1ConvertedInt!! - number2ConvertedDouble!!

                    }
                }
                "x"->{
                    //TIMES FOR INTEGERS
                    if(number1ConvertedInt != null && number2ConvertedInt != null){
                        return number1ConvertedInt!! * number2ConvertedInt!!

                    }
                    //TIMES FOR Doubles
                    if(number1ConvertedDouble != null && number2ConvertedDouble != null){
                        return number1ConvertedDouble!! * number2ConvertedDouble!!

                    }

                    //TIMES FOR num1Doub and num2Int
                    if(number1ConvertedDouble != null && number2ConvertedInt != null){
                        return number1ConvertedDouble!! * number2ConvertedInt!!

                    }
                    //TIMES FOR num1Int and num2Double
                    if(number1ConvertedInt != null && number2ConvertedDouble != null){
                        return number1ConvertedInt!! * number2ConvertedDouble!!

                    }
                }
                "/"->{
                    //DIVISION FOR INTEGERS
                    if(number1ConvertedInt != null && number2ConvertedInt != null){
                        return if (number1ConvertedInt!! / number2ConvertedInt!! == 0){
                            number1ConvertedInt!!.toDouble() / number2ConvertedInt!!.toDouble()
                        }else{
                            number1ConvertedInt!! / number2ConvertedInt!!

                        }
                    }

                    //DIVISION FOR Doubles
                    if(number1ConvertedDouble != null && number2ConvertedDouble != null){
                        return number1ConvertedDouble!! / number2ConvertedDouble!!
                    }

                    //DIVISION FOR num1Doub and num2Int
                    if(number1ConvertedDouble != null && number2ConvertedInt != null){
                        return number1ConvertedDouble!! / number2ConvertedInt!!

                    }
                    //DIVISION FOR num1Int and num2Double
                    if(number1ConvertedInt != null && number2ConvertedDouble != null) {
                        return number1ConvertedInt!! / number2ConvertedDouble!!
                    }
                }

            }
        }else{
            return 0
        }
        return null
    }
    private fun calculateResult(forcedResult:Boolean=false):String{
        val numbersString :Any
        lateinit var number1 :String
        lateinit var number2 :String

        when(this.currentOp){
            "+" ->{
                numbersString = Pattern.compile("\\+").split(txtResult.text)
                number1 = numbersString[0]
                number2 = numbersString[1]
                findRightDataType(number1,number2)
                txtResult.text=calculate()?.toString()?:"ERROR"
            }
            "-" ->{
                numbersString = Pattern.compile("-").split(txtResult.text)
                number1 = numbersString[0]
                number2 = numbersString[1]
                findRightDataType(number1,number2)
                txtResult.text=calculate()?.toString()?:"ERROR"
            }
            "x" -> {
                numbersString = Pattern.compile("x").split(txtResult.text)
                number1 = numbersString[0]
                number2 = numbersString[1]
                findRightDataType(number1,number2)
                txtResult.text=calculate()?.toString()?:"ERROR"
            }
            "/" -> {
                numbersString = Pattern.compile("/").split(txtResult.text)
                number1 = numbersString[0]
                number2 = numbersString[1]
                findRightDataType(number1,number2)
                txtResult.text=calculate()?.toString()?:"ERROR"

            }
        }
        if(forcedResult){
            txtResult.text="${txtResult.text}$currentOp"
        }
        return ""
    }
    private fun findRightDataType(num1:String,num2:String){

        if(num1.contains(".")){
            number1ConvertedDouble = num1.toDoubleOrNull()

        }else{
            number1ConvertedInt = num1.toIntOrNull()

        }
        if(num2.contains(".")){
            number2ConvertedDouble = num2.toDoubleOrNull()

        }else{
            number2ConvertedInt = num2.toIntOrNull()
        }


    }
    private fun initializeView(){

        btns_Numbers.add(findViewById(R.id.btnV_0))
        btns_Numbers.add(findViewById((R.id.btnV_1)))
        btns_Numbers.add(findViewById((R.id.btnV_2)))
        btns_Numbers.add(findViewById((R.id.btnV_3)))
        btns_Numbers.add(findViewById((R.id.btnV_4)))
        btns_Numbers.add(findViewById((R.id.btnV_5)))
        btns_Numbers.add(findViewById((R.id.btnV_6)))
        btns_Numbers.add(findViewById((R.id.btnV_7)))
        btns_Numbers.add(findViewById((R.id.btnV_8)))
        btns_Numbers.add(findViewById((R.id.btnV_9)))

        btn_operations.add(findViewById((R.id.btnV_opADD)))
        btn_operations.add(findViewById((R.id.btnV_opMinus)))
        btn_operations.add(findViewById((R.id.btnV_opTimes)))
        btn_operations.add(findViewById((R.id.btnV_opDivide)))
        btn_operations.add(findViewById(R.id.btnV_dot))
        btn_operations.add(findViewById(R.id.btnV_opDelete))
        btn_operations.add(findViewById((R.id.btnV_clear)))
        btn_operations.add(findViewById((R.id.btnV_opEquals)))

        txtResult = findViewById(R.id.txtV_OPERATIONS)
        txtResult.text ="0"

        for (i in 0 until btns_Numbers.size){
            //TODO u can actually pass values to a string resource instead of creating multiple string resources
            val numberPressed =  getString(R.string.pressed_number, i)
            btns_Numbers[i].text=numberPressed
            btns_Numbers[i].setOnClickListener(){
                if(txtResult.text.equals("0")){
                    txtResult.text =numberPressed
                }else{
                    if(txtResult.text.substring(0,txtResult.text.length) == "0"){
                        txtResult.text=txtResult.text.substring(0,txtResult.text.lastIndex)+numberPressed
                    }else{
                        txtResult.text="${txtResult.text}$numberPressed"
                    }

                }
            }
        }
        //CLEAR
        btn_operations[btn_operations.size-2].setOnClickListener{
            this.txtResult.text="0"
        }
        //EQUALS
        btn_operations[btn_operations.size-1].setOnClickListener {
            calculateResult()
        }
        //the rest of OPs
        for (i in 0 until btn_operations.size-2){
            btn_operations[i].setOnClickListener {
                //DOT
                if(btn_operations[i].text.equals(".")){
                    if(isLastCharacterAnOperation()){
                        txtResult.text="${txtResult.text}."
                    }
                }
                //DELETE
                if(btn_operations[i].text.equals("<-")){
                    if(isLastCharacterAnOperation() && !txtResult.text.equals("0") && !txtResult.text.equals("") ){
                        if(txtResult.text.substring(0,txtResult.text.lastIndex).equals("")){
                            txtResult.text ="0"
                        }else{
                            txtResult.text=txtResult.text.substring(0,txtResult.text.lastIndex)
                        }
                    }
                }else{
                    //ARITHMETIC OPs
                    if(isLastCharacterAnOperation()){
                        //TODO aqui se pudo haber utilizado string array
                        when(btn_operations[i].text){
                            "+" ->{
                                this.currentOp="+"
                                if(txtResult.text.contains("+") || txtResult.text.contains("-") ||txtResult.text.contains("x") || txtResult.text.contains("/") ){
                                    calculateResult(true)
                                }else{txtResult.text="${txtResult.text}${btn_operations[i].text}"}
                            }
                            "-" ->{
                                this.currentOp="-"
                                if(txtResult.text.contains("+") || txtResult.text.contains("-") ||txtResult.text.contains("x") || txtResult.text.contains("/") ){
                                    calculateResult(true)
                                }else{txtResult.text="${txtResult.text}${btn_operations[i].text}"}
                            }
                            "x" ->{
                                this.currentOp="x"
                                if(txtResult.text.contains("+") || txtResult.text.contains("-") ||txtResult.text.contains("x") || txtResult.text.contains("/") ){
                                    calculateResult(true)
                                }else{txtResult.text="${txtResult.text}${btn_operations[i].text}"}
                            }
                            "/" ->{
                                this.currentOp="/"
                                if(txtResult.text.contains("+") || txtResult.text.contains("-") ||txtResult.text.contains("x") || txtResult.text.contains("/") ){
                                    calculateResult(true)

                                }else{txtResult.text="${txtResult.text}${btn_operations[i].text}"}

                            }
                        }

                    }
                }

            }
        }
    }
}