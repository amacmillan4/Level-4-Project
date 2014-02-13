package project.android.bellringing.views;

import project.android.bellringing.utilities.Utils;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class BobSingleView extends TextView {

	String bell = "2";
	int bells;
	int display = 0;

	String[] possiblities = {"1","2","3","4", "5", "6", "7", "8", "9", "0", "E", "T", "A","B","C", "D"};

	public boolean lines = false;
	Paint paint1 = new Paint();
	Paint paint2 = new Paint();
	Paint p = new Paint();

	public BobSingleView(Context context){
		super(context);
	}

	public BobSingleView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BobSingleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setBell(String bell){
		this.bell = bell;
	}

	public void setNumberOfBells(int bell){
		this.bells = bell;
	}

	public void clearText(){
		super.setText("");
	}

	public void changeDisplay(){
		display = (display + 1) % 2;
		super.setText(super.getText().toString());

	}

	public void setLimitingText(int bells, String text, int lines){

		int characters_allowed = ((bells + 1) * (lines)) - 1;

		if (text.length() > characters_allowed){
			text = text.substring(text.length() - characters_allowed, text.length());
			text = text.substring(1 + text.indexOf("\n"), text.length());
		}

		super.setText(text);
	}

	public void drawLines(boolean lines){
		this.lines  = lines;
	}

	public boolean getdrawLines(){
		return lines;
	}

	@Override
	public void onDraw(Canvas canvas){


		if (display == 0)
			setTextColor(Color.BLACK);
		else
			setTextColor(Color.TRANSPARENT);


		paint2.setAntiAlias(true);
		paint2.setStrokeWidth(4f);
		paint2.setColor(Color.BLUE);

		paint1.setAntiAlias(true);
		paint1.setStrokeWidth(5f);
		paint1.setColor(Color.RED);

		String a = (String) super.getText().toString();

		for(int i = 0; i < bells; i++){

			String currentBell = possiblities[i];

			if (currentBell.equals("1"))
				p = paint1;
			else
				p = paint2;

			float y = Utils.dpToPx(11, getContext());
			float pos = getWidth()/(bells * 2);

			String b = a;

			int index = b.indexOf(currentBell);
			int next = 0;


			while (b.length() > bells){

				b = b.substring(bells + 1, b.length());

				next = b.indexOf(currentBell);

				if (next != -1)
					canvas.drawLine( (float) (pos + (index * (2 * pos))), y - Utils.dpToPx(1, getContext()),
							(float) (pos + (next * (2 * pos))), y + Utils.dpToPx(16, getContext()), p);

				y = y + Utils.dpToPx(16f, getContext());

				index = next;
			}
			
			Paint line = new Paint();
			line.setColor(Color.BLACK);
			line.setAntiAlias(true);
			line.setStrokeWidth(2f);
			
			canvas.drawLine(0, getHeight()/2, getWidth(), getHeight()/2, line);
		}

		super.onDraw(canvas);

	}

}
