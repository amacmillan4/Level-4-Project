package project.android.bellringing.views;

import project.android.bellringing.utilities.Utils;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class LinesView extends TextView {

	String bell1 = "2";
	String bell2 = "3";
	int bells;

	boolean lines = false;
	boolean handbells = false;
	boolean testSizes = true;
	float height = 0;
	float width = 0;


	Paint paint1 = new Paint();
	Paint paint2 = new Paint();
	Paint paint3 = new Paint();


	public LinesView(Context context){
		super(context);
	}

	public LinesView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public LinesView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setBell(String bell){
		this.bell1 = bell;
	}

	public void setSecondBell(String bell){
		this.bell2 = bell;
	}

	public void setNumberOfBells(int bell){
		this.bells = bell;
	}
	public void calcDimensions(){
		String s = "";
		for(int i = 0; i < bells; i++)
			s+=" ";

		String t = s + "\n\n\n\n\n\n";

		super.setText(t);
	}

	public void isHandbells(boolean x){
		handbells = x;
	}

	public void clearText(){
		super.setText("");
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
		super.setText(super.getText().toString());
	}

	public boolean getdrawLines(){
		return lines;
	}

	@Override
	public void onDraw(Canvas canvas){

		height = (float) super.getHeight()/ 13.7f - Utils.dpToPx(0.0f, getContext()) ;
		width =  (float) super.getWidth()/((float) bells * 2f + 1f);

		if (lines){
			paint2.setAntiAlias(true);
			paint2.setStrokeWidth(6f);
			paint2.setColor(Color.BLUE);

			paint1.setAntiAlias(true);
			paint1.setStrokeWidth(6f);
			paint1.setColor(Color.RED);

			paint3.setAntiAlias(true);
			paint3.setStrokeWidth(6f);
			paint3.setColor(Color.GREEN);

			String a = (String) super.getText().toString();

			int index2 = a.indexOf(bell1);
			int index1 = a.indexOf("1");
			int index3 = a.indexOf(bell2);

			int next2 = 0;
			int next1 = 0;
			int next3 = 0;

			float y = height;

			while (a.length() > bells){

				a = a.substring(bells + 1, a.length());

				next2 = a.indexOf(bell1);
				next3 = a.indexOf(bell2);
				next1 = a.indexOf("1");

				if (next2 != -1)
					canvas.drawLine(1 + width + index2 *2* width, y-1 ,1 + width + next2 *2* width, y+1 + (2*height), paint2);

				if (next1 != -1)
					canvas.drawLine(1 + width + index1 *2* width,y-1 ,1 + width + next1 *2* width, y+1 + (2*height), paint1);


				if(handbells)
					if (next3 != -1)
						canvas.drawLine(1 + width + index3 *2* width,y-1 ,1 + width + next3 *2* width, y +1+ (2*height), paint3);

				y = y + (2*height);

				index2 = next2;
				index1 = next1;
				index3 = next3;


			}
		}

		super.onDraw(canvas);

	}

}
