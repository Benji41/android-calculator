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
    private fun isForcingOperation(i:Int){
        if(txtResult.text.contains("+") || txtResult.text.contains("-") ||txtResult.text.contains("x") || txtResult.text.contains("/") ){
            calculateResult(true)
        }else{txtResult.text="${txtResult.text}${btn_operations[i].text}"}
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
    private fun settingStringNumbers(arr:Array<String>){
        lateinit var number1 :String
        lateinit var number2 :String
        number1 = arr[0]
        number2 = arr[1]
        findRightDataType(number1,number2)
        treatResult()
    }
    private fun calculate() : Number?{
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
                       val result :Double =  number1ConvertedDouble!! + number2ConvertedInt!!.toDouble()
                        return result
                    }
                    //ADDITION FOR num1Int and num2Double
                    if(number1ConvertedInt != null && number2ConvertedDouble != null){
                       val result :Double =  number1ConvertedInt!!.toDouble() + number2ConvertedDouble!!
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
                      val result :Double =  number1ConvertedDouble!! - number2ConvertedInt!!.toDouble()
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
                       val result :Double =  number1ConvertedDouble!! * number2ConvertedInt!!.toDouble()
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
                       val result :Double =  number1ConvertedDouble!! / number2ConvertedInt!!.toDouble()
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
        val resultToString =calculate()?.toString()!!
        if(resultToString == "0.0"){
            txtResult.text="0"
        }else{
            if(resultToString.endsWith(".0")){
                txtResult.text= calculate()?.toString()!!.trim('.','0')
            }else{
                txtResult.text=resultToString
            }
        }
    }
    private fun calculateResult(forcedResult:Boolean=false):String{
        val numbersString :Any

        when(this.currentOp){
            "+" ->{
                numbersString = Pattern.compile("\\+").split(txtResult.text)
                settingStringNumbers(numbersString)
            }
            "-" ->{
                numbersString = Pattern.compile("-").split(txtResult.text)
                settingStringNumbers(numbersString)
            }
            "x" -> {
                numbersString = Pattern.compile("x").split(txtResult.text)
                settingStringNumbers(numbersString)
            }
            "/" -> {
                numbersString = Pattern.compile("/").split(txtResult.text)
                settingStringNumbers(numbersString)
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
            if(!txtResult.text.contains("+") && !txtResult.text.contains("-") && !txtResult.text.contains("x") && !txtResult.text.contains("/") ){
                txtResult.text=txtResult.text
            }else{calculateResult()}
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
                        when(btn_operations[i].text){
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
}