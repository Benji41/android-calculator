package noe.basic.mycalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private val btnNumbers = ArrayList<Button>(10)
    private val btnOps = ArrayList<Button>(7)
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
    private fun initializeView(){

        btnNumbers.add(findViewById(R.id.btnV_0))
        btnNumbers.add(findViewById((R.id.btnV_1)))
        btnNumbers.add(findViewById((R.id.btnV_2)))
        btnNumbers.add(findViewById((R.id.btnV_3)))
        btnNumbers.add(findViewById((R.id.btnV_4)))
        btnNumbers.add(findViewById((R.id.btnV_5)))
        btnNumbers.add(findViewById((R.id.btnV_6)))
        btnNumbers.add(findViewById((R.id.btnV_7)))
        btnNumbers.add(findViewById((R.id.btnV_8)))
        btnNumbers.add(findViewById((R.id.btnV_9)))

        btnOps.add(findViewById((R.id.btnV_opADD)))
        btnOps.add(findViewById((R.id.btnV_opMinus)))
        btnOps.add(findViewById((R.id.btnV_opTimes)))
        btnOps.add(findViewById((R.id.btnV_opDivide)))
        btnOps.add(findViewById(R.id.btnV_dot))
        btnOps.add(findViewById(R.id.btnV_opDelete))
        btnOps.add(findViewById((R.id.btnV_clear)))
        btnOps.add(findViewById((R.id.btnV_opEquals)))

        txtResult = findViewById(R.id.txtV_OPERATIONS)
        txtResult.text ="0"

        for (i in 0 until btnNumbers.size){
            //TODO u can actually pass values to a string resource instead of creating multiple string resources
            val numberPressed =  getString(R.string.pressed_number, i)
            btnNumbers[i].text=numberPressed
            btnNumbers[i].setOnClickListener(){
                if(txtResult.text.equals("0")){
                    txtResult.text =numberPressed
                }else{
                    txtResult.text="${txtResult.text}$numberPressed"
                }
            }
        }
        //CLEAR
        btnOps[btnOps.size-2].setOnClickListener{
            this.txtResult.text="0"
        }
        //EQUALS
        btnOps[btnOps.size-1].setOnClickListener {
            if(txtResult.text.contains("+") || txtResult.text.contains("-") || txtResult.text.contains("x") || txtResult.text.contains("/") ){
                calculateResult()
            }
        }
        //the rest of OPs
        for (i in 0 until btnOps.size-2){
            btnOps[i].setOnClickListener {
                //DOT
                if(btnOps[i].text.equals(".")){
                    if(isLastCharacterAnOperation() && !txtResult.text.endsWith(".") ){
                        txtResult.text="${txtResult.text}."
                    }
                }
                //DELETE
                if(btnOps[i].text.equals("<-")){
                    if(isLastCharacterAnOperation() && !txtResult.text.equals("0") && !txtResult.text.equals("") ){
                        if(txtResult.text.substring(0,txtResult.text.lastIndex) == ""){
                            txtResult.text ="0"
                        }else{
                            txtResult.text=txtResult.text.substring(0,txtResult.text.lastIndex)
                        }
                    }
                }else{
                    //ARITHMETIC OPs
                    if(isLastCharacterAnOperation()){
                        //TODO aqui se pudo haber utilizado string array
                        when(btnOps[i].text){
                            "+" ->{
                                this.currentOp="+"
                                isForcingOperation(i)
                            }
                            "-" ->{
                                this.currentOp="-"
                                isForcingOperation(i)
                            }
                            "x" ->{
                                this.currentOp="x"
                                isForcingOperation(i)
                            }
                            "/" ->{
                                this.currentOp="/"
                                isForcingOperation(i)
                            }
                        }

                    }
                }

            }
        }
    }
    private fun isForcingOperation(i:Int){
        if(txtResult.text.contains("+") || txtResult.text.contains("-") ||txtResult.text.contains("x") || txtResult.text.contains("/") ){
            calculateResult(true)
        }else{if(!txtResult.text.endsWith("."))txtResult.text="${txtResult.text}${btnOps[i].text}"}
    }
    private fun isLastCharacterAnOperation():Boolean{
        var ableToAdd = true
        for (op in btnOps.subList(0,4)){
            if(txtResult.text.endsWith(op.text)){
                ableToAdd = false
            }
        }
        return ableToAdd
    }
    private fun settingStringNumbers(arr:Array<String>){
        val number1 :String = arr[0]
        val number2 :String = arr[1]
        findRightDataType(number1,number2)
        treatResult()
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
    private fun calculate() : Any?{
            when(this.currentOp){
                "+"->{
                    //ADDITION FOR INTEGERS
                    if(number1ConvertedInt != null && number2ConvertedInt != null){
                       val result :Int=  number1ConvertedInt!! + number2ConvertedInt!!
                        return result
                    }
                    //ADDITION FOR Doubles
                    if(number1ConvertedDouble != null && number2ConvertedDouble != null){
                       val result :Double =  number1ConvertedDouble!! + number2ConvertedDouble!!
                        return result
                    }

                    //ADDITION FOR num1Doub and num2Int
                    if(number1ConvertedDouble != null && number2ConvertedInt != null){
                       val result :Double =  number1ConvertedDouble!! + number2ConvertedInt!!
                        return result
                    }
                    //ADDITION FOR num1Int and num2Double
                    if(number1ConvertedInt != null && number2ConvertedDouble != null){
                       val result :Double =  number1ConvertedInt!! + number2ConvertedDouble!!
                        return result
                    }
                }
                "-" -> {
                    //SUBTRACTION FOR INTEGERS
                    if(number1ConvertedInt != null && number2ConvertedInt != null){
                       val result :Int =  number1ConvertedInt!! - number2ConvertedInt!!
                        return result
                    }
                    //SUBTRACTION FOR Doubles
                    if(number1ConvertedDouble != null && number2ConvertedDouble != null){
                       val result :Double =  number1ConvertedDouble!! - number2ConvertedDouble!!
                        return result
                    }

                    //SUBTRACTION FOR num1Doub and num2Int
                    if(number1ConvertedDouble != null && number2ConvertedInt != null){
                      val result :Double =  number1ConvertedDouble!! - number2ConvertedInt!!
                        return result
                    }
                    //SUBTRACTION FOR num1Int and num2Double
                    if(number1ConvertedInt != null && number2ConvertedDouble != null){
                       val result :Double =  number1ConvertedInt!!.toDouble() - number2ConvertedDouble!!
                        return result
                    }
                }
                "x"->{
                    //TIMES FOR INTEGERS
                    if(number1ConvertedInt != null && number2ConvertedInt != null){
                       val result :Int =  number1ConvertedInt!! * number2ConvertedInt!!
                        return result
                    }
                    //TIMES FOR Doubles
                    if(number1ConvertedDouble != null && number2ConvertedDouble != null){
                       val result :Double =  number1ConvertedDouble!! * number2ConvertedDouble!!
                        return result
                    }

                    //TIMES FOR num1Doub and num2Int
                    if(number1ConvertedDouble != null && number2ConvertedInt != null){
                       val result :Double =  number1ConvertedDouble!! * number2ConvertedInt!!
                        return result
                    }
                    //TIMES FOR num1Int and num2Double
                    if(number1ConvertedInt != null && number2ConvertedDouble != null){
                       val result :Double =  number1ConvertedInt!!.toDouble() * number2ConvertedDouble!!
                        return result
                    }
                }
                "/"->{
                    //DIVISION FOR INTEGERS
                    if(number1ConvertedInt != null && number2ConvertedInt != null){
                        var result :Number
                        if (number1ConvertedInt!! / number2ConvertedInt!! == 0){
                            result = number1ConvertedInt!!.toDouble() / number2ConvertedInt!!.toDouble()
                        }else{
                            result = number1ConvertedInt!! / number2ConvertedInt!!
                        }
                        return result
                    }

                    //DIVISION FOR Doubles
                    if(number1ConvertedDouble != null && number2ConvertedDouble != null){
                       val result :Double =  number1ConvertedDouble!! / number2ConvertedDouble!!
                        return result

                    }

                    //DIVISION FOR num1Doub and num2Int
                    if(number1ConvertedDouble != null && number2ConvertedInt != null){
                       val result :Double =  number1ConvertedDouble!! / number2ConvertedInt!!
                        return result
                    }
                    //DIVISION FOR num1Int and num2Double
                    if(number1ConvertedInt != null && number2ConvertedDouble != null) {
                       val result :Double =  number1ConvertedInt!!.toDouble() / number2ConvertedDouble!!
                        return result
                    }
                }

            }
        return null
    }
    private fun treatResult(){
        val resultToString =calculate() as Number
        if(resultToString.toString() == "0.0"){
            txtResult.text="0"
        }else{
            if(resultToString.toString().endsWith(".0")){
                txtResult.text= resultToString.toString().trim('.','0')
            }else{
                txtResult.text=resultToString.toString()
            }
        }
        this.number1ConvertedInt = null
        this.number2ConvertedInt = null
        this.number1ConvertedDouble = null
        this.number2ConvertedInt = null
    }
    private fun calculateResult(forcedResult:Boolean=false){
        when(this.currentOp){
            "+" ->{
                settingStringNumbers(Pattern.compile("\\+").split(txtResult.text))
            }
            "-" ->{
                settingStringNumbers(Pattern.compile("-").split(txtResult.text))
            }
            "x" -> {
                settingStringNumbers(Pattern.compile("x").split(txtResult.text))
            }
            "/" -> {
                settingStringNumbers(Pattern.compile("/").split(txtResult.text))
            }
        }
        if(forcedResult){
            txtResult.text="${txtResult.text}$currentOp"
        }
    }
}