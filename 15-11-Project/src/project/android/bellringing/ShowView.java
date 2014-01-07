package project.android.bellringing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.TextView;

public class ShowView extends TextView {

	String bell = "2";
	int bells;
	int display = 0;
		
	public boolean lines = false;
	Paint paint1 = new Paint();
	Paint paint2 = new Paint();
	Paint p = new Paint();

	public ShowView(Context context){
		super(context);
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
		display = (display + 1) % 3;
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
		
		if (display == 0 || display == 1){
			
			if (display == 0)
				setTextColor(Color.BLACK);
			else if (display == 1)
				setTextColor(Color.TRANSPARENT);
			
			paint2.setAntiAlias(true);
			paint2.setStrokeWidth(5f);
			paint2.setColor(Color.GREEN);

			paint1.setAntiAlias(true);
			paint1.setStrokeWidth(5f);
			paint1.setColor(Color.RED);

			String a = (String) super.getText().toString();

			int index2 = a.indexOf(bell);
			int index1 = a.indexOf("1");
			
			int next2 = 0;
			int next1 = 0;
			
			float y = Utils.dpToPx(11, getContext());
			
			float pos = getWidth()/(bells * 2);
			
			for (float i = 0; i < getWidth(); i = i + pos)
				canvas.drawLine(i, 10, i, 200, p);

			while (a.length() > bells){
				
				a = a.substring(bells + 1, a.length());

				next2 = a.indexOf(bell);
				next1 = a.indexOf("1");
				
				if (next2 != -1)
					canvas.drawLine( (float) (pos + (index2 * (2 * pos))), y - Utils.dpToPx(1, getContext()),
							(float) (pos + (next2 * (2 * pos))), y + Utils.dpToPx(16, getContext()), paint2);
				
				if (next1 != -1)
					canvas.drawLine((float) (pos + (index1 * (2 * pos))), y - Utils.dpToPx(1, getContext()),
							(float) (pos + (next1 * (2 * pos))), y + Utils.dpToPx(16, getContext()), paint1);
				
				y = y + Utils.dpToPx(16f, getContext());
				
				index2 = next2;
				index1 = next1;
				
			}
			
			if (display == 1){
				
				p.setAntiAlias(true);
				p.setStrokeWidth(2f);
				p.setColor(Color.BLACK);

				
				for(float i = 0; i < getHeight(); i = i + getHeight()/32 ){
					
					for(float j = 0; j < bells/2; j++){
						
						float distance = getWidth()/(bells);
						
						canvas.drawLine(distance + j * 4f * distance, 10 + i, distance + j * 4f * distance, 10 + i + 12, p);
						
					}
										
				}
			}
			
		}

		super.onDraw(canvas);

	}

}
