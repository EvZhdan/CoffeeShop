package biz.softmobile.coffeeshop;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int amountCoffee=2;
    private int price=5;
    private boolean cream=false;
    private boolean choco_in_coffee=false;
    private int creamPrice = 2;
    private int pr=0;
    private int chocolate = 1;
    private int totalPrice=0;
    private String userName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        displayCoffeeCups(amountCoffee);
        coffeeForPrice(price, amountCoffee);
    }

    public void orderButton(View view){
        totalPrice = coffeeForPrice(price, amountCoffee);
        CheckBox checkBox = (CheckBox) findViewById(R.id.wipped_cream);
        CheckBox checkBoxChoco = (CheckBox) findViewById(R.id.choco);
        EditText editText = (EditText) findViewById(R.id.enterYourName);
        userName = editText.getText().toString();
        if(checkBox.isChecked()){
            cream=true;
            totalPrice += coffeeForPrice(creamPrice, amountCoffee);
        }

        if(checkBoxChoco.isChecked()){
            choco_in_coffee=true;
            totalPrice += coffeeForPrice(chocolate, amountCoffee);
        }

        TextView textView = (TextView) findViewById(R.id.your_order);

        textView.setText("Name: " + userName + "\nQuantity: " +  amountCoffee +
                "\nCream: "+ cream + ", Chocolate: " + choco_in_coffee +

                "\nTotal: $" + totalPrice + "\nThank You!");

    }

    public void orderNull(){
        TextView textView = (TextView) findViewById(R.id.your_order);
        textView.setText("");
    }

    public void displayCoffeeCups(int amount){
        TextView textView = (TextView) findViewById(R.id.amount_of_coffee);
        textView.setText(""+amount);
    }

    public int coffeeForPrice(int amount, int priceOneCup){
        TextView textView = (TextView) findViewById(R.id.price);
        int how_much = amount * priceOneCup;
        textView.setText("Total: $"+how_much);
        return how_much;
    }

    public void plusCups(View view){
        amountCoffee++;
        displayCoffeeCups(amountCoffee);
        coffeeForPrice(price, amountCoffee);
        orderNull();
    }

    public void minusCups(View view){
        amountCoffee--;
            if(amountCoffee<=1)
                {
                    amountCoffee=1;
                    Toast toast = Toast.makeText(getApplicationContext(),"Нельзя заказать меньше 1 чашки!", Toast.LENGTH_SHORT);
                    toast.show();
                }
        displayCoffeeCups(amountCoffee);
        coffeeForPrice(price, amountCoffee);
        orderNull();
    }
}
