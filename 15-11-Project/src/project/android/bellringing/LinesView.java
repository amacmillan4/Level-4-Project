package project.android.bellringing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class LinesView extends TextView {

	String bell = "2";
	
	public boolean lines = false;
	Paint paint1 = new Paint();
	Paint paint2 = new Paint();

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
		this.bell = bell;
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

		if (lines){
			paint2.setAntiAlias(true);
			paint2.setStrokeWidth(8f);
			paint2.setColor(Color.BLUE);

			paint1.setAntiAlias(true);
			paint1.setStrokeWidth(8f);
			paint1.setColor(Color.RED);

			String a = (String) super.getText().toString();

			int index2 = a.indexOf(bell);
			int index1 = a.indexOf("1");
			
			int next2 = 0;
			int next1 = 0;
			
			int y = 32;

			while (a.length() > 8){
				
				a = a.substring(9, a.length());

				next2 = a.indexOf(bell);
				next1 = a.indexOf("1");
				
				if (next2 != -1)
					canvas.drawLine(12 + index2 * 25, y-2,12 + next2 * 25, y + 52, paint2);
				
				if (next1 != -1)
					canvas.drawLine(12 + index1 * 25, y-2,12 + next1 * 25, y + 52, paint1);
				
				y = y + 52;
				
				index2 = next2;
				index1 = next1;
				
			}
		}

		super.onDraw(canvas);

	}

}
